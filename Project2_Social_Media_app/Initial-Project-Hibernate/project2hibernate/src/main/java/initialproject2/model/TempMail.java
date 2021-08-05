package initialproject2.model;

public class TempMail {
	private String userName;
	private String passWord;
	private String resetToken;
	public TempMail(String userName, String passWord, String resetToken) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.resetToken = resetToken;
	}
	public TempMail() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	@Override
	public String toString() {
		return "TempMail [userName=" + userName + ", passWord=" + passWord + ", resetToken=" + resetToken + "]";
	}
	
	
	

}
