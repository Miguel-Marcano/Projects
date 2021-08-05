package initialproject2.dao;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import initialproject2.model.User;


@Transactional
@Repository("userDao")
public class UserDaoImpl implements UserDao{
	
	private SessionFactory sesFact;

	final static Logger loggy = Logger.getLogger(UserDaoImpl.class);
	
	{
		loggy.setLevel(Level.ALL);
	}
	
	@Autowired
	public UserDaoImpl(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

	@Override
	public void insert(User user) {

		sesFact.getCurrentSession().save(user);
		loggy.info("We've created a new user");
	}

	@Override
	public void update(User user) {
		
		sesFact.getCurrentSession().update(user);
		loggy.info("We've updated the user information");
	}

	@Override
	public void delete(User user) {
		sesFact.getCurrentSession().delete(user);
		loggy.info("We've deleted a user");
	}

	@Override
	public User selectByID(int id) {	
		User myUser = sesFact.getCurrentSession().get(User.class, id);
				
		//BoilerPlate session end
		//ses.close();
		loggy.info("We've selected a user by the ID");
		return myUser;
	}

	@Override
	public List<User> selectAll() {

		List<User> myUserList = 
				sesFact.getCurrentSession().createQuery("from User", User.class).list();
		loggy.info("We've selected all the users");	
		return myUserList;
	}

	//GET USER THROUGH USERNAME FOR SAKE OF USER on Log in page
	@Override
	public User selectByUserName(String username) {
				
		List<User> myUserList = sesFact.getCurrentSession().createQuery("from User where user_username = ?1", User.class).setParameter(1, username).list();
				
		loggy.info("We've selected all the users by they username");
		return myUserList.get(0);
	}

	//LOGIN CHECK
	@Override
	public Boolean userExists(String username, String password) {
		
		Boolean bool = (Long) sesFact.getCurrentSession()
				.createQuery("SELECT count(*) from User where user_username = ?1 AND user_password = ?2")
				.setParameter(1, username)
				.setParameter(2,password)
				.uniqueResult() > 0;
		loggy.info("We've checked if the user exists");
		return bool;
	}

	@Override
	public Boolean emailExists(String email) {
		
		Boolean bool = (Long) sesFact.getCurrentSession()
				.createQuery("SELECT count(*) from User where user_email = ?1")
				.setParameter(1, email)
				.uniqueResult() > 0;
		loggy.info("We've checked if the user email exists");
		return bool;
	}

	@Override
	public User selectByEmail(String email) {
		List<User> myUserList = sesFact.getCurrentSession().createQuery("from User where user_email = ?1", User.class).setParameter(1, email).list();
		
		loggy.info("We've selected a user by the email");
		return myUserList.get(0);
	}
	
}
