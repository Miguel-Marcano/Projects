package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import model.EmpReimbursement;
import model.Manager;
import model.User;

public class UserMethodsDaoImpl implements UserMethodsDao {
	
	public String url = "jdbc:postgresql://${DB}/Project_1";
	public String username = "${DB_USERNAME}";
	public String password = "${DB_PASSWORD}";
	
	static { 
	      try {
	          Class.forName("org.postgresql.Driver");
	      }catch(ClassNotFoundException e) {
	          e.printStackTrace();
	          System.out.println("Static block has failed me");
	      }
	}

	
	final static Logger loggy = Logger.getLogger(UserMethodsDaoImpl.class);
	
	{
		loggy.setLevel(Level.ALL);
	}

	@Override
	public List<String> checkEmp(String usernamedb, String pass, String type) {
		List<String> checker = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "SELECT ers_password, ers_username FROM ers_users WHERE ers_username = ? AND ers_password = ? AND user_role_id = (SELECT ers_user_role_id FROM ers_user_roles WHERE user_role = ?);";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usernamedb);
			ps.setString(2, pass);
			ps.setString(3, type);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String returnValue = rs.getString(1);
				String returnValue2 = rs.getString(2);
				checker.add(returnValue);
				checker.add(returnValue2);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
		
		loggy.info("We've check the user or manager profile in the DB");
		//System.out.println("In the dao" + checker);
		return checker;
	}

