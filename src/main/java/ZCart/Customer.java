package ZCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    private String name;
    private String userName;
    private String password;
    private String mobileNumber;
    private int customerId;
    private Map<String,Integer>coupon=new HashMap<>();
    private Map<Integer,Invoice>invoices=new HashMap<>();

    public void setCustomerId(int customerId) {
    	this.customerId=customerId;
    }
    public int getCustomerId() {
    	return customerId;
    }
    public void setCoupon(Map<String, Integer> coupon) {
        this.coupon = coupon;
    }
    public void setCoupon(String code){
        coupon.put(code,3);
    }
    public void setInvoices(Invoice invoice){
    	int invoiceNumber=invoice.getInvoiceNumber();
        invoices.put(invoiceNumber,invoice);
    }

    public Map<String, Integer> getCoupon() {
        return coupon;
    }

    public Map<Integer, Invoice> getInvoices() {
        return invoices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }



}
