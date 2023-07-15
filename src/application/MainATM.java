package application;

import java.util.Scanner;

import entities.Account;
import entities.Bank;
import entities.User;

public class MainATM {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Bank bank = new Bank("Smart Bank");
		
		User aUser = bank.addUser("Travis", "Scott", "123456");
		
		Account aAccount = new Account("Checking", aUser, bank);
		aUser.addAccount(aAccount);
		bank.addAccount(aAccount);
		
		User currentUser;
		
		while(true) {
			//Stay in the login prompt until sucess
			currentUser = MainATM.MainMenuPrompt(bank, sc);
			
			//stay in main menu until user quits
			
		}
	}

	public static User MainMenuPrompt(Bank bank, Scanner sc) {
		String userId;
		String pin;
		User authUser;
		
		do {
			System.out.printf("\n\nWelcome to %s\n\n" , bank.getName());
			System.out.print("Enter user ID: ");
			userId = sc.nextLine();
			System.out.print("Enter pin: ");
			pin = sc.nextLine();
			
			authUser = bank.userLogin(userId, pin);
			if(authUser == null) {
				System.out.println("Incorrect user Id/pin combo. \nPlease try again.");
			}
		} while(authUser == null);
		
		return authUser;
	}
	
	//print a summary of the user's accounts
	public static void printUserMenu(User user, Scanner sc) {
		user.printAccountsSummary();
		
		int option;
		
		do {
			System.out.printf("Welcome %s, what would you like to do?" , user.getFirstName());
			System.out.println("1 - Show account transaction history");
			System.out.println("2 - Withdrawl");
			System.out.println("3 - Deposit");
			System.out.println("4 - Transfer");
			System.out.println("5 - Quit\n");
			System.out.println("Enter the option that you would like");
			option = sc.nextInt();
			if(option < 1 || option > 5) {
				System.out.println("Invalid option!\n Please select 1 to 5");
			}
		} while (option < 1 || option > 5);
		
		switch(option) {
		case 1:
			MainATM.showTransactionHistory(user, sc);
			break;
		case 2:
			MainATM.withdrawl(user, sc);
			break;
		case 3:
			MainATM.deposit(user, sc);
			break;
		case 4:
			MainATM.transfer(user, sc);
			break;
		}
		if(option != 5) {
			MainATM.printUserMenu(user, sc);
		}
	}
}
