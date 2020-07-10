package in.co.online.ticket.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import in.co.online.ticket.bean.BaseBean;
import in.co.online.ticket.bean.BookBean;
import in.co.online.ticket.bean.MovieBean;
import in.co.online.ticket.bean.UserBean;
import in.co.online.ticket.exception.ApplicationException;
import in.co.online.ticket.exception.DuplicateRecordException;
import in.co.online.ticket.model.BookModel;
import in.co.online.ticket.model.MovieModel;
import in.co.online.ticket.util.DataUtility;
import in.co.online.ticket.util.DataValidator;
import in.co.online.ticket.util.PropertyReader;
import in.co.online.ticket.util.ServletUtility;

/**
 * Servlet implementation class BookCtl
 */
@WebServlet(name = "BookCtl", urlPatterns = { "/ctl/BookCtl" })
public class BookCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(MovieCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("MovieCtl validate method start");
		boolean pass = true;
		
		String op=DataUtility.getString(request.getParameter("operation"));
		
		if (DataValidator.isNull(request.getParameter("bookDate"))) {
			request.setAttribute("bookDate", PropertyReader.getValue("error.require", "Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("noP"))) {
			request.setAttribute("noP", PropertyReader.getValue("error.require", "No Of Person"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("show"))) {
			request.setAttribute("show",
					PropertyReader.getValue("error.require", "Show Time"));
			pass = false;
		}
		
		if(OP_PAYMENT_BOOK.equalsIgnoreCase(op)) {
			pass=true;
		}
		
		log.debug("MovieCtl validate method end");
		return pass;
	}
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("MovieCtl populateBean method start");
		BookBean bean = new BookBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setBookDate(DataUtility.getDate(request.getParameter("bookDate")));
		bean.setShowTime(DataUtility.getString(request.getParameter("show")));
		bean.setNoOfPerson(DataUtility.getLong(request.getParameter("noP")));
		populateDTO(bean, request);
		log.debug("MovieCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("MovieCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		HttpSession session=request.getSession();
		
		long Mid=DataUtility.getLong(request.getParameter("moID"));
		session.setAttribute("MoId",Mid);
		
		BookModel model = new BookModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			BookBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("MovieCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("DessertCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		BookModel model = new BookModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		HttpSession session=request.getSession();
		if (OP_PAYMENT.equalsIgnoreCase(op)) {

			BookBean bean = (BookBean) populateBean(request);
			
			UserBean uBean=(UserBean)session.getAttribute("user");
			long moovieId=(long)session.getAttribute("MoId");
			
			bean.setMovieId(moovieId);
			MovieModel mModel=new MovieModel();
			MovieBean mBean;
			try {
				mBean = mModel.findByPK(moovieId);
				bean.setFinalAmount(mBean.getPrice()*bean.getNoOfPerson());
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			bean.setUserId(uBean.getId());
			
			session.setAttribute("BookB", bean);
			
			ServletUtility.forward(OTBView.PAYMENT_VIEW, request, response);
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			BookBean bean = (BookBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OTBView.MOVIE_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OTBView.MOVIE_LIST_CTL, request, response);
			return;
		}else if(OP_PAYMENT_BOOK.equalsIgnoreCase(op)) {
			long pk=0;
			BookBean bBean=(BookBean)session.getAttribute("BookB");
			try {
				 pk=	model.add(bBean);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("msg","Ticket is Successfully Booked");
			ServletUtility.forward(OTBView.PAYMENT_VIEW, request, response);
		} 

		ServletUtility.forward(getView(), request, response);
		log.debug("DessertCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTBView.BOOK_VIEW;
	}

}
