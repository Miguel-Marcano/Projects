package initialproject2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import initialproject2.dao.PostDao;
import initialproject2.dao.PostDaoImpl;
import initialproject2.model.Post;
import initialproject2.model.User;


@Service("postServ")
public class PostServiceImpl implements PostService {
	
	private PostDao pdi;
	

	@Override
	public void addPost(Post post) {
		pdi.insert(post);

	}

	@Override
	public List<Post> getAllPosts() {

		return pdi.selectAll();
	}
	
	public PostServiceImpl() {
	}

	@Autowired
	public PostServiceImpl(PostDao pdi) {
		super();
		this.pdi = pdi;
	}

	public PostDao getPdi() {
		return pdi;
	}

	public void setPdi(PostDao pdi) {
		this.pdi = pdi;
	}

	@Override
	public void update(Post post) {
		pdi.update(post);
		
	}

	@Override
	public void delete(Post post) {
		pdi.delete(post);
		
	}

	@Override
	public Post selectById(int id) {
		// TODO Auto-generated method stub
		return pdi.selectById(id);
	}

	@Override
	public List<Post> selectByUser(User user) {
		return pdi.selectByUser(user);
	}
	
	

}
