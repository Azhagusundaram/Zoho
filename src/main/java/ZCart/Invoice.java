package ZCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Invoice {
    private int invoiceNumber;
    private Map<Integer,Integer>items=new HashMap<>();
    private double totalAmount;
    private double savingAmount;
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

    public void setItems(int itemId,int quantity) {
        Integer num=items.get(itemId);
        if(num==null){
            num=0;
        }
        num+=quantity;
        items.put(itemId,num);

    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount+= totalAmount;
    }

    public double getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(double savingAmount) {
        this.savingAmount+= savingAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String toString(){
        return "Total Amount:"+totalAmount+"\nDiscount:"+savingAmount+"\nBalance:"+(totalAmount-savingAmount);
    }
}
