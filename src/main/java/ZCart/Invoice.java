package ZCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Invoice {
    private int invoiceNumber;
    private Map<Integer,Integer>items=new HashMap<>();
    private double totalAmount;
    private double discountAmount;
    private double balance;

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(int productId,int quantity) {
        Integer num=items.get(productId);
        if(num==null){
            num=0;
        }
        num+=quantity;
        items.put(productId,num);

    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount+= totalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double savingAmount) {
        this.discountAmount+= savingAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String toString(){
        return "Total Amount:"+totalAmount+"\nDiscount:"+discountAmount+"\nBalance:"+(totalAmount-discountAmount);
    }
}
