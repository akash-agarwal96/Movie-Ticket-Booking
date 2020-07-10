package in.co.online.ticket.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import in.co.online.ticket.bean.BaseBean;
import in.co.online.ticket.bean.MovieBean;
import in.co.online.ticket.exception.ApplicationException;
import in.co.online.ticket.exception.DuplicateRecordException;
import in.co.online.ticket.model.MovieModel;
import in.co.online.ticket.util.DataUtility;
import in.co.online.ticket.util.DataValidator;
import in.co.online.ticket.util.PropertyReader;
import in.co.online.ticket.util.ServletUtility;

/**
 * Servlet implementation class MovieCtl
 */
@WebServlet(name = "MovieCtl", urlPatterns = { "/ctl/MovieCtl" })
@MultipartConfig(maxFileSize = 16177215)
public class MovieCtl extends BaseCtl {
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

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("certificate"))) {
			request.setAttribute("certificate", PropertyReader.getValue("error.require", "Certificate"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("type"))) {
			request.setAttribute("type", PropertyReader.getValue("error.require", "Type"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("language"))) {
			request.setAttribute("language", PropertyReader.getValue("error.require", "Language"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("director"))) {
			request.setAttribute("director", PropertyReader.getValue("error.require", "Director"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("cast"))) {
			request.setAttribute("cast", PropertyReader.getValue("error.require", "Cast"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		log.debug("MovieCtl validate method end");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("MovieCtl populateBean method start");
		MovieBean bean = new MovieBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setCertificate(DataUtility.getString(request.getParameter("certificate")));
		bean.setType(DataUtility.getString(request.getParameter("type")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));
		bean.setDirector(DataUtility.getString(request.getParameter("director")));
		bean.setCast(DataUtility.getString(request.getParameter("cast")));
		bean.setLanguage(DataUtility.getString(request.getParameter("language")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setPrice(DataUtility.getLong(request.getParameter("price")));
		populateDTO(bean, request);
		log.debug("MovieCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("MovieCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		MovieModel model = new MovieModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			MovieBean bean;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("In Do pOst================");
		log.debug("DessertCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		MovieModel model = new MovieModel();
		long id = DataUtility.getLong(request.getParameter("id"));

		String savePath = DataUtility.getString(PropertyReader.getValue("path"));

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		Part part = request.getPart("image");

		String fileName = extractFileName(part);
		part.write(savePath + File.separator + fileName);

		String filePath =fileName;

		if (OP_SAVE.equalsIgnoreCase(op)) {

			MovieBean bean = (MovieBean) populateBean(request);
			bean.setImage(filePath);

			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);

				} else {

					long pk = model.add(bean);
					// bean.setId(id);
					ServletUtility.setSuccessMessage("Data is successfully Saved", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.forward(OTBView.ERROR_VIEW, request, response);
				return;

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			MovieBean bean = (MovieBean) populateBean(request);
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
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OTBView.MOVIE_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("DessertCtl doPost method end");
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}


	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OTBView.MOVIE_VIEW;
	}

}
