package model;

public class EmpReimbursement {
	
	private String rei_id, amount, sub_time, res_time, description, receipt, status, type;

	public EmpReimbursement(String rei_id, String amount, String sub_time, String res_time, String description,
			String receipt, String status, String type) {
		super();
		this.rei_id = rei_id;
		this.amount = amount;
		this.sub_time = sub_time;
		this.res_time = res_time;
		this.description = description;
		this.receipt = receipt;
		this.status = status;
		this.type = type;
	}

	public String getRei_id() {
		return rei_id;
	}

	public void setRei_id(String rei_id) {
		this.rei_id = rei_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSub_time() {
		return sub_time;
	}

	public void setSub_time(String sub_time) {
		this.sub_time = sub_time;
	}

	public String getRes_time() {
		return res_time;
	}

	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
	
}
