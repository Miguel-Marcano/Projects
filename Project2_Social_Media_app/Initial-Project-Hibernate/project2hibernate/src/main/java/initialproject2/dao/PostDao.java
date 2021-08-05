package initialproject2.dao;

import java.util.List;

import initialproject2.model.Post;
import initialproject2.model.User;

public interface PostDao {
	
	public void insert(Post post);


	public void update(Post post);


	public void delete(Post post);

	public List<Post> selectAll();
	
	public Post selectById(int id);
	
	public List<Post> selectByUser(User user);

}
