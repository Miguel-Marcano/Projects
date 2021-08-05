package initialproject2.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="project2_users")
public class User {
	
	@Id //means primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_username", unique=true, nullable=false)
	private String userName;
	
	@Column(name="user_password", unique=true, nullable=false)
	private String passWord;
	
	@Column(name="user_first_name")
	private String firstName;
	
	@Column(name="user_last_name")
	private String lastName;
	
	@Column(name="user_email", unique=true, nullable = false)
	private String email;
	
	@Column(name="user_picture")
	private String picture;
	
	@Column(name="user_background")
	private String background;
		
	@JsonIgnore
	@Column(name="user_reset_token")
	private String reset_token;
	
	@OneToMany(mappedBy = "postUser", fetch=FetchType.EAGER)
	private List<Post> posts = new ArrayList<>();
	

	public User() {

	}

	
	public User(String userName, String passWord, String reset_token) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.reset_token = reset_token;
	}


	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	public User(String email) {
		super();
		this.email = email;
	}

	public User(int userId, String userName, String passWord, String firstName, String lastName, String email,
			String picture, String background, List<Post> posts) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.picture = picture;
		this.background = background;
		this.posts = posts;
	}

	public User(int userId) {
		super();
		this.userId = userId;
	}


	public User(String userName, String passWord, String firstName, String lastName, String email, String picture,
			String background, List<Post> posts) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.picture = picture;
		this.background = background;
		this.posts = posts;
	}

	public User(int userId, String userName, String passWord, String firstName, String lastName, String email,
			String picture, String background, String reset_token, List<Post> posts) {

		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.picture = picture;
		this.background = background;
		this.reset_token = reset_token;
		this.posts = posts;
	}

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getBackground() {
		return background;
	}


	public void setBackground(String background) {
		this.background = background;
	}


	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String getReset_token() {
		return reset_token;
	}
	
	public void setReset_token(String reset_token) {
		this.reset_token = reset_token;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", picture=" + picture + ", background="
				+ background + ", reset_token=" + reset_token + ", posts=" + posts + "]";
	}

	

	

	
	
	
	
	
}
