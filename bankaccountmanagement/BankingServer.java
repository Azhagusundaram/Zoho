package com.bankaccountmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BankingServer {
	static Scanner scan=new Scanner(System.in);
	static ProgramDriver driver=new ProgramDriver();
	public static void main(String[] args) throws Exception {

//		driver.setStorage();
		try {
			driver.setDataBase();
		}catch (DataErrorException e){
			System.out.println(e);
		}
		while(true) {
			System.out.println("1.New customer\n2.New Account \n3.Check Balance \n4.Others\n5.Exit");
			int decision=scan.nextInt();
			if(decision==1) {
				addNewCustomer();
			}
			else if(decision==2) {
				addNewAccount();
			}
			else if(decision==3) {
				checkBalance();
			}
			else if(decision==4){
				while(true){
					try{
						System.out.println("1.Delete Customer\n2.Delete Account\n3.Deposit Amount\n4.Withdrawl Amount\n5.Activate Account\n6.Activate Customer\n7.Exit");
						int decision1=scan.nextInt();
						if(decision==7){
							break;
						}
						else if(decision1>7&&decision1<=0) {
							System.out.println("Invalid Input");
						}
						else {
							System.out.println("Enter the Customer Id : ");
							int customerId=scan.nextInt();
							if(driver.checkCustomerId(customerId)&&decision1<=4) {
								if(decision1==1){
									deleteCustomer(customerId);
								}
								else {
									System.out.println("Enter the Account Number : ");
									long accountNumber = scan.nextLong();
									if (driver.checkAccountNumber(customerId, accountNumber)) {
										if (decision1 == 2) {
											deleteAccount(customerId, accountNumber);
										} else if (decision1 == 3) {
											depositAmount(customerId, accountNumber);
										} else if (decision1 == 4) {
											withdrawlAmount(customerId, accountNumber);
										}
									}else {
										System.out.println("Invalid Account Number");
									}
								}
							}else if(driver.checkDeactivatedCustomerId(customerId)){
								if(decision1==6){
									System.out.println("1.Existing Account\n2.New Account");
									int decision3=scan.nextInt();
									if(decision3==1){
										activateExistingAccountCustomerId(customerId);
									}
									else if(decision3==2){
										activateNewAccountCustomerId(customerId);
									}else{
										System.out.println("Invalid Input");
									}
									driver.setDataBase();
								}else if(!driver.checkDeactivatedCustomerId(customerId)){
									activateAccount(customerId);
								}else {
									System.out.println("First Activate the Customer");
								}
							}else {
								System.out.println("Invalid Customer id ");
							}
						}
					}catch (DataErrorException e){
						System.out.println(e);
					}
				}
			}
			else if(decision==5) {
				driver.closeConnection();
				System.out.println("Thank you");
				break;
			}
			else{
				System.out.println("Invalid Input");
			}
		}
		scan.close();
	}

	private static void activateAccount(int customerId) throws DataErrorException {
		System.out.println("Enter the Account Number : ");
		long accountNumber=scan.nextLong();
		if(driver.checkDeactivatedAccount(customerId,accountNumber)){
			driver.activateAccount(customerId,accountNumber);
			System.out.println("Account is activated");
		}
	}

	private static void activateNewAccountCustomerId(int customerId) throws DataErrorException {
		System.out.print("Enter the Deposit Amount : ");
		double amount=scan.nextLong();
		amount=checkAmount(amount);
		scan.nextLine();
		AccountInfo account = Helper.getAccountInfo(customerId, amount);
		HashMap<String,AccountInfo> newAccount=driver.addNewAccount(account);
		System.out.println(newAccount);
		driver.activateCustomerId(customerId);
		System.out.println("Customer Id and Account is Activated");
	}

	private static void activateExistingAccountCustomerId(int customerId) throws DataErrorException {
		System.out.print("Enter the Account Number : ");
		long accountNumber=scan.nextLong();
		if(driver.checkDeactivatedAccount(customerId,accountNumber)){
			driver.activateAccount(customerId,accountNumber);
			driver.activateCustomerId(customerId);
			System.out.println("Customer Id and Account is Activated");
		}else {
			System.out.println("Invalid AccountNumber");
		}
	}

	private static void withdrawlAmount(int customerId, long accountNumber) throws DataErrorException {
		System.out.println("Enter the Amount to Withdrawl : ");
		double amount=scan.nextInt();
		amount=checkAmount(amount);
		while(driver.checkWithdrawl(accountNumber, customerId,amount)){
			System.out.println("Out of Balance.Enter the Withdrawl Amount : ");
			amount=scan.nextInt();
		}
		driver.withdrawlAmountDataBase(accountNumber,amount);
		driver.withdrawlAmount(accountNumber, customerId,amount);
		System.out.println("Amount Withdrawn");
	}

	public static void depositAmount(int customerId,long accountNumber)throws DataErrorException{
		System.out.println("Enter the Amount to deposit : ");
		double amount = scan.nextInt();
		amount = checkAmount(amount);
		driver.depositAmountDataBase(accountNumber, amount);
		driver.depositAmount(accountNumber, customerId, amount);
		System.out.println("Amount Deposited");
	}
	public static void deleteAccount(int customerId,long accountNumber)throws DataErrorException{
		driver.deleteAccount(accountNumber);
		driver.deleteCustomer(customerId);
		System.out.println("Account Deleted Successful");
	}
	public static void deleteCustomer(int customerId)throws DataErrorException{
		driver.deleteAllAccounts(customerId);
		driver.deleteCustomer(customerId);
		System.out.println("Customer Id Deleted Successful");
	}
	public static String checkName(String name){
		while(!Pattern.matches("[\\D]{4,20}",name)){
			System.out.println("Enter the Correct Name : ");
			name=scan.nextLine();
		}
		return name;
	}
	public static long checkPhoneNumber(long phoneNumber){
		while (!Pattern.matches("[6789][0-9]{9}",Long.toString(phoneNumber))){
			System.out.println("Enter the valid Phone Number : ");
			phoneNumber= scan.nextLong();
		}
		return phoneNumber;
	}
	public static double checkAmount(double amount){
		while(amount>100000&&amount<100){
			System.out.println("Amount must be less than 100000 and Greater than 100\n Enter the amount");
			amount=scan.nextLong();
		}
		return amount;
	}
	public static String checkEmailId(String emailId){
		while (!Pattern.matches("^[a-z0-9_.-]+@[a-z]+\\.[a-z]+$",emailId)){
			System.out.println("Enter the Valid Email Id : ");
			emailId=scan.nextLine();
		}
		return emailId;
	}
	public static void addNewCustomer()throws DataErrorException{
		System.out.print("Enter the number of customers : ");
		int number =scan.nextInt();
		scan.nextLine();
		ArrayList<ArrayList> customers = new ArrayList<>(number);
		for(int i=0;i<number;i++) {
			ArrayList array=new ArrayList<>(2);
			System.out.print("Enter the Name : ");
			String name=scan.nextLine();
			name=checkName(name);
			System.out.print("Enter the Email Id : ");
			String emailId=scan.nextLine();
			emailId=checkEmailId(emailId);
			System.out.print("Enter the Phone number : ");
			long phone=scan.nextLong();
			phone=checkPhoneNumber(phone);
			System.out.print("Enter the Deposit Amount : ");
			double amount=scan.nextLong();
			amount=checkAmount(amount);
			scan.nextLine();
			CustomerInfo customer = Helper.getCustomerInfo(name, emailId, phone);
			AccountInfo account = Helper.getAccountInfo( 0, amount);
			array.add(customer);
			array.add(account);
			customers.add(array);
		}
		HashMap<String,ArrayList<ArrayList>> newCustomer =driver.addNewCustomer(customers);
		if(newCustomer.isEmpty()){
			System.out.println("customers ArrayList is null");
		}else{
			System.out.println(newCustomer);
		}
	}
	public static void addNewAccount()throws DataErrorException{
		System.out.print("Enter the Customer Id : ");
		int customerId =scan.nextInt();
		if(driver.checkCustomerId(customerId)){
			System.out.print("Enter the Deposit Amount : ");
			double amount=scan.nextLong();
			amount=checkAmount(amount);
			scan.nextLine();
			AccountInfo account = Helper.getAccountInfo(customerId, amount);
			HashMap<String,AccountInfo> newAccount=driver.addNewAccount(account);
			if (newAccount.isEmpty()){
				System.out.println("Account ArrayList is Null");
			}else {
				System.out.println(newAccount);
			}
		}else if(driver.checkDeactivatedCustomerId(customerId)){
			System.out.println("Your customer Id is Deactivated. So  activate the customer Id");
		}
		else{
			System.out.println("Invalid CustomerId");
		}
	}
	public static void checkBalance(){
		try {
			System.out.print("Enter the Customer id : ");
			int customerId =scan.nextInt();
			if(driver.checkCustomerId(customerId)){
				System.out.println("1.Individual Account\n2.All Account");
				int decision1=scan.nextInt();
				if(decision1==1){
					System.out.print("Enter the Account Number of Individual Account otherwise enter 0 : ");
					long accountNumber=scan.nextInt();
					if(driver.checkAccountNumber(customerId,accountNumber)) {
						String str=driver.getAccount(accountNumber, customerId);
						System.out.println(str);
					}else {
						System.out.println("Invalid Account Number");
					}
				}else if(decision1==2){
					ArrayList<String> array=driver.getAccount(customerId);
					System.out.println(array);
				}else {
					System.out.println("Invalid input");
				}
			}else {
				System.out.println("Invalid Customer id ");
			}
		}catch (DataErrorException e){
			e.printStackTrace();
		}
	}


}
