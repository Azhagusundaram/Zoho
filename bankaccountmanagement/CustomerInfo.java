package com.bankaccountmanagement;

public class CustomerInfo {
	private String name,address;
	private long phoneNumber;
	private int customerId;
	private String status;
	public void setStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setCustomerId(int customerId) {
		this.customerId=customerId;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber=phoneNumber;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public String toString(){
		return  "\nName         : "+name+ "" + "\nAddress      : "+address+ "\nPhone Number : "+phoneNumber+ "\nCustomer Id  : "+customerId;

	}
}
