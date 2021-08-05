package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.UserMethodsService;
import services.UserMethodsServiceImpl;

public class FormController {

	public static void form(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = null;
		UserMethodsService methodsService = new UserMethodsServiceImpl();
		
		String amount = req.getParameter("empamount");
        String description = req.getParameter("empdescription");
        String type = req.getParameter("emptype");
		System.out.println(amount);
		System.out.println(description);
		System.out.println(type);
		System.out.println("inside the form"); 
		
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("loggedInUsername");
		String password = (String) session.getAttribute("loggedInPassword");
		
		int id = methodsService.getId(username, password);
		
		int amou = Integer.parseInt(amount);
		int typeint = Integer.parseInt(type);
		
		methodsService.addReimRequest(amou, description, id, typeint);
		
		myPath = "/employee/form";
		
		req.getRequestDispatcher(myPath).forward(req, res);
	}
	
	public static void formScreen(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = "/resources/html/emp-form.html";
		
		req.getRequestDispatcher(myPath).forward(req, res);
	}
}
