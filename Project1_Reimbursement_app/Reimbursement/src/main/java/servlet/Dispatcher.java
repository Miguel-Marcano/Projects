package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import controller.FormController;
import controller.HomeController;
import controller.LoginController;
import controller.ManagerHomeController;
import controller.ManagerLoginController;
import controller.UserTicketsController;


public class Dispatcher {
	
	public static void myVirtualRouter(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException, ServletException {
		
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/Reimbursement/login":
			System.out.println("case 1");
			break;
		case "/Reimbursement/employee":
			System.out.println("Case 2");
			LoginController.login(req, res);
			break;
		case "/Reimbursement/employee/home":
			System.out.println("case 3");
			HomeController.home(req, res);
			break;
		case "/Reimbursement/employee/form":
			System.out.println("case 4");
			FormController.formScreen(req, res);
			//FormController.form(req, res);
			break;
		case "/Reimbursement/employee/tickets":
			System.out.println("Case 5");
			UserTicketsController.ticketsScreen(req, res);
			break;
		case "/Reimbursement/employee/tickets/json":
			System.out.println("Case 6");
			UserTicketsController.tickets(req, res);
			break;
		case "/Reimbursement/manager":
			System.out.println("Case 7");
			ManagerLoginController.manLogin(req, res);
			break;
		case "/Reimbursement/manager/home":
			System.out.println("Case 8");
			ManagerHomeController.manhome(req, res);
			break;
		case "/Reimbursement/manager/home/json":
			System.out.println("Case 9");
			ManagerLoginController.alltickets(req, res);
			break;
//		case "/Reimbursement/manager/json/status":
//			System.out.println("Case 10");
//			ManagerHomeController.manstatus(req, res);
//			ManagerLoginController.statusfilter(req, res);
//			break;
//		case "/Reimbursement/manager/json/status/json":
//			System.out.println("Case 11");
//			ManagerLoginController.statusfilter(req, res);
//			break;
		case "/Reimbursement/manager/choose":
			System.out.println("Case 10");
			ManagerLoginController.updateStatus(req, res);
			break;
		case "/Reimbursement/employee/form/task":
			System.out.println("Case 11");
			FormController.form(req, res);
			break;
		default:
			System.out.println("Dude, you gave me a bad URI.");
			req.getRequestDispatcher("/resources/html/fail.html").forward(req, res);
			return;
		}
	}
}
