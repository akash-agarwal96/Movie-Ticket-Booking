package in.co.online.ticket.controller;

public interface OTBView {
	
	public String APP_CONTEXT = "/OnlineTicketBooking";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	public String MOVIE_VIEW = PAGE_FOLDER + "/MovieView.jsp";	
	public String MOVIE_LIST_VIEW = PAGE_FOLDER + "/MovieListView.jsp";
	
	public String BOOK_VIEW = PAGE_FOLDER + "/BookView.jsp";	
	public String BOOK_LIST_VIEW = PAGE_FOLDER + "/BookListView.jsp";
	
	
	public String PAYMENT_VIEW = PAGE_FOLDER + "/PaymentView.jsp";
		
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	public String MOVIE_CTL = APP_CONTEXT + "/ctl/MovieCtl";
	public String MOVIE_LIST_CTL = APP_CONTEXT + "/ctl/MovieListCtl";
	
	public String BOOK_CTL = APP_CONTEXT + "/ctl/BookCtl";
	public String BOOK_LIST_CTL = APP_CONTEXT + "/ctl/BookListCtl";
	
	
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";



}
