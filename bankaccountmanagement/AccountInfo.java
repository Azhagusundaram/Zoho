package com.bankaccountmanagement;

public class AccountInfo {
	private long accountNumber;
	private int customerId;
	private double balance=0;
	private String status;
	public void setStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber=accountNumber;
	}
	public void setCustomerId(int customerId) {
		this.customerId=customerId;
	}
	public void setBalance(double balance) {
		this.balance+=balance;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public double getBalance() {
		return balance;
	}
	@Override
	public String toString(){
		return  "\nAccount Number : "+accountNumber+"" + "\nCustomer Id    : "+customerId+"" + "\nBalance        : "+balance;
	}
}
