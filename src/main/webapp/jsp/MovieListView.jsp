<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.ticket.bean.MovieBean"%>
<%@page import="in.co.online.ticket.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movies List</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div id="fh5co-blog-section" class="fh5co-section-gray">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						<h3>Movies</h3>
						<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
						<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
					</div>
				</div>
			</div>
	<form action="<%=OTBView.MOVIE_LIST_CTL%>" method="post">	
		
			<div class="container">
				<div class="row row-bottom-padded-md">
				
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					MovieBean bean = null;
					List list = ServletUtility.getList(request);
					Iterator<MovieBean> i = list.iterator();
					while (i.hasNext()) {
						bean = i.next();
				%>
					<div class="col-lg-4 col-md-4 col-sm-6">
						<div class="fh5co-blog animate-box">
							<a href="#"><img class="img-responsive" src="../images/<%=bean.getImage()%>" alt=""></a>
							<div class="blog-text">
								<div class="prod-title">
									<h3><%=bean.getName()%></h3>
									<span class="posted_by">(<%=bean.getCertificate()%>),&nbsp;<%=bean.getLanguage()%>,&nbsp;<%=bean.getType()%>,&nbsp;<%=bean.getDuration()%></span>
									<p><%=bean.getDirector()%></p>
									<p><%=bean.getCast()%></p>
									<p><a href="#"><%=bean.getPrice()%>&nbsp;Rs</a></p>
									<p>
									<%if(userBean.getRoleId()==2){ %>
									<a href="BookCtl?moID=<%=bean.getId()%>" class="btn btn-primary">Book</a>
									<%}else{%>
									<a href="MovieCtl?id=<%=bean.getId()%>" class="btn btn-primary">Edit</a>
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