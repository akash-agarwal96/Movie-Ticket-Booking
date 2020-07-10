<%@page import="in.co.online.ticket.controller.MovieCtl"%>
<%@page import="in.co.online.ticket.util.DataUtility"%>
<%@page import="in.co.online.ticket.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div id="fh5co-contact" class="fh5co-section-gray">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						<h3>Add Movie</h3>
						<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
						<b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
					</div>
			</div>
				
				<form action="<%=OTBView.MOVIE_CTL%>"  method="post" enctype="multipart/form-data">
				
				<jsp:useBean id="bean" class="in.co.online.ticket.bean.MovieBean"
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
								<label>Name</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Name"
										name="name" value="<%=DataUtility.getStringData(bean.getName())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font>
									</div>
								</div>
								<div class="col-md-6">
								<label>Certificate</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Certificate"
										name="certificate" value="<%=DataUtility.getStringData(bean.getCertificate())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("certificate", request)%></font>
									</div>
								</div>
								
								<div class="col-md-6">
								<label>Type</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Type"
										name="type" value="<%=DataUtility.getStringData(bean.getType())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("type", request)%></font>
									</div>
								</div>
								<div class="col-md-6">
								<label>Duration</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Duration"
										name="duration" value="<%=DataUtility.getStringData(bean.getDuration())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("duration", request)%></font>
									</div>
								</div>
								
								<div class="col-md-6">
								<label>Language</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Language"
										name="language" value="<%=DataUtility.getStringData(bean.getLanguage())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("language", request)%></font>
									</div>
								</div>
								
								<div class="col-md-6">
								<label>Director</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Director"
										name="director" value="<%=DataUtility.getStringData(bean.getDirector())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("director", request)%></font>
									</div>
								</div>
								
								<div class="col-md-12">
								<label>Cast</label>
									<div class="form-group">
									<textarea name="cast" class="form-control"  cols="30" rows="4" placeholder="Enter Cast"><%=DataUtility.getStringData(bean.getCast())%></textarea>
										<font color="red"> <%=ServletUtility.getErrorMessage("cast", request)%></font>
									</div>
								</div>

								
								<div class="col-md-12">
								<label>Image</label>
									<div class="form-group">
										<input type="file" class="form-control" placeholder="Upload  Image"
										name="image" value="<%=DataUtility.getStringData(bean.getImage())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("image", request)%></font>
									</div>
								</div>
								
								<div class="col-md-12">
								<label>Price</label>
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Price"
										name="price" value="<%=(bean.getPrice()==0)?"":bean.getPrice()%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("price", request)%></font>
									</div>
								</div>
								
								<div class="col-md-12">
								<label>Description</label>
									<div class="form-group">
									<textarea name="description" class="form-control"  cols="30" rows="4" placeholder="Enter Description"><%=DataUtility.getStringData(bean.getDescription())%></textarea>
										<font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<input type="submit" name="operation" value="<%=MovieCtl.OP_SAVE%>" class="btn btn-primary">
										<input type="submit" name="operation" value="<%=MovieCtl.OP_RESET%>" class="btn btn-primary">
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