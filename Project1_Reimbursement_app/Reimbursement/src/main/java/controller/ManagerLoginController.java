package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.EmpReimbursement;
import services.UserMethodsService;
import services.UserMethodsServiceImpl;

public class ManagerLoginController {

	static { 
	      try {
	          Class.forName("org.postgresql.Driver");
	      }catch(ClassNotFoundException e) {
	          e.printStackTrace();
	          System.out.println("Static block has failed me");
	      }
	}
	
	public static void manLogin(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = null;
		UserMethodsService methodsService = new UserMethodsServiceImpl();
		List<String> checker = new ArrayList<>();
		
		if(!req.getMethod().equals("POST")){
			myPath = "/index.html";
			req.getRequestDispatcher(myPath).forward(req, res);
			return;
		}
		
		String username = req.getParameter("manusername");
		String password = req.getParameter("manpassword");
		
		System.out.println(username);
		
		checker = methodsService.checkEmp(username, password, "manager");
		System.out.println(checker);
	
		if(checker.isEmpty()) {
			myPath = "/employee/incorrectCredentials";
			req.getRequestDispatcher(myPath).forward(req, res);
			return;
		}else {
		
			req.getSession().setAttribute("managerLoggedInUsername", username);
			req.getSession().setAttribute("managerLoggedInPassword", password);
		
			myPath = "/manager/home";
			req.getRequestDispatcher(myPath).forward(req, res);
			return;
		}
	}
	
	public static void alltickets(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		UserMethodsService methodsService = new UserMethodsServiceImpl();
		List<EmpReimbursement> allchecker = new ArrayList<>();
		
		allchecker = methodsService.viewReim();
		System.out.println(allchecker);
		System.out.println(req.getRequestURI());
		
		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(allchecker));
	}
	
	public static void statusfilter(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		UserMethodsService methodsService = new UserMethodsServiceImpl();
		List<EmpReimbursement> status = new ArrayList<>();
		
		String type = req.getParameter("manstatus");
		System.out.println(type);
		int num_type = Integer.parseInt(type);
		
		status = methodsService.filterStatus(num_type);
		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(status));
		
		String myPath = "/manager/home";
	}
	
	public static void updateStatus(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		UserMethodsService methodsService = new UserMethodsServiceImpl();
		
		String id = req.getParameter("reimId");
		String status = req.getParameter("choose");
		
		int num_id = Integer.parseInt(id);
		int num_status = Integer.parseInt(status);
		
		System.out.println(num_id);
		System.out.println(status);
		
		HttpSession session = req.getSession();
		String manUsername = (String) session.getAttribute("managerLoggedInUsername");
		String manPassword = (String) session.getAttribute("managerLoggedInPassword");
		
		int manid = methodsService.getId(manUsername, manPassword);
		System.out.println(manid);
		
		methodsService.approveReim(num_id, num_status, manid);
		
		String myPath = "/manager/home";
		req.getRequestDispatcher(myPath).forward(req, res);
		return;
	}
}
