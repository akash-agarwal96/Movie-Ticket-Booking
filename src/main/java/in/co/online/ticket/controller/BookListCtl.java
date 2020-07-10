package in.co.online.ticket.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.online.ticket.bean.BaseBean;
import in.co.online.ticket.bean.BookBean;
import in.co.online.ticket.bean.MovieBean;
import in.co.online.ticket.bean.UserBean;
import in.co.online.ticket.exception.ApplicationException;
import in.co.online.ticket.model.BookModel;
import in.co.online.ticket.model.MovieModel;
import in.co.online.ticket.util.DataUtility;
import in.co.online.ticket.util.PropertyReader;
import in.co.online.ticket.util.ServletUtility;

/**
 * Servlet implementation class BookListCtl
 */
@WebServlet(name = "BookListCtl", urlPatterns = { "/ctl/BookListCtl" })
public class BookListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(BookListCtl.class);

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BookListCtl populateBean method start");
		BookBean bean = new BookBean();
		bean.setUserName(DataUtility.getString(request.getParameter("name")));
		log.debug("BookListCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BookListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		long bid=DataUtility.getLong(request.getParameter("Bid"));
		BookModel model = new BookModel();
		BookBean bean = (BookBean) populateBean(request);
		try {
			HttpSession session=request.getSession();
			UserBean uBean=(UserBean)session.getAttribute("user");
			
			if(bid>0) {
				bean.setId(bid);
				model.delete(bean);
				ServletUtility.setErrorMessage("Ticlet Is Canceled", request);
			}
			
			if(uBean.getRoleId()==2) {
				bean.setUserName(uBean.getFirstName()+" "+uBean.getLastName());
			}
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("BookListCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("MovieListCtl doPost method start");
		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		BookBean bean = (BookBean) populateBean(request);

		BookModel model = new BookModel();
		String[] ids = request.getParameterValues("ids");
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {

			if (OP_SEARCH.equalsIgnoreCase(op)) {

				pageNo = 1;

			} else if (OP_NEXT.equalsIgnoreCase(op)) {

				pageNo++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {

				pageNo--;
			}
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OTBView.BOOK_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				BookBean deletebean = new BookBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						e.printStackTrace();
						return;
					}
				}
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OTBView.BOOK_LIST_CTL, request, response);
			return;

		}

		try {
			
			HttpSession session=request.getSession();
			UserBean uBean=(UserBean)session.getAttribute("user");
			if(uBean.getRoleId()==2) {
				bean.setUserName(uBean.getFirstName()+" "+uBean.getLastName());
			}

			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("NO Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}

		log.debug("MovieListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTBView.BOOK_LIST_VIEW;
	}

}
