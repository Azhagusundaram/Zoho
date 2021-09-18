package invoicemanagementsystem;

import java.util.*;

public class Invoice {
    private int invoiceNumber;
    private Map<Integer,Integer> items=new HashMap<>();
    private int totalAmount;

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setItems(List<Integer> itemIds) {
        for(int itemId:itemIds){
            Integer numOfItem=this.items.get(itemId);
            if(numOfItem==null){
                numOfItem=0;
            }
            numOfItem=numOfItem+1;
            this.items.put(itemId,numOfItem);

        }
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

}
