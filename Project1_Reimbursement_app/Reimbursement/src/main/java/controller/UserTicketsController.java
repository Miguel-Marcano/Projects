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

public class UserTicketsController {
	
	public static void tickets(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		UserMethodsService methodsService = new UserMethodsServiceImpl();
	
		List<EmpReimbursement> checker = new ArrayList<>();
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("loggedInUsername");
		String password = (String) session.getAttribute("loggedInPassword");
		int id = methodsService.getId(username, password);
		
		checker = methodsService.viewTickets(id);
		System.out.println(checker);
		System.out.println(req.getRequestURI());
		
		PrintWriter printer = res.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(checker));
		
	}
	
	public static void ticketsScreen(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = "/resources/html/emp-table.html";
		
		req.getRequestDispatcher(myPath).forward(req, res);
	}
	
}
