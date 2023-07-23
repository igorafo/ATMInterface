package application;

import java.util.Scanner;

import entities.Account;
import entities.Bank;
import entities.User;

public class MainATM {

	public static void main(String[] args) {
		/* Instantiating Objetcs types Scanner, Bank, User and Account
		 * and adding to the list of accounts
		 */
		Scanner sc = new Scanner(System.in);

		Bank bank = new Bank("Smart Bank");

		User aUser = bank.addUser("Travis", "Scott", "123456");

		Account aAccount = new Account("Checking", aUser, bank);
		aUser.addAccount(aAccount);
		bank.addAccount(aAccount);

		// Adding a current user to use to login
		User currentUser;

		while (true) {
			// Stay in the login prompt until sucess
			currentUser = MainATM.MainMenuPrompt(bank, sc);

			// stay in main menu until user quits
			MainATM.printUserMenu(currentUser, sc);

		}
	}

	/* Print the main menu of the login 
	 * 
	 */
	public static User MainMenuPrompt(Bank bank, Scanner sc) {
		String userId;
		String pin;
		User authUser;

		//Entering the credentials and cheking if its correct
		do {
			System.out.printf("\n\nWelcome to %s\n\n", bank.getName());
			System.out.print("Enter user ID: ");
			userId = sc.nextLine();
			System.out.print("Enter pin: ");
			pin = sc.nextLine();

			authUser = bank.userLogin(userId, pin);
			if (authUser == null) {
				System.out.println("Incorrect user Id/pin combo. \nPlease try again.");
			}
		} while (authUser == null);

		return authUser;
	}

	// print a user menu to display the available options
	public static void printUserMenu(User user, Scanner sc) {
		user.printAccountsSummary();

		int option;

		do {
			System.out.printf("Welcome %s, what would you like to do?\n", user.getFirstName());
			System.out.println("1 - Show account transaction history");
			System.out.println("2 - withdraw");
			System.out.println("3 - Deposit");
			System.out.println("4 - Transfer");
			System.out.println("5 - Quit\n");
			System.out.print("Enter the option that you would like: ");
			option = sc.nextInt();
			if (option < 1 || option > 5) {
				System.out.println("Invalid option!\n Please select 1 to 5");
			}
		} while (option < 1 || option > 5);

		switch (option) {
		case 1:
			MainATM.showTransactionHistory(user, sc);
			break;
		case 2:
			MainATM.withdraw(user, sc);
			break;
		case 3:
			MainATM.deposit(user, sc);
			break;
		case 4:
			MainATM.transfer(user, sc);
			break;
		}
		if (option != 5) {
			MainATM.printUserMenu(user, sc);
		}
	}

	/* Method to show the transaction history for the accounts
	 * 
	 */
	public static void showTransactionHistory(User user, Scanner sc) {
		int account;

		do {
			System.out.printf("Enter the number (1-%d) of the account whose transactions you would like to see: ",
					user.numberOfAccounts());
			account = sc.nextInt() - 1;
			if (account < 0 || account >= user.numberOfAccounts()) {
				System.out.println("Invalid option. Please try again.");
			}
		} while (account < 0 || account >= user.numberOfAccounts());
		user.printAccountTransactionHistory(account);
	}

	/*
	 * Method to transfer from one account to another
	 */
	public static void transfer(User user, Scanner sc) {

		int fromAccount;
		int toAccount;
		double amount;
		double accountBalance;

		//Getting the account to transfer from
		do {
			System.out.printf("Enter the number of the account(1-%d) you want to transfer from: ", user.numberOfAccounts());
			fromAccount = sc.nextInt() - 1;
			if (fromAccount < 0 || fromAccount >= user.numberOfAccounts()) {
				System.out.println("Invalid option. Please try again.");
			}
		} while (fromAccount < 0 || fromAccount >= user.numberOfAccounts());
		accountBalance = user.getAccountBalance(fromAccount);

		//Getting the account to transfer to
		do {
			System.out.printf("Enter the number of the account(1-%d) you want to transfer to: " ,user.numberOfAccounts());
			toAccount = sc.nextInt() - 1;
			if (toAccount < 0 || toAccount >= user.numberOfAccounts()) {
				System.out.println("Invalid option. Please try again.");
			}
		} while (toAccount < 0 || toAccount >= user.numberOfAccounts());

		//Getting the amount
		do {
			System.out.printf("Enter the amount to transfer (max %.2f): $", accountBalance);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero. ");
			} else if (amount > accountBalance) {
				System.out.printf("Amount must not be greater than balance of %.2f\n", accountBalance);
			}
		} while (amount < 0 || amount > accountBalance);

		//Executing the transactions
		user.addAccountTransaction(fromAccount, -1 * amount,
				String.format("Transfered to account %s", user.getAccountUserId(toAccount)));
		user.addAccountTransaction(toAccount, amount,
				String.format("Transfered from account %s", user.getAccountUserId(fromAccount)));
	}

	/*
	 * Method to withdraw a value from the selected account
	 */
	public static void withdraw(User user, Scanner sc) {

		int fromAccount;
		double amount;
		double accountBalance;
		String memo;

		//Selecting the number of the account to withdraw from
		do {
			System.out.printf("Enter the number of the account(1-%d) you want to withdraw: ", user.numberOfAccounts());
			fromAccount = sc.nextInt() - 1;
			if (fromAccount < 0 || fromAccount >= user.numberOfAccounts()) {
				System.out.println("Invalid option. Please try again.");
			}
		} while (fromAccount < 0 || fromAccount >= user.numberOfAccounts());
		accountBalance = user.getAccountBalance(fromAccount);

		//Entering the amount to withdraw and cheking if its available
		do {
			System.out.printf("Enter the amount to withdraw (max %.2f): $", accountBalance);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero. ");
			} else if (amount > accountBalance) {
				System.out.printf("Amount must not be greater than balance of %.2f\n", accountBalance);
			}
		} while (amount < 0 || amount > accountBalance);

		sc.nextLine();

		System.out.println("Enter a memo");
		memo = sc.nextLine();

		//Executing the transaction
		user.addAccountTransaction(fromAccount, -1 * amount, memo);
	}

	/*
	 * Method to deposit to the selected account
	 */
	public static void deposit(User user, Scanner sc) {
		int toAccount;
		double amount;
		double accountBalance;
		String memo;

		//Entering the number of the account to deposit
		do {
			System.out.printf("Enter the number of the account (1-%d) you want to deposit: ", user.numberOfAccounts());
			toAccount = sc.nextInt() - 1;
			if (toAccount < 0 || toAccount >= user.numberOfAccounts()) {
				System.out.println("Invalid option. Please try again.");
			}
		} while (toAccount < 0 || toAccount >= user.numberOfAccounts());
		accountBalance = user.getAccountBalance(toAccount);

		//Getting the amount to deposit
		do {
			System.out.printf("Enter the amount to deposit: $ ", accountBalance);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero. ");
			}
		} while (amount < 0);

		sc.nextLine();

		System.out.print("Enter a memo: ");
		memo = sc.nextLine();

		user.addAccountTransaction(toAccount, amount, memo);
	}
}
