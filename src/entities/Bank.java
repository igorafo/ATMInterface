package entities;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

	// The name of the bank
	private String name;

	// The list of the users for this bank
	private ArrayList<User> users;

	// The list of accounts for this bank
	private ArrayList<Account> accounts;

	/**
	 * Create new User exclusive Id
	 * @return
	 */
	public String getNewUserId() {
		String userId = "";
		Random rnd = new Random();
		int length = 6;
		boolean nonUnique;

		do {
			for (int i = 0; i < length; i++) {
				userId += ((Integer) rnd.nextInt(10)).toString();
			}
			nonUnique = false;
			for (User user : this.users) {
				if (userId.compareTo(user.getUserId()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		return userId;
	}

	/**
	 * Create a new account exclusive Id
	 * @return
	 */
	public String getNewAccountId() {
		String accountId = "";
		Random rnd = new Random();
		int length = 10;
		boolean nonUnique;

		do {
			for (int i = 0; i < length; i++) {
				accountId += ((Integer) rnd.nextInt(10)).toString();
			}
			nonUnique = false;
			for (Account account : this.accounts) {
				if (accountId.compareTo(account.getAccountId()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		return accountId;
	}
	
	public void addAccount(Account account) {
		this.accounts.add(account);
	}
	
	/**
	 * Adding a new User
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @return
	 */
	public User addUser(String firstName, String lastName, String pin) {
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.accounts.add(newAccount);
		
		return newUser;
	}
	
	/**
	 * Creating login method using the userId and the Pin, if not valid, return null
	 * @param userId
	 * @param pin
	 * @return
	 */
	public User userLogin(String userId, String pin) {
		for(User user : this.users) {
			if(user.getUserId().compareTo(userId) == 0 && user.validatePin(pin)) {
				return user;
			}
		}
		return null;
	}
}
