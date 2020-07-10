<%@page import="in.co.online.ticket.controller.ChangePasswordCtl"%>
<%@page import="in.co.online.ticket.util.DataUtility"%>
<%@page import="in.co.online.ticket.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password </title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div id="fh5co-contact" class="fh5co-section-gray">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						<h3>Change Password</h3>
						<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
						<b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
					</div>
			
			</div>
				
				<form action="<%=OTBView.CHANGE_PASSWORD_CTL%>" method="post">
				
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

								<div class="col-md-12">
								<label>Old Password</label>
									<div class="form-group">
										<input type="password" class="form-control" placeholder="Enter Old Password"
										name="oldPassword" value=<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>>
										<font color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
									</div>
								</div>
								
								<div class="col-md-6">
								<label>New Password</label>
									<div class="form-group">
										<input type="password" class="form-control" placeholder="Enter New Password"
										name="newPassword" value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request.getParameter("newPassword")))%>>
										<font color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font>
									</div>
								</div>
								<div class="col-md-6">
								<label>Confirm Password</label>
									<div class="form-group">
										<input type="password" class="form-control" placeholder="Confirm Password"
										name="confirmPassword" value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%>>
										<font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<input type="submit" name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>" class="btn btn-primary">
										<input type="submit" name="operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>" class="btn btn-primary">
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