package entities;

import java.util.ArrayList;

public class Account {

	//Name of the Account
	private String name;
	
	//The account number ID
	private String accountId;
	
	//The User object that is the owner of this account
	private User holder;
	
	//The list of transactions for this account
	private ArrayList<Transaction> transactions;
	
	public Account() {
	}
	
	public Account(String name, User holder, Bank bank) {
		this.name = name;
		this.holder = holder;
		this.accountId = bank.getNewAccountId();
		this.transactions = new ArrayList<Transaction>();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public User getHolder() {
		return holder;
	}

	public void setHolder(User holder) {
		this.holder = holder;
	}
	
	public String getSummaryLine() {
		double balance = this.getBalance();
		
		if(balance>=0) {
			return String.format("Account id: %s | Balance: $%.2f | Account type: %s", this.accountId, balance, this.name);
		} else {
			return String.format("Account id: %s | Balance: $%.2f | Account type: %s", this.accountId, balance, this.name);
		}
		 
	}
	
	public double getBalance() {
		double balance = 0;
		for(Transaction t : this.transactions) {
			balance += t.getAmount();
		}
		return balance; 
	}
	
	/*
	 * Method to print the transaction history for the accounts
	 */
	public void printTransactionHistory() {
		System.out.printf("\nTransaction history for account %s\n", accountId);
		System.out.println("Day | Month | timeStamp | year | value");
		for(int i = transactions.size()-1; i>=0; i--) {
			
			System.out.printf(transactions.get(i).getSummaryLine()+"\n");
		}
		System.out.println();
	}
	/*
	 * Performing a transaction
	 */
	public void addTransaction(double amount, String memo) {
		Transaction transaction = new Transaction(amount, memo, this);
		transactions.add(transaction);
	}
}
