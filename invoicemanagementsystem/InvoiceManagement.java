package invoicemanagementsystem;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InvoiceManagement {
    private Map<Integer,Customer> customerDetails=new HashMap<>();
    private Map<Integer,Invoice>invoices=new HashMap<>();
    private Map<String,Integer>items=new LinkedHashMap<>();

    public Map<Integer, Customer> getCustomerDetails() {
        return customerDetails;
    }

    public Map<Integer, Invoice> getInvoices() {
        return invoices;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

}
