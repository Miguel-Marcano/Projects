package model;

public class User {
	private String uUsername, uPassword, uFirst_name, uLast_name, uEmail;
	private int uId_seri, uId;

	public User(int id_serial, String username, String password, String first_name, String last_name, String email, int id) {
		super();
		uId_seri = id_serial;
		uUsername = username;
		uPassword = password;
		uFirst_name = first_name;
		uLast_name = last_name;
		uEmail = email;
		uId = id;
	}
	
	public int getuId_seri() {
		return uId_seri;
	}

	public void setuId_seri(int uId_seri) {
		this.uId_seri = uId_seri;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getuUsername() {
		return uUsername;
	}

	public void setuUsername(String uUsername) {
		this.uUsername = uUsername;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuFirst_name() {
		return uFirst_name;
	}

	public void setuFirst_name(String uFirst_name) {
		this.uFirst_name = uFirst_name;
	}

	public String getuLast_name() {
		return uLast_name;
	}

	public void setuLast_name(String uLast_name) {
		this.uLast_name = uLast_name;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
}
