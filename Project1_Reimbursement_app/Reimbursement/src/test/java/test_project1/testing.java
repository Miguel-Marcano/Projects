package test_project1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.UserMethodsDao;
import dao.UserMethodsDaoImpl;
import model.EmpReimbursement;

public class testing {
	public static UserMethodsDao dao = new UserMethodsDaoImpl();
	//EmpReimbursement reim = new EmpReimbursement();
	
	@Test
	public void testCheckEmp() {
	    List<String> expected = Arrays.asList("1234", "mick");
	    List<String> actual = dao.checkEmp("mick", "1234", "employee");

	    assertTrue(expected.equals(actual), "Both arrays are equal");
	}
	
//	@Test
//	public void testviewTickets() {
//	    
//	}
	
	@Test
	public void testaddReimRequest() {
		boolean add = dao.addReimRequest(8900, null, 2, 1);
		assertTrue(add == true, "We add a new reimbursement succesfully");
	}

//	@Test
//	public void testviewReim() {
//		
//	}
	
	@Test
	public void testapproveReim() {
		boolean app = dao.approveReim(7, 3, 1);
		assertTrue(app == true, "We approve/deny a reimbursement succesfully");
	}
	
	@Test
	public void testgetId() {
		int id = dao.getId("mick", "1234");
		
		assertTrue(id == 2, "The Id for this user is correct");
	}
	
	
}
