<%@page import="in.co.online.ticket.controller.UserRegistrationCtl"%>
<%@page import="in.co.online.ticket.util.ServletUtility"%>
<%@page import="in.co.online.ticket.util.DataUtility"%>
<%@page import="in.co.online.ticket.controller.OTBView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div id="fh5co-contact" class="fh5co-section-gray">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						<h3>User Registration</h3>
						<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
						<b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
					</div>
			
			</div>
				
				<form action="<%=OTBView.USER_REGISTRATION_CTL%>" method="post">
				
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
										<input type="text" class="form-control" placeholder="Enter Login Id"
										name="login" value="<%=DataUtility.getStringData(bean.getLogin())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
									</div>
								</div>
								
					
							<div class="col-md-6">
								<label>Password</label>
									<div class="form-group">
										<input type="password" class="form-control" placeholder="Enter Password"
										name="password" value="<%=DataUtility.getStringData(bean.getPassword())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
									</div>
								</div>
								<div class="col-md-6">
								<label>Confirm Password</label>
									<div class="form-group">
										<input type="password" class="form-control" placeholder="Confirm Password"
										name="confirmPassword" value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
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
										<input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP%>" class="btn btn-primary">
										<input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_RESET%>" class="btn btn-primary">
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