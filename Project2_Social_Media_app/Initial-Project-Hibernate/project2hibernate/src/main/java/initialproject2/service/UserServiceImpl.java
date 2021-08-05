package initialproject2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import initialproject2.dao.UserDao;
import initialproject2.dao.UserDaoImpl;
import initialproject2.model.User;


@Service("userServ")
public class UserServiceImpl implements UserService {
	
	UserDao udi;
	
	
	@Autowired
	public UserServiceImpl(UserDao udi) {
		super();
		this.udi = udi;
	}
	
	

	public UserDao getUdi() {
		return udi;
	}



	public void setUdi(UserDao udi) {
		this.udi = udi;
	}



	@Override
	public void addUser(User user) {
		udi.insert(user);
	}

	@Override
	public List<User> getAllUsers() {
		return udi.selectAll();
	}

	@Override
	public User getUsingId(int id) {
		// TODO Auto-generated method stub
		return udi.selectByID(id);
	}

	@Override
	public User getUserByUserName(String username) {
		
		return udi.selectByUserName(username);
	}

	@Override
	public Boolean userLoginCheck(String username, String password) {
		// TODO Auto-generated method stub
		return udi.userExists(username, password);
	}



	@Override
	public void update(User user) {
		udi.update(user);
		
	}



	@Override
	public Boolean emailExists(String email) {
		return udi.emailExists(email);
	}



	@Override
	public User getUserByEmail(String email) {
		return udi.selectByEmail(email);
	}

}
