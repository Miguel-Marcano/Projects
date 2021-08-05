package services;

import java.util.List;

import dao.UserMethodsDao;
import dao.UserMethodsDaoImpl;
import model.EmpReimbursement;
import model.Manager;
import model.User;

public class UserMethodsServiceImpl implements UserMethodsService{
	
	private UserMethodsDao usemetdao = new UserMethodsDaoImpl();

	@Override
	public List<String> checkEmp(String username, String pass, String type) {
		return usemetdao.checkEmp(username, pass, type);
	}

//	@Override
//	public List<String> checkMan(String username, String pass, String type) {
//		return usemetdao.checkMan(username, pass, type);
//	}

	@Override
	public User currentUser(String username, String pass, String type) {
		return usemetdao.currentUser(username, pass, type);
	}

	@Override
	public Manager currentManager(String username, String pass, String type) {
		return usemetdao.currentManager(username, pass, type);
	}

	@Override
	public List<EmpReimbursement> viewTickets(int us) {
		return usemetdao.viewTickets(us);
	}

	@Override
	public boolean addReimRequest(int amount, String description, int us, int type) {
		return usemetdao.addReimRequest(amount, description, us, type);
	}

	@Override
	public List<EmpReimbursement> viewReim() {
		return usemetdao.viewReim();
	}

	@Override
	public boolean approveReim(int id, int type, int manId) {
		return usemetdao.approveReim(id, type, manId);
	}

	@Override
	public List<EmpReimbursement> filterStatus(int id) {
		return usemetdao.filterStatus(id);
	}

	@Override
	public int getId(String empusername, String emppassword) {
		return usemetdao.getId(empusername, emppassword);
	}
	
}
	

