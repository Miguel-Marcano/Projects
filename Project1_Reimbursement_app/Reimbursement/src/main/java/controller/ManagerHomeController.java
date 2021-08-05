package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerHomeController {

	public static void manhome(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = "/resources/html/man-home.html";
		
		req.getRequestDispatcher(myPath).forward(req, res);
	}
	
	public static void manstatus(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String myPath = "/resources/html/man-status.html";
		
		req.getRequestDispatcher(myPath).forward(req, res);
	}
	
	
}
