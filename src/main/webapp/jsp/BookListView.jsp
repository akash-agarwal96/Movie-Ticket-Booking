<%@page import="in.co.online.ticket.model.UserModel"%>
<%@page import="in.co.online.ticket.bean.MovieBean"%>
<%@page import="in.co.online.ticket.model.MovieModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.online.ticket.bean.BookBean"%>
<%@page import="in.co.online.ticket.controller.BookListCtl"%>
<%@page import="in.co.online.ticket.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book List</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div id="fh5co-blog-section" class="fh5co-section-gray">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						<h3>Book Movie List</h3>
						<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
						<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
					</div>
				</div>
			</div>
	<form action="<%=OTBView.BOOK_LIST_CTL%>" method="post">	
		
			<div class="container">
				<div class="row row-bottom-padded-md">
				
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					BookBean bean = null;
					List list = ServletUtility.getList(request);
					Iterator<BookBean> i = list.iterator();
					while (i.hasNext()) {
						bean = i.next();
						
						MovieModel mModel=new MovieModel();
						MovieBean mBean=mModel.findByPK(bean.getMovieId());
						
						
				%>
					<div class="col-lg-4 col-md-4 col-sm-6">
						<div class="fh5co-blog animate-box">
							<a href="#"><img class="img-responsive" src="../images/<%=mBean.getImage()%>" alt=""></a>
							<div class="blog-text">
								<div class="prod-title">
									<h3><%=bean.getMovieName()%></h3>
									<p>User Name:&nbsp;<%=bean.getUserName()%></p>
									<p>No Of Person:&nbsp;<%=bean.getNoOfPerson()%></p>
									<span class="posted_by">(<%=mBean.getCertificate()%>),&nbsp;<%=mBean.getLanguage()%>,&nbsp;<%=mBean.getType()%>,&nbsp;<%=mBean.getDuration()%></span>
									<p><%=mBean.getDirector()%></p>
									<p><%=mBean.getCast()%></p>
									<p><a href="#"><%=bean.getFinalAmount()%>&nbsp;Rs</a></p>
									
									<p>
									<%if(userBean.getRoleId()==2){ %>
									<a href="BookListCtl?Bid=<%=bean.getId()%>" class="btn btn-primary">Cancel</a>
								<%} %> 
									</p>
								</div>
							</div> 
						</div>
					</div>
					
					<div class="clearfix visible-sm-block"></div>
					<%} %>
	
				</div>
			</div>
			
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
			</form>
		</div>
<%@ include file="Footer.jsp" %>
</body>
</html>