package services;

import java.util.List;

import model.EmpReimbursement;
import model.Manager;
import model.User;

public interface UserMethodsService {
	public List<String> checkEmp(String username, String pass, String type);
	//public List<String> checkMan(String username, String pass, String type);
	public User currentUser(String username, String pass, String type);
	public Manager currentManager(String username, String pass, String type);
	public List<EmpReimbursement> viewTickets(int us);
	public boolean addReimRequest(int amount, String description, int us, int type);
	public List<EmpReimbursement> viewReim();
	public boolean approveReim(int id, int type, int manId);
	public List<EmpReimbursement> filterStatus(int id);
	public int getId(String empusername, String emppassword);
}
