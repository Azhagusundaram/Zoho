package invoicemanagementsystem;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private String name;
    private String address;
    private long mobileNumber;
    private int customerId;
    private Map<Integer,Invoice> invoices=new HashMap<>();

    public void setName(String name) {
        this.name = name;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Map<Integer, Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Invoice invoice) {
        int invoiceNumber=invoice.getInvoiceNumber();
        invoices.put(invoiceNumber,invoice);
    }
    @Override
    public String toString(){
        return "\n"+customerId+"\t"+name+"\t"+address+"\t"+mobileNumber;
    }

}
