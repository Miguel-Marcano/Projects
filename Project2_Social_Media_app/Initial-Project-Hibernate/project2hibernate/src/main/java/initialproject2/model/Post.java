package initialproject2.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import initialproject2.helper.MiniUser;


@Entity
@Table(name="project2_posts")
public class Post {
	
	@Id //means primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id")
	private int postId;
	
	//note: there are MORE attributes for these annotations, i'm just showing a handful
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date timePosted;
	
	@Column(name="post_content")
	private String postContent;
	
	@Column(name="post_picture")
	private String postPicture;
	
	@Column(name="post_likes", columnDefinition = "int default 0")
	private Integer postLikes;
	
	@Column(name="post_dislikes")
	private Integer postDislikes;
		
	//Foreign KEY and access to the User the post is connected to
	@ManyToOne (cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="user_FK")
	private User postUser;
	
	public Post() {
		// TODO Auto-generated constructor stub
	}

	public Post(String postContent) {
		// TODO Auto-generated constructor stub
		super();
		this.postContent = postContent;
	}
	
	public Post(String postContent, User postUser) {
		super();
		this.postContent = postContent;
		this.postUser = postUser;
	}
	

	public Post(int postId, Date timePosted, String postContent, String postPicture, Integer postLikes,
			Integer postDislikes, User postUser) {
		super();
		this.postId = postId;
		this.timePosted = timePosted;
		this.postContent = postContent;
		this.postPicture = postPicture;
		this.postLikes = postLikes;
		this.postDislikes = postDislikes;
		this.postUser = postUser;
	}

	public Post(int postId, Date timePosted, String postContent, Integer postLikes, Integer postDislikes,
			User postUser) {
		super();
		this.postId = postId;
		this.timePosted = timePosted;
		this.postContent = postContent;
		this.postLikes = postLikes;
		this.postDislikes = postDislikes;
		this.postUser = postUser;
	}
	
	public Post(Date timePosted, String postContent, Integer postLikes, Integer postDislikes,
			User postUser) {
		super();
		this.timePosted = timePosted;
		this.postContent = postContent;
		this.postLikes = postLikes;
		this.postDislikes = postDislikes;
		this.postUser = postUser;
	}
	
	public Post(Date timePosted, String postContent, Integer postLikes, Integer postDislikes) {
		super();
		this.timePosted = timePosted;
		this.postContent = postContent;
		this.postLikes = postLikes;
		this.postDislikes = postDislikes;
	}
	

	public Post(Date timePosted, String postContent, String postPicture, Integer postLikes, Integer postDislikes,
			User postUser) {
		super();
		this.timePosted = timePosted;
		this.postContent = postContent;
		this.postPicture = postPicture;
		this.postLikes = postLikes;
		this.postDislikes = postDislikes;
		this.postUser = postUser;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTimePosted() {
		return timePosted.toString().substring(0, 16);
	}

	public void setTimePosted(Date timePosted) {
		this.timePosted = timePosted;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Integer getPostLikes() {
		return postLikes;
	}

	public void setPostLikes(Integer postLikes) {
		this.postLikes = postLikes;
	}

	public Integer getPostDislikes() {
		return postDislikes;
	}

	public void setPostDislikes(Integer postDislikes) {
		this.postDislikes = postDislikes;
	}

	public String getPostPicture() {
		return postPicture;
	}

	public void setPostPicture(String postPicture) {
		this.postPicture = postPicture;
	}

	public MiniUser getPostUser() {
		return new MiniUser(postUser.getUserName(), postUser.getPicture());
	}

	public void setPostUser(User postUser) {
		this.postUser = postUser;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", timePosted=" + timePosted + ", postContent=" + postContent
				+ ", postPicture=" + postPicture + ", postLikes=" + postLikes + ", postDislikes=" + postDislikes
				+ ", postUser=" + "]";
	}
	
	
	
	
	
	
}
