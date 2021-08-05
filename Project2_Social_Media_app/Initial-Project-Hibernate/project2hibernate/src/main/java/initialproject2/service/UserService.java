package initialproject2.service;

import java.util.List;

import initialproject2.model.User;

public interface UserService {
	public void addUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUsingId(int id);
	
	public User getUserByUserName(String username);
	
	public User getUserByEmail(String email);
	
	public Boolean userLoginCheck(String username, String password);
	
	public void update(User user);
	
	public Boolean emailExists(String email);

}
