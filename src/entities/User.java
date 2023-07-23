package entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

	//First name of the User
	private String firstName;
	
	//Last name of the User
	private String lastName;
	
	//User Id
	private String userId;
	
	//The MD5 hash of the user's pin number
	private byte pinhash[];
	
	//The List of accounts for this user
	private ArrayList<Account> accounts;
	
	public User() {	
	}

	public User(String firstName, String lastName, String pin, Bank bank) {
		this.firstName = firstName;
		this.lastName = lastName;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinhash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error! " + e);
			e.printStackTrace();
		}
		this.userId = bank.getNewUserId();
		this.accounts = new ArrayList<Account>();
		
		System.out.printf("New User %s, %s with ID %s created. \n" ,lastName , firstName ,  this.userId);
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
	}
	
	public boolean validatePin(String aPin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()),
					this.pinhash);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error! " + e);
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * Printing the user's accounts summary and info
	 */
	public void printAccountsSummary() {
		System.out.printf("\n\n%s's accounts info \n" , this.firstName);
		for(int i = 0; i< this.accounts.size(); i++) {
			System.out.printf("%d) %s\n", i+1,
					this.accounts.get(i).getSummaryLine());
		}
		System.out.println();
	}
	
	public int numberOfAccounts() {
		return accounts.size();
	}
	
	public void printAccountTransactionHistory(int accountId) {
		accounts.get(accountId).printTransactionHistory();
	}
	
	public double getAccountBalance(int accountId) {
		return accounts.get(accountId).getBalance();
	}
	
	public String getAccountUserId(int accountId) {
		return accounts.get(accountId).getAccountId(); 
	}
	
	public void addAccountTransaction(int accountId, double amount, String memo) {
		accounts.get(accountId).addTransaction(amount, memo);
	}
	 
}
