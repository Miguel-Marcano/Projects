package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import services.UserMethodsService;
import services.UserMethodsServiceImpl;



public class LoginController {
	
	static { 
	      try {
	          Class.forName("org.postgresql.Driver");
	      }catch(ClassNotFoundException e) {
	          e.printStackTrace();
	          System.out.println("Static block has failed me");
	      }
	}
	
	public static void login(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = null;
		UserMethodsService methodsService = new UserMethodsServiceImpl();
		List<String> checker = new ArrayList<>();
		
		if(!req.getMethod().equals("POST")){
			myPath = "/index.html";
			req.getRequestDispatcher(myPath).forward(req, res);
			return;
		}
		
		String username = req.getParameter("empusername");
		String password = req.getParameter("emppassword");
		
		System.out.println(username);
		
		checker = methodsService.checkEmp(username, password, "employee");
		System.out.println(checker);
		
		if(checker.isEmpty()) {
			myPath = "/employee/incorrectCredentials";
			req.getRequestDispatcher(myPath).forward(req, res);
			return;
		}else {
			
			req.getSession().setAttribute("loggedInUsername", username);
			req.getSession().setAttribute("loggedInPassword", password);
			
			myPath = "/employee/home";
			req.getRequestDispatcher(myPath).forward(req, res);
			return;
		}
	}
}