//	@Override
//	public List<String> checkMan(String username, String pass, String type) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public User currentUser(String usernamedb, String pass, String type) {
		User user = null;
		List<String> checker = new ArrayList<>();
		checker = checkEmp(usernamedb, pass, type);
		
		if (checker.isEmpty()) {
			return null;
		} else {
			try(Connection conn = DriverManager.getConnection(url, username, password)){
				
				String sql = "SELECT * FROM ers_users WHERE ers_password = ? AND ers_username = ?;";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setString(1, checker.get(0));
				ps.setString(2, checker.get(1));
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					int returnValue = rs.getInt(1);
					String returnValue2 = rs.getString(2);
					String returnValue3 = rs.getString(3);
					String returnValue4 = rs.getString(4);
					String returnValue5 = rs.getString(5);
					String returnValue6 = rs.getString(6);
					int returnValue7 = rs.getInt(7);
					User new_user = new User(returnValue, returnValue2, returnValue3, returnValue4, returnValue5, returnValue6, returnValue7);
					user = new_user;
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
				loggy.error("A SQL exception was thrown: ", e);
			}
			loggy.info("We've get the current user from the DB");
			return user;
		}
	}

	@Override
	public Manager currentManager(String usernamedb, String pass, String type) {
		Manager man = null;
		List<String> checker = new ArrayList<>();
		checker = checkEmp(usernamedb, pass, type);
		
		if (checker.isEmpty()) {
			return null;
		} else {
			try(Connection conn = DriverManager.getConnection(url, username, password)){
			
				String sql = "SELECT * FROM ers_users WHERE ers_password = ? AND user_role_id = ?;";
			
				PreparedStatement ps = conn.prepareStatement(sql);
			
				ps.setString(1, checker.get(0));
				ps.setString(2, checker.get(1));
				ResultSet rs = ps.executeQuery();
			
				while(rs.next()) {
					int returnValue = rs.getInt(1);
					String returnValue2 = rs.getString(2);
					String returnValue3 = rs.getString(3);
					String returnValue4 = rs.getString(4);
					String returnValue5 = rs.getString(5);
					String returnValue6 = rs.getString(6);
					int returnValue7 = rs.getInt(7);
					Manager new_man = new Manager(returnValue, returnValue2, returnValue3, returnValue4, returnValue5, returnValue6, returnValue7);
					man = new_man;
				}
			
			}catch(SQLException e) {
				e.printStackTrace();
				loggy.error("A SQL exception was thrown: ", e);
			}
			loggy.info("We've get the current manager from the DB");
			return man;
		}
	}

	@Override
	public List<EmpReimbursement> viewTickets(int us) {
		
		List<EmpReimbursement> checker = new ArrayList<>();
		EmpReimbursement emp = null;
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "SELECT e.reimb_id, e.reimb_amount, e.reimb_submitted, e.reimb_resolved, e.reimb_description , e.reimb_receipt, s.reimb_status, t.reimb_type FROM ers_reimbursement AS e JOIN ers_reimbursement_status s ON e.reimb_status_id = s.reimb_status_id JOIN ers_reimbursement_type t ON e.reimb_type_id = t.reimb_type_id WHERE e.reimb_author = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, us);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int returnValue = rs.getInt(1);
				String value1=String.valueOf(returnValue);
				int returnValue2 = rs.getInt(2);
				String value2=String.valueOf(returnValue2);
				String returnValue3 = rs.getString(3);
				String returnValue4 = rs.getString(4);
				String returnValue5 = rs.getString(5);
				String returnValue6 = rs.getString(6);
				String returnValue7 = rs.getString(7);
				String returnValue8 = rs.getString(8);
				EmpReimbursement new_reim = new EmpReimbursement(value1, value2, returnValue3, returnValue4, returnValue5, returnValue6, returnValue7, returnValue8);
				checker.add(new_reim);
			}
			
			System.out.println(checker);
			loggy.info("We've view all the tickets from the DB");
			
		}catch(SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
		return checker;
	}

	@Override
	public boolean addReimRequest(int amount, String description, int us, int type) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "INSERT INTO ers_reimbursement (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_RECEIPT, REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID) VALUES (?, CURRENT_TIMESTAMP, NULL, ?, NULL, ?, NULL, 1, ?);";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setString(2, description);
			ps.setInt(3, us);
			ps.setInt(4, type);
			
			ps.execute();
			System.out.println("inside the add reim dao");
			loggy.info("We've created a new reimbursement in the DB");
			
		}catch(SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
		return true;
	}

	@Override
	public List<EmpReimbursement> viewReim() {
		List<EmpReimbursement> checker = new ArrayList<>();

		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "SELECT e.reimb_id, e.reimb_amount, e.reimb_submitted, e.reimb_resolved, e.reimb_description , e.reimb_receipt, s.reimb_status, t.reimb_type \r\n"
					+ "FROM ers_reimbursement AS e\r\n"
					+ "JOIN ers_reimbursement_status s ON e.reimb_status_id = s.reimb_status_id \r\n"
					+ "JOIN ers_reimbursement_type t ON e.reimb_type_id = t.reimb_type_id \r\n"
					+ "ORDER BY e.reimb_id;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int returnValue = rs.getInt(1);
				String value1=String.valueOf(returnValue);
				int returnValue2 = rs.getInt(2);
				String value2=String.valueOf(returnValue2);
				String returnValue3 = rs.getString(3);
				String returnValue4 = rs.getString(4);
				String returnValue5 = rs.getString(5);
				String returnValue6 = rs.getString(6);
				String returnValue7 = rs.getString(7);
				String returnValue8 = rs.getString(8);
				EmpReimbursement new_reim = new EmpReimbursement(value1, value2, returnValue3, returnValue4, returnValue5, returnValue6, returnValue7, returnValue8);
				checker.add(new_reim);
			}
			loggy.info("We've view all the DB");
			
		}catch(SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
		return checker;
	}

	@Override
	public boolean approveReim(int id, int type, int manId) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "UPDATE ers_reimbursement SET reimb_status_id = ? WHERE reimb_status_id = 1 AND reimb_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, type);
			ps.setInt(2, id);
			
			ps.execute();
			
			String sql2 = "UPDATE ers_reimbursement SET reimb_resolver = ? WHERE reimb_resolver is NULL AND reimb_id = ?;";
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setInt(1, manId);
			ps2.setInt(2, id);
			
			ps2.execute();
			
			String sql3 = "UPDATE ers_reimbursement SET reimb_resolved = current_timestamp WHERE reimb_resolved is NULL AND reimb_id = ?;";
			
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ps3.setInt(1, id);
			
			ps3.execute();
			loggy.info("We've approve a reimbursement and modify the DB");
			
		}catch(SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
		return true;
	}

	@Override
	public List<EmpReimbursement> filterStatus(int id) {
		List<EmpReimbursement> checker = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "SELECT e.reimb_id, e.reimb_amount, e.reimb_submitted, e.reimb_resolved, e.reimb_description , e.reimb_receipt, s.reimb_status, t.reimb_type\r\n"
					+ "FROM ers_reimbursement AS e\r\n"
					+ "JOIN ers_reimbursement_status s ON e.reimb_status_id = s.reimb_status_id \r\n"
					+ "JOIN ers_reimbursement_type t ON e.reimb_type_id = t.reimb_type_id\r\n"
					+ "WHERE e.reimb_status_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int returnValue = rs.getInt(1);
				String value1=String.valueOf(returnValue);
				int returnValue2 = rs.getInt(2);
				String value2=String.valueOf(returnValue2);
				String returnValue3 = rs.getString(3);
				String returnValue4 = rs.getString(4);
				String returnValue5 = rs.getString(5);
				String returnValue6 = rs.getString(6);
				String returnValue7 = rs.getString(7);
				String returnValue8 = rs.getString(8);
				EmpReimbursement new_reim = new EmpReimbursement(value1, value2, returnValue3, returnValue4, returnValue5, returnValue6, returnValue7, returnValue8);
				checker.add(new_reim);
			}
			loggy.info("We've show all the reimbursement by status from the DB");
			
		}catch(SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
		return checker;
	}

	@Override
	public int getId(String empusername, String emppassword) {
		int id = 0;
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			String sql = "SELECT ers_users_id FROM ers_users WHERE ers_username = ? AND ers_password = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, empusername);
			ps.setString(2, emppassword);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt(1);
			}
			
			
			loggy.info("We've get the Id from the DB");
			
		}catch(SQLException e) {
			e.printStackTrace();
			loggy.error("A SQL exception was thrown: ", e);
		}
		return id;
	}

}
