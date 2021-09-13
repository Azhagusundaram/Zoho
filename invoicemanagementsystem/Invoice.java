package invoicemanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private int invoiceNumber;
    private List<Item> items=new ArrayList<>();
    private int totalAmount;

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setItems(List<Item> items) {
        this.items.addAll(items);
    }


    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    @Override
    public String toString(){
        StringBuilder result=new StringBuilder();
        result.append("\nInvoice Number : ");
        result.append(invoiceNumber);
        result.append("\nItem\tPrice");
        for(Item item:items){
            result.append("\n"+item.getName()+"\t"+item.getPrice());
        }
        result.append("\nTotal Items :");
        result.append(items.size());
        result.append("\nTotalPrice : ");
        result.append(totalAmount);
        String output=result.toString();
        return output;
    }
}
