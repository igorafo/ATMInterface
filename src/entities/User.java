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
	private String UserId;
	
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
		this.UserId = bank.getNewUserId();
		this.accounts = new ArrayList<Account>();
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
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
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
	
	public void printAccountsSummary() {
		System.out.printf("\n\n%s's accounts summary " , this.firstName);
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
		
	}
	 
}
