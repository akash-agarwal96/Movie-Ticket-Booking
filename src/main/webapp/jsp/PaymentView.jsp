<%@page import="in.co.online.ticket.controller.BookCtl"%>
<%@page import="in.co.online.ticket.util.ServletUtility"%>
<%@page import="in.co.online.ticket.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.online.ticket.util.DataUtility"%>
<%@page import="in.co.online.ticket.bean.MovieBean"%>
<%@page import="in.co.online.ticket.model.MovieModel"%>
<%@page import="in.co.online.ticket.bean.BookBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
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
				<% BookBean bBean=(BookBean)session.getAttribute("BookB");
				
					MovieModel mModel=new MovieModel();
					MovieBean mBean=mModel.findByPK(bBean.getMovieId());
				%>
				<div class="row">
					<div class="col-md-12 animate-box">
						<h2 class="heading-title"><%=mBean.getName()%></h2>
						<%  String mssg=(String)request.getAttribute("msg"); %>
						<b><font color="green"> <%=(mssg==null)?"":mssg%>
						</font></b>
					</div>
					<div class="col-md-6 animate-box">
						<span class="posted_by">(<%=mBean.getCertificate()%>),&nbsp;<%=mBean.getLanguage()%>,&nbsp;<%=mBean.getType()%>,&nbsp;<%=mBean.getDuration()%></span>
									<p><%=mBean.getDirector()%></p>
									<p><%=mBean.getCast()%></p>
									<p><a href="#"><%=mBean.getPrice()%>&nbsp;Rs</a></p>
									<p><%=mBean.getDescription()%></p>
									<p><%=userBean.getFirstName()+" "+userBean.getLastName()%></p>
									<p>Total Amount:<%=bBean.getFinalAmount()%>&nbsp;Rs</p>
					</div>
					<div class="col-md-6 animate-box">
						<img class="img-responsive" src="../images/<%=mBean.getImage()%>" alt="travel">
					</div>
				</div>
				
				
				<%if(mssg==null){%>
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
							<div class="col-md-6">
								<label>Credit Card Number</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Credit Card Number"
										name="" value="">
									</div>
								</div>
								
								<div class="col-md-6">
								<label>Name on Credit Card</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Name on Credit Card"
										name="" value="">
									</div>
								</div>
								<div class="col-md-12">
								<label>Credit Card Type</label>
									<div class="form-group">
									<% HashMap<String,String> map=new HashMap<String,String>();
										map.put("Saving","Saving");
										map.put("Cureent","Current");
									
										
									%>
									<%=HTMLUtility.getList("show",String.valueOf(bean.getShowTime()), map) %>
									</div>
								</div>
								
								<div class="col-md-6">
								<label>Month</label>
									<div class="form-group">
									<% HashMap<String,String> map1=new HashMap<String,String>();
										map1.put("January","January");
										map1.put("Febuary","Febuary");
										map1.put("March","March");
										map1.put("April","April");
									
										
									%>
									<%=HTMLUtility.getList("show",String.valueOf(bean.getShowTime()), map1) %>
									</div>
								</div>
								
								<div class="col-md-6">
								<label>Year</label>
									<div class="form-group">
									<% HashMap<String,String> map2=new HashMap<String,String>();
										map2.put("2019","2019");
										map2.put("2021","2021");
										map2.put("2022","2022");
										map2.put("2023","2023");
									
										
									%>
									<%=HTMLUtility.getList("show",String.valueOf(bean.getShowTime()), map2) %>
									</div>
								</div>
								
								<div class="col-md-12">
								<label>CVV No.</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter CVV No."
										name="" value="">
									</div>
								</div>
								
								<div class="col-md-12">
								<label>Amount Paid</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Amount Paid" readonly="readonly"
										name="" value="<%=bBean.getFinalAmount()%>">
									</div>
								</div>
								
								
								
								<div class="col-md-12">
									<div class="form-group">
										<input type="submit" name="operation" value="<%=BookCtl.OP_PAYMENT_BOOK%>" class="btn btn-primary">
										<input type="submit" name="operation" value="<%=BookCtl.OP_CANCEL%>" class="btn btn-primary">
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
				</form>
				<%} %>
			</div>
		</div>
		
<%@ include file="Footer.jsp" %>
</body>
</html>