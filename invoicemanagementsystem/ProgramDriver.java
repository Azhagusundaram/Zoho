package invoicemanagementsystem;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProgramDriver {
    int customerId=0;
    int invoiceNumber=0;
    InvoiceManagement cache=new InvoiceManagement();
    public void addItem(List<Item>items){
        Map<String,Integer>itemMap=cache.getItems();
        for(Item item:items){
            String itemName=item.getName();
            int price= item.getPrice();
            itemMap.put(itemName,price);
        }
    }
    public int addCustomer(Customer customer){
        customerId++;
        Map<Integer,Customer>customerDetails= cache.getCustomerDetails();
        customerDetails.put(customerId,customer);
        customer.setCustomerId(customerId);
        return customerId;
    }
    public int addInvoice(List<Item> items,int customerId){
        invoiceNumber++;
        Map<Integer,Customer>customerDetails= cache.getCustomerDetails();
        if(checkCustomerId(customerId)){
            Customer customer=customerDetails.get(customerId);
            Map<Integer,Invoice>invoices=cache.getInvoices();
            Invoice invoice=new Invoice();
            invoice.setInvoiceNumber(invoiceNumber);
            invoice.setItems(items);
            int totalAmount=getAmount(items);
            invoice.setTotalAmount(totalAmount);
            invoices.put(invoiceNumber,invoice);
            customer.setInvoices(invoice);
            return invoiceNumber;
        }
        return 0;
    }
    private int calculateAmount(String itemName){
        Map<String,Integer>items=cache.getItems();
        int amount=items.get(itemName);
        return amount;
    }
    private int getAmount(List<Item>items){
        int totalAmount=0;

        for(Item item:items){
            String itemName=item.getName();
            totalAmount+=calculateAmount(itemName);
        }
        return totalAmount;
    }
    public String addItemToInvoice(List<Item>items,int invoiceNumber,int customerId){
        Map<Integer, Invoice> invoices=cache.getInvoices();
        Map<Integer,Customer>customerDetails= cache.getCustomerDetails();
        if(!checkCustomerId(customerId)){
            return "Invalid Customer Id";
        }
        Customer customer=customerDetails.get(customerId);
        if(!checkInvoiceNumber(invoiceNumber)){
            return "Invalid Invoice Number";
        }
        Invoice invoice=invoices.get(invoiceNumber);
        invoice.setItems(items);
        customer.setInvoices(invoice);
        return "Item added SuccessFully";
    }
    public StringBuilder getAllCustomers(){
        StringBuilder stringBuilder=new StringBuilder();
        Map<Integer,Customer>customerDetails= cache.getCustomerDetails();
        if(customerDetails.isEmpty()){
            return new StringBuilder("Customer Details is Empty");
        }
        Set<Integer>customerIds=customerDetails.keySet();
        stringBuilder.append("CustomerId\tCustomerName\tAddress\tMobile Number");
        for(int customerId:customerIds){
            Customer customer=customerDetails.get(customerId);
            stringBuilder.append(customer);
        }
        return stringBuilder;

    }
    public StringBuilder getAllInvoices(){
        StringBuilder stringBuilder=new StringBuilder();
        Map<Integer, Invoice> invoices=cache.getInvoices();
        if(invoices.isEmpty()){
            return new StringBuilder("Invoices is empty");
        }
        Set<Integer>invoiceNumbers=invoices.keySet();
        for(int invoiceNumber:invoiceNumbers){
            Invoice invoice=invoices.get(invoiceNumber);
            stringBuilder.append(invoice);
        }
        return stringBuilder;
    }
    public StringBuilder getInvoices(int customerId){
        StringBuilder stringBuilder=new StringBuilder();
        Map<Integer,Customer>customerDetails= cache.getCustomerDetails();
        if(!checkCustomerId(customerId)){
            return new StringBuilder("Invalid Customer Id");
        }
        Customer customer=customerDetails.get(customerId);
        Map<Integer,Invoice>invoices=customer.getInvoices();
        if(invoices.isEmpty()){
            return new StringBuilder("Invoice is empty for this customer Id");
        }
        Set<Integer>invoiceNumbers=invoices.keySet();
        for(int invoiceNumber:invoiceNumbers){
            Invoice invoice=invoices.get(invoiceNumber);
            stringBuilder.append(invoice);
        }

        return stringBuilder;
    }
    public String getInvoice(int invoiceNumber){
        Map<Integer, Invoice> invoices=cache.getInvoices();
        if(!checkInvoiceNumber(invoiceNumber)){
            return "Invalid Invoice Number";
        }
       Invoice invoice= invoices.get(invoiceNumber);
       return invoice.toString();
    }
    public boolean checkCustomerId(int customerId){
        Map<Integer,Customer>customerDetails= cache.getCustomerDetails();
        return customerDetails.containsKey(customerId);
    }
    public boolean checkInvoiceNumber(int invoiceNumber){
        Map<Integer, Invoice> invoices=cache.getInvoices();
        return invoices.containsKey(invoiceNumber);
    }
    public StringBuilder getItems(){
        int i=1;
        StringBuilder stringBuilder=new StringBuilder();
        Map<String,Integer>items=cache.getItems();
        Set<String>itemNames=items.keySet();
        for(String itemName:itemNames){
            stringBuilder.append(i+"."+itemName+" : "+items.get(itemName)+"   ");
            i++;
        }
        return stringBuilder;
    }
}
