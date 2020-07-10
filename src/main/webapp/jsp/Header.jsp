<%@page import="in.co.online.ticket.controller.LoginCtl"%>
<%@page import="in.co.online.ticket.bean.UserBean"%>
<%@page import="in.co.online.ticket.controller.OTBView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="/OnlineTicketBooking/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="/OnlineTicketBooking/css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="/OnlineTicketBooking/css/bootstrap.css">
	<!-- Superfish -->
	<link rel="stylesheet" href="/OnlineTicketBooking/css/superfish.css">
	<!-- Magnific Popup -->
	<link rel="stylesheet" href="/OnlineTicketBooking/css/magnific-popup.css">
	<!-- Date Picker -->
	<link rel="stylesheet" href="/OnlineTicketBooking/css/bootstrap-datepicker.min.css">
	<!-- CS Select -->
	<link rel="stylesheet" href="/OnlineTicketBooking/css/cs-select.css">
	<link rel="stylesheet" href="/OnlineTicketBooking/css/cs-skin-border.css">
	
	<link rel="stylesheet" href="/OnlineTicketBooking/css/style.css">
</head>
<body>

<%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>

<header id="fh5co-header-section" class="sticky-banner">
			<div class="container">
				<div class="nav-header">
					<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
					<h1 id="fh5co-logo"><a href="<%=OTBView.WELCOME_CTL%>">Online Ticket Booking</a></h1>
					<!-- START #fh5co-menu-wrap -->
					<nav id="fh5co-menu-wrap" role="navigation">
						<ul class="sf-menu" id="fh5co-primary-menu">
							<li class="active"><a href="<%=OTBView.WELCOME_CTL%>">Home</a></li>
							
							
							<%if(userLoggedIn){ %>
							
							<%if(userBean.getRoleId()==1){%>
							<li><a href="<%=OTBView.MOVIE_CTL%>">Add Movie</a></li>
							<li><a href="<%=OTBView.MOVIE_LIST_CTL%>">Movies</a></li>
							<li><a href="<%=OTBView.BOOK_LIST_CTL%>">Book List</a></li>
							
							<%}else if(userBean.getRoleId()==2){ %>
							
							<li><a href="<%=OTBView.MOVIE_LIST_CTL%>">Movie List</a></li>
							<li><a href="<%=OTBView.BOOK_LIST_CTL%>">Book List</a></li>
							
							<%} %>
							
							<li>
								<a href="" class="fh5co-sub-ddown"><%=welcomeMsg%></a>
								<ul class="fh5co-sub-menu">
									<li><a href="<%=OTBView.MY_PROFILE_CTL%>">My Profile</a></li>
									<li><a href="<%=OTBView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
									<li><a href="<%=OTBView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">LogOut</a></li>
									
								</ul>
							</li>
							
							<%}else{ %>
							<li><a href="<%=OTBView.LOGIN_CTL%>">Sign In</a></li>
							<li><a href="<%=OTBView.USER_REGISTRATION_CTL%>">Sign Up</a></li>
							<li><a href=""><%=welcomeMsg%></a></li>
							<%} %>
						</ul>
					</nav>
				</div>
			</div>
		</header>
</body>
</html>