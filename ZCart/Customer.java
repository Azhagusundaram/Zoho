package ZCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    private String name;
    private String userName;
    private String password;
    private long mobileNumber;
    private Map<String,Integer>coupon=new HashMap<>();
    private List<Integer>invoices=new ArrayList<>();

    public void setCoupon(Map<String, Integer> coupon) {
        this.coupon = coupon;
    }
    public void setCoupon(String code){
        coupon.put(code,3);
    }

    public void setInvoices(List<Integer> invoices) {
        this.invoices = invoices;
    }
    public void setInvoices(int invoice){
        invoices.add(invoice);
    }

    public Map<String, Integer> getCoupon() {
        return coupon;
    }

    public List<Integer> getInvoices() {
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

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }



}
