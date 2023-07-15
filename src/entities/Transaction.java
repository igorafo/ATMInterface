package entities;

import java.util.Date;

public class Transaction {

	//The amount of this transaction
	private Double amount;
	
	//The date and time of this transaction
	private Date timestamp;
	
	//A memo for this transaction
	private String memo;
	
	//The acount in which the transaction was performed
	private Account inAccount;
	
	public Transaction() {
	}

	public Transaction(Double amount, Date timestamp, Account inAccount) {
		this.amount = amount;
		this.timestamp = new Date();
		this.inAccount = inAccount;
	}

	public Transaction(Double amount, Date timestamp, String memo, Account inAccount) {
		this.amount = amount;
		this.timestamp = timestamp;
		this.memo = memo;
		this.inAccount = inAccount;
	}
	
}
