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

import initialproject2.helper.MiniUser;
import initialproject2.helper.TokenGenerator;
import initialproject2.model.Post;
import initialproject2.model.TempMail;
import initialproject2.model.TempPost;
import initialproject2.model.User;
import initialproject2.service.UserService;

@RestController
@RequestMapping("/usercontrol")
@CrossOrigin("*")
public class UserController {
	
	private UserService usi;
	
	@Autowired
	public UserController(UserService usi) {
		super();
		this.usi = usi;
	}
	
	@PostMapping(value= "/updateuser")
	public void updateUser(@RequestBody User user) {
		usi.update(user);
	}
	
	
	@PutMapping(value = "/newuser")
    public @ResponseBody User addUser(HttpSession session, @RequestBody User user) {
		user.setReset_token(TokenGenerator.getAlphaNumericString());
        usi.addUser(user);
        session.setAttribute("LoggedinUser", user);
        return user;
    }
	
	@GetMapping(value = "/getusers")
	public @ResponseBody List<User> getAllUsers(){
		//System.out.println(usi.getAllUsers());
		return usi.getAllUsers();
	}
	
	@PostMapping(value = "/getposts")
	public @ResponseBody List<Post> getUserPosts(@RequestBody TempPost user){
		User tempuser = usi.getUsingId(user.getUserId());
		//System.out.println(usi.getAllUsers());
		return tempuser.getPosts();
	}
	
	@PostMapping(value = "/getuserbyusername")
	public @ResponseBody User getUserByUserName(@RequestBody MiniUser user){
		//System.out.println(usi.getAllUsers());
		return usi.getUserByUserName(user.getUserName());
	}
	
	@PostMapping(value = "/updatePassword")
	public @ResponseBody boolean updatePassword(@RequestBody TempMail user) {
		User fullUser = usi.getUserByUserName(user.getUserName());
		System.out.println(user);
		System.out.println(fullUser);
		if(fullUser.equals(null) || !(fullUser.getReset_token().equals(user.getResetToken())))
		{
			return false;
		}
		fullUser.setPassWord(user.getPassWord());
		usi.update(fullUser);
		return true;
	}
	
	@PostMapping(value ="/checkEmail")
	public @ResponseBody boolean checkEmail(@RequestBody User user) {
		return usi.emailExists(user.getEmail());
	}
	
	
}
