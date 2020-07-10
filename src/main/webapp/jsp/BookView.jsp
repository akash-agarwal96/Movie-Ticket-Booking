<%@page import="in.co.online.ticket.controller.BookCtl"%>
<%@page import="in.co.online.ticket.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.online.ticket.util.ServletUtility"%>
<%@page import="in.co.online.ticket.util.DataUtility"%>
<%@page import="in.co.online.ticket.bean.MovieBean"%>
<%@page import="in.co.online.ticket.model.MovieModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div id="fh5co-tours" class="fh5co-section-gray">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						<h3>Book</h3>
					</div>
				</div>
				<% long mid=(long)session.getAttribute("MoId");
				
				MovieModel mModel=new MovieModel();
				
				MovieBean mBean=mModel.findByPK(mid);
				
				%>
				<div class="row">
					<div class="col-md-12 animate-box">
						<h2 class="heading-title"><%=mBean.getName()%></h2>
					</div>
					<div class="col-md-6 animate-box">
						<span class="posted_by">(<%=mBean.getCertificate()%>),&nbsp;<%=mBean.getLanguage()%>,&nbsp;<%=mBean.getType()%>,&nbsp;<%=mBean.getDuration()%></span>
									<p><%=mBean.getDirector()%></p>
									<p><%=mBean.getCast()%></p>
									<p><a href="#"><%=mBean.getPrice()%>&nbsp;Rs</a></p>
									<p><%=mBean.getDescription()%></p>
						
					</div>
					<div class="col-md-6 animate-box">
						<img class="img-responsive" src="../images/<%=mBean.getImage()%>" alt="travel">
					</div>
				</div>
				
				<form action="<%=OTBView.BOOK_CTL%>"  method="post" >
				
				<jsp:useBean id="bean" class="in.co.online.ticket.bean.BookBean"
			scope="request"></jsp:useBean>
			
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
				
					<div class="row animate-box">
					
						<div class="col-md-6">
							<div class="row">
							<div class="col-md-12">
								<label>Date</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Date"
										name="bookDate" value="<%=DataUtility.getDateString(bean.getBookDate())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("bookDate", request)%></font>
									</div>
								</div>
								<div class="col-md-12">
								<label>Show Time</label>
									<div class="form-group">
									<% HashMap<String,String> map=new HashMap<String,String>();
										map.put("9:00 AM To 12:00 PM","9:00 AM To 12:00 PM");
										map.put("10:00 AM To 1:00 PM","10:00 AM To 9:00 PM");
										map.put("12:00 PM To 3:00 PM","12:00 PM To 3:00 PM");
										map.put("2:00 PM To 5:00 PM","2:00 PM To 5:00 PM");
										
									%>
									<%=HTMLUtility.getList("show",String.valueOf(bean.getShowTime()), map) %>
										<font color="red"> <%=ServletUtility.getErrorMessage("show", request)%></font>
									</div>
								</div>
								
								<div class="col-md-12">
								<label>No Of Person</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter No Of Person"
										name="noP" value="<%=(bean.getNoOfPerson()==0)?"":bean.getNoOfPerson()%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("noP", request)%></font>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<input type="submit" name="operation" value="<%=BookCtl.OP_PAYMENT%>" class="btn btn-primary">
										<input type="submit" name="operation" value="<%=BookCtl.OP_CANCEL%>" class="btn btn-primary">
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
				</form>
				
			</div>
		</div>
		
<%@ include file="Footer.jsp" %>
</body>
</html>