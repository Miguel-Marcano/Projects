package initialproject2.model;

public class TempPost {
	private String content;
	private int userId;
	private String picture;

	
	public TempPost(String content, int userId, String picture) {
		super();
		this.content = content;
		this.userId = userId;
		this.picture = picture;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public TempPost(String content, int userId) {
		super();
		this.content = content;
		this.userId = userId;
	}

	public TempPost() {
		// TODO Auto-generated constructor stub
	}
}
