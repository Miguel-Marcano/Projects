package initialproject2.dao;

import java.util.List;

import initialproject2.model.User;

public interface UserDao {
	
	public void insert(User user);
	public void update(User user);
	public void delete(User user);
	
	public User selectByID(int id);
	public User selectByUserName(String username);
	public User selectByEmail(String email);
	public List<User> selectAll();
	public Boolean userExists(String username, String password);
	public Boolean emailExists(String email);

}
