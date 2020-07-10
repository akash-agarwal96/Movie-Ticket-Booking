package in.co.online.ticket.bean;

import java.util.Date;

public class BookBean extends BaseBean {
	
	private long userId;
	private String userName;
	private long movieId;
	private String movieName;
	private Date bookDate;
	private String showTime;
	private long noOfPerson;
	private long FinalAmount;
	private String emalid;
	private String mobileNo;
	
	
	
	
	
	

	public String getEmalid() {
		return emalid;
	}

	public void setEmalid(String emalid) {
		this.emalid = emalid;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public long getNoOfPerson() {
		return noOfPerson;
	}

	public void setNoOfPerson(long noOfPerson) {
		this.noOfPerson = noOfPerson;
	}

	public long getFinalAmount() {
		return FinalAmount;
	}

	public void setFinalAmount(long finalAmount) {
		FinalAmount = finalAmount;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
