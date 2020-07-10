package in.co.online.ticket.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.online.ticket.bean.BaseBean;
import in.co.online.ticket.bean.UserBean;
import in.co.online.ticket.exception.ApplicationException;
import in.co.online.ticket.exception.RecordNotFoundException;
import in.co.online.ticket.model.UserModel;
import in.co.online.ticket.util.DataUtility;
import in.co.online.ticket.util.DataValidator;
import in.co.online.ticket.util.PropertyReader;
import in.co.online.ticket.util.ServletUtility;



/**
 * Servlet implementation class ChangePasswordCtl
 */

/**
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */
@WebServlet(name = "ChangePasswordCtl", urlPatterns = { "/ctl/ChangePasswordCtl" })
public class ChangePasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ChangePasswordCtl.class);

	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";

	/**
	 * Validates input data entered by User
	 * 
	 * @param request
	 * @return
	 */

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("ChangePasswordCtl  validate method start");

		boolean pass = true;

		String op = request.getParameter("operation");

		if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {

			return pass;
		}
		
		if (DataValidator.isNull(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword", PropertyReader.getValue("error.require", "Old Password"));
			pass = false;
		}else if (!DataValidator.isPassword(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword", PropertyReader.getValue("error.password", "Old Password"));
			return false;
		}
		if (DataValidator.isNull(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", PropertyReader.getValue("error.require", "New Password"));
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", PropertyReader.getValue("error.password", "New Password"));
			return false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}
		if (!request.getParameter("newPassword").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			ServletUtility.setErrorMessage("New and confirm passwords not matched", request);

			pass = false;
		}

		log.debug("ChangePasswordCtl  validate method end");
		return pass;
	}
	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("ChangePasswordCtl  populateBean method start");

		UserBean bean = new UserBean();
		bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));

		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		populateDTO(bean, request);
		log.debug("ChangePasswordCtl  populateBean method end");
		return bean;
	}

	/**
	 * Display Logics inside this method
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("ChangePasswordCtl  doGet method start");
		ServletUtility.forward(getView(), request, response);
		log.debug("ChangePasswordCtl  doGet method end");
	}

	/**
	 * Submit logic inside it
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("ChangePasswordCtl  doPost method start");

		HttpSession session = request.getSession(true);

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModel model = new UserModel();
		UserBean bean = (UserBean) populateBean(request);

		UserBean UserBean = (UserBean) session.getAttribute("user");

		String newPassword = request.getParameter("newPassword");
		
		long id = UserBean.getId();
		
		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				boolean flag = model.changePassword(id, bean.getPassword(), newPassword);
		
				if (flag == true) {
				
					bean = model.findByLogin(UserBean.getLogin());
					
					session.setAttribute("user", bean);
					
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Password has been changed Successfully", request);
				}
			} catch (ApplicationException e) {

				ServletUtility.handleException(e, request, response);
				return;

			} catch (RecordNotFoundException e) {
				ServletUtility.setErrorMessage("Old Password is Invalid", request);
			}
		} else if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OTBView.MY_PROFILE_CTL, request, response);
			return;
		}

		ServletUtility.forward(OTBView.CHANGE_PASSWORD_VIEW, request, response);
		log.debug("ChangePasswordCtl  doPost method end");
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTBView.CHANGE_PASSWORD_VIEW;
	}

}
