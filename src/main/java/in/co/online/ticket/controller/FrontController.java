package in.co.online.ticket.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.online.ticket.util.ServletUtility;


/**
 * Servlet Filter implementation class FrontController
 */
/**
 * Main Controller performs session checking and logging operations before
 * calling any application controller.It prevents any user to access
 * application without login.
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */
@WebFilter(filterName="FrontCtl",urlPatterns={"/ctl/*","/doc/*"})
public class FrontController implements Filter {

	private static Logger log=Logger.getLogger(FrontController.class);
	
	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		log.debug("FrontController doFilter method start");
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(true);
	
		
		
		if (session.getAttribute("user") == null) {
			
			ServletUtility.setErrorMessage("Your session has been expired. Please re-Login.", request);
			
			String hitUri=request.getRequestURI();
		
			req.setAttribute("uri", hitUri);			
			
			ServletUtility.forward("/LoginCtl", request, response);
			
		} else {
			chain.doFilter(req, resp);
			System.out.println("respronse front coltoller");
		}
		log.debug("FrontController doFilter method end");
	}
	

	public void init(FilterConfig conf) throws ServletException {
		// TODO Auto-generated method stub

	}

}
