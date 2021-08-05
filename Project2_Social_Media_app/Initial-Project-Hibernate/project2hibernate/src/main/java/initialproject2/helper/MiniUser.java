package initialproject2.helper;

public class MiniUser {
	private String userName;
	private String picture;
	
	public MiniUser() {
		// TODO Auto-generated constructor stub
	}

	public MiniUser(String userName, String picture) {
		super();
		this.userName = userName;
		this.picture = picture;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	

}
