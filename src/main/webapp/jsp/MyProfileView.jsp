<%@page import="in.co.online.ticket.controller.MyProfileCtl"%>
<%@page import="in.co.online.ticket.util.DataUtility"%>
<%@page import="in.co.online.ticket.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div id="fh5co-contact" class="fh5co-section-gray">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						<h3>My Profile</h3>
						<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
						<b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
					</div>
			
			</div>
				
				<form action="<%=OTBView.MY_PROFILE_CTL%>" method="post">
				
				<jsp:useBean id="bean" class="in.co.online.ticket.bean.UserBean"
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
								<label>First Name</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter First Name"
										name="firstName" value="<%=DataUtility.getStringData(bean.getFirstName())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
									</div>
								</div>
								<div class="col-md-6">
								<label>Last Name</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Last Name"
										name="lastName" value="<%=DataUtility.getStringData(bean.getLastName())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
									</div>
								</div>
								
								<div class="col-md-12">
								<label>Login</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Login Id" readonly="readonly"
										name="login" value="<%=DataUtility.getStringData(bean.getLogin())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
									</div>
								</div>
								
								<div class="col-md-12">
								<label>Mobile</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Mobile No"
										name="mobile" value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%></font>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<input type="submit" name="operation" value="<%=MyProfileCtl.OP_SAVE%>" class="btn btn-primary">
										<input type="submit" name="operation" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>" class="btn btn-primary">
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