package entities;

import java.util.ArrayList;

public class Account {

	//Name of the Account
	private String name;
	
	//The account number ID
	private String AccountId;
	
	//The User object that is the owner of this account
	private User holder;
	
	//The list of transactions for this account
	private ArrayList<Transaction> transactions;
	
	public Account() {
	}
	
	public Account(String name, User holder, Bank bank) {
		this.name = name;
		this.holder = holder;
		this.AccountId = bank.getNewAccountId();
		this.transactions = new ArrayList<Transaction>();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountId() {
		return AccountId;
	}

	public void setAccountId(String accountId) {
		AccountId = accountId;
	}

	public User getHolder() {
		return holder;
	}

	public void setHolder(User holder) {
		this.holder = holder;
	}
	
	
}
