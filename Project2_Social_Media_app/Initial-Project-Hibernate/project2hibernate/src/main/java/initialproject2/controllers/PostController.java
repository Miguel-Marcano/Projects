package initialproject2.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import initialproject2.model.Post;
import initialproject2.model.TempPost;
import initialproject2.model.User;
import initialproject2.service.PostService;
import initialproject2.service.UserService;

@RestController
@RequestMapping("/postcontrol")
@CrossOrigin("*")
public class PostController {
	
	private PostService psi;
	private UserService usi;
	
	@Autowired
	public PostController(PostService psi, UserService usi) {
		super();
		this.psi = psi;
		this.usi = usi;
	}


	
	@PutMapping(value = "/addpost")
	public void addPost(HttpSession session,@RequestBody TempPost tempPost) {
		User user = (User) session.getAttribute("LoggedinUser");
		Post post = new Post(tempPost.getContent());
		post.setPostUser(usi.getUsingId(tempPost.getUserId()));
//		post.setPostUser(user.getUserId());
		System.out.println(tempPost.getContent());
		System.out.println(tempPost.getUserId());
		post.setPostLikes(0);
		post.setPostPicture(tempPost.getPicture());
		psi.addPost(post);
	}
	
	@GetMapping(value = "/getallposts")
	public @ResponseBody List<Post> getAllPosts(){
		return psi.getAllPosts();
	}
	
	@PostMapping(value = "/updatepost")
	public void update(@RequestBody Post post) {
		System.out.println("IN update Post");
		System.out.println(post);
		Post updatePost = psi.selectById(post.getPostId());
		updatePost.setPostLikes(post.getPostLikes());
		psi.update(updatePost);
	}
	
	@DeleteMapping(value = "/deletepost")
	public void delete(@RequestBody Post post) {
		psi.delete(post);
	}
	
	@GetMapping(value = "/postbyid")
	public @ResponseBody Post selectById(@RequestBody int id) {
		return psi.selectById(id);
	}
	
	@GetMapping(value = "/userposts")
	public @ResponseBody List<Post> selectByUser(@RequestBody User user) {
		return psi.selectByUser(user);
	}

}
