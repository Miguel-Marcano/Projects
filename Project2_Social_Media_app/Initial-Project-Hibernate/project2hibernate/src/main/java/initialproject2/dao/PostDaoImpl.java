package initialproject2.dao;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import initialproject2.model.Post;
import initialproject2.model.User;

@Transactional
@Repository("postDao")
public class PostDaoImpl implements PostDao {
	
	private SessionFactory sesFact;
	
	final static Logger loggy = Logger.getLogger(PostDaoImpl.class);
	
	{
		loggy.setLevel(Level.ALL);
	}
	
	
	@Autowired
	public PostDaoImpl(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

	@Override
	public void insert(Post post) {

		sesFact.getCurrentSession().save(post);
		loggy.info("We've inserted a new post");

	}

	@Override
	public void update(Post post) {

		sesFact.getCurrentSession().update(post);
		loggy.info("We've updated a post");
	}
	
	@Override
	public Post selectById(int id) {

		loggy.info("We've selected a post by his ID");
		return sesFact.getCurrentSession().get(Post.class, id);
		
	}

	@Override
	public void delete(Post post) {

		sesFact.getCurrentSession().delete(post);		
		loggy.info("We've deleted a post");
	}

	@Override
	public List<Post> selectAll() {


		List<Post> myPostList = 
				sesFact.getCurrentSession().createQuery("from Post", Post.class).list();
				
		//BoilerPlate session end
		//ses.close();
		loggy.info("We've selected all the post from the DB");
		return myPostList;
	}

	@Override
	public List<Post> selectByUser(User user) {
		
		List<Post> myPostList = 
				sesFact.getCurrentSession().createQuery("from Post Where postUser = ?1", Post.class).setParameter(1, user).list();
		loggy.info("We've selected all the post from the DB using the user as parameter");
		return myPostList;
	}

}
