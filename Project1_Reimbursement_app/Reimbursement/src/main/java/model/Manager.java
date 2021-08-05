package model;

public class Manager {
	private String mUsername, mPassword, mFirst_name, mLast_name, mEmail;
	private int mId_seri, mId;
	
	public Manager(int id_serial, String username, String password, String first_name, String last_name, String email, int id) {
		super();
		mId_seri = id_serial;
		mUsername = username;
		mPassword = password;
		mFirst_name = first_name;
		mLast_name = last_name;
		mEmail = email;
		mId = id;
	}

	public int getmId_seri() {
		return mId_seri;
	}

	public void setmId_seri(int mId_seri) {
		this.mId_seri = mId_seri;
	}

	public String getmUsername() {
		return mUsername;
	}

	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}

	public String getmPassword() {
		return mPassword;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public String getmFirst_name() {
		return mFirst_name;
	}

	public void setmFirst_name(String mFirst_name) {
		this.mFirst_name = mFirst_name;
	}

	public String getmLast_name() {
		return mLast_name;
	}

	public void setmLast_name(String mLast_name) {
		this.mLast_name = mLast_name;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}
}
