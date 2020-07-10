<%@page import="in.co.online.ticket.controller.LoginCtl"%>
<%@page import="in.co.online.ticket.util.DataUtility"%>
<%@page import="in.co.online.ticket.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div id="fh5co-contact" class="fh5co-section-gray">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						<h3>Login</h3>
						<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
							<b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
							</font></b>
					</div>
			
			</div>
				
				<form action="<%=OTBView.LOGIN_CTL%>" method="post">
				
				<jsp:useBean id="bean" class="in.co.online.ticket.bean.UserBean"
			scope="request"></jsp:useBean>
			<% String uri=(String)request.getAttribute("uri");%>
		
              <input type="hidden" name="uri" value="<%=uri%>">
			
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
								<label>Login Id</label>
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
								
								
								
								<div class="col-md-12">
									<div class="form-group">
										<input type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_IN%>" class="btn btn-primary">
										<input type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>" class="btn btn-primary">
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