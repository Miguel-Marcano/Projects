package initialproject2.service;

import java.util.List;

import initialproject2.model.Post;
import initialproject2.model.User;

public interface PostService {
	
	public void addPost(Post post);	
	
	public List<Post> getAllPosts();
	
	public void update(Post post);
	
	public void delete(Post post);
	
	public Post selectById(int id);
	
	public List<Post> selectByUser(User user);

}
