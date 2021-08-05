package initialproject2.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import initialproject2.Mail.JavaMailUtil;
import initialproject2.helper.TokenGenerator;
import initialproject2.model.Mail;
import initialproject2.model.User;
import initialproject2.service.UserService;
import initialproject2.service.UserServiceImpl;

@RestController
@RequestMapping("/logincontrol")
@CrossOrigin("*")
public class LoginController {
	
	private UserService usi;
	private JavaMailUtil jmu;
	
	@Autowired
	public LoginController(UserService usi, JavaMailUtil jmu) {
		super();
		this.usi = usi;
		this.jmu = jmu;
	}


	@PostMapping(value= "/login")
	public @ResponseBody User login(HttpSession session, @RequestBody User user) {
		if(usi.userLoginCheck(user.getUserName(), user.getPassWord()))
		{
			User fullUser = usi.getUserByUserName(user.getUserName());
			session.setAttribute("LoggedinUser", fullUser);
			System.out.println( session.getAttribute("LoggedinUser"));
			return fullUser;
		}
		return new User();
	}
	
	@GetMapping(value = "/getLoggedUser")
	public @ResponseBody User getLoggedInUser(HttpSession session) {
		User user = (User) session.getAttribute("LoggedinUser");
		if(user == null)
			return new User();
		return user;
	}
	
	
	@DeleteMapping(value = "/logout")
	public void logout(HttpServletRequest myReq) {
		
		HttpSession session = myReq.getSession(false);
		
		if(session!=null) {
				session.invalidate();
		}
		
	}
	
	@PostMapping(value = "/recoverPassword")
	public void recoverPassword(HttpSession session, @RequestBody Mail email) throws Exception {
		System.out.println(email);
		User user = usi.getUserByEmail(email.getEmail());
		String token = TokenGenerator.getAlphaNumericString();
		user.setReset_token(token);
		usi.update(user);
		jmu.RecoverEmail(email, token);
	}
	

}
