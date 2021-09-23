package invoicemanagementsystem;

import java.util.*;

public class InvoiceDriver {
    int customerId=0;
    int invoiceNumber=0;
    InvoiceManagement cache=new InvoiceManagement();
    public void addItem(List<Item>items){
        Map<Integer, Item> itemMap=cache.getItems();
        for(Item item:items){
            int itemId=item.getItemId();
            itemMap.put(itemId,item);
        }
    }
    public int addCustomer(Customer customer){
        customerId++;
        Map<Integer,Customer>customerDetails= cache.getCustomerDetails();
        customerDetails.put(customerId,customer);
        customer.setCustomerId(customerId);
        return customerId;
    }
    public int addInvoice(List<Integer> itemIds,int customerId){
        invoiceNumber++;
        List<Item>items=getItems(itemIds);
        Map<Integer,Customer>customerDetails= cache.getCustomerDetails();
        if(checkCustomerId(customerId)){
            Customer customer=customerDetails.get(customerId);
            Map<Integer,Invoice>invoices=cache.getInvoices();
            Invoice invoice=new Invoice();
            invoice.setInvoiceNumber(invoiceNumber);
            invoice.setItems(itemIds);
            int totalAmount=getAmount(itemIds);
            invoice.setTotalAmount(totalAmount);
            invoices.put(invoiceNumber,invoice);
            customer.setInvoices(invoice);
            return invoiceNumber;
        }
        return 0;
    }
    public String getInvoiceDetails(int invoiceNumber){
        Map<Integer,Item>allItems=cache.getItems();
        Invoice invoice=cache.getInvoices().get(invoiceNumber);
        Map<Integer,Integer> items=invoice.getItems();
        int totalAmount=invoice.getTotalAmount();
        StringBuilder result=new StringBuilder();
        result.append("\nInvoice Number : ");
        result.append(invoiceNumber);
        result.append("\nItem\tPrice\tNumber of item");
        Set<Integer> itemIds=items.keySet();
        for(int itemId:itemIds){
            Item item=allItems.get(itemId);
            result.append("\n"+item.getName()+"\t"+item.getPrice()+"\t"+items.get(itemId));
        }
        result.append("\nTotal Items :");
        result.append(items.size());
        result.append("\nTotalPrice : ");
        result.append(totalAmount);
        return result.toString();

    }
    private int calculateAmount(int itemId){
        Map<Integer, Item> items=cache.getItems();
        Item item=items.get(itemId);
        return item.getPrice();
    }
    private int getAmount(List<Integer>itemIds){
        int totalAmount=0;

        for(int itemId:itemIds){
            totalAmount+=calculateAmount(itemId);
        }
        return totalAmount;
    }
    public List<Item> getItems(List<Integer>itemIds){
        List<Item>items=new ArrayList<>();
        for (int itemId:itemIds){
            items.add(getItem(itemId));
        }
        return items;
    }
    public String addItemToInvoice(List<Integer>itemIds,int invoiceNumber,int customerId){

        Map<Integer, Invoice> invoices=cache.getInvoices();
        Map<Integer,Customer>customerDetails= cache.getCustomerDetails();
        if(!checkCustomerId(customerId)){
            return "Invalid Customer Id";
        }
        Customer customer=customerDetails.get(customerId);
        if(!checkInvoiceNumber(invoiceNumber,customerId)){
            return "Invalid Invoice Number";
        }
        Invoice invoice=invoices.get(invoiceNumber);
        invoice.setItems(itemIds);
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

            stringBuilder.append(getInvoiceDetails(invoiceNumber));
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
            stringBuilder.append(getInvoiceDetails(invoiceNumber));
        }

        return stringBuilder;
    }
    public String getInvoice(int invoiceNumber){
        Map<Integer, Invoice> invoices=cache.getInvoices();
        if(!checkInvoiceNumber(invoiceNumber)){
            return "Invalid Invoice Number";
        }
       return getInvoiceDetails(invoiceNumber);
    }
    public boolean checkCustomerId(int customerId){
        Map<Integer,Customer>customerDetails= cache.getCustomerDetails();
        return customerDetails.containsKey(customerId);
    }
    public boolean checkInvoiceNumber(int invoiceNumber){
        Map<Integer, Invoice> invoices=cache.getInvoices();
        return invoices.containsKey(invoiceNumber);
    }
    public boolean checkInvoiceNumber(int invoiceNumber,int customerId){
        Customer customer=cache.getCustomerDetails().get(customerId);
        Map<Integer,Invoice>invoices=customer.getInvoices();
        return invoices.containsKey(invoiceNumber);
    }
    public List<Item> getItems(){
        Map<Integer, Item> items=cache.getItems();
        Set<Integer> itemIds=items.keySet();
        List<Item>allItems=new ArrayList<>();
        for(int itemId:itemIds){
            Item item=items.get(itemId);
            allItems.add(item);
        }
        return allItems;
    }
    public Item getItem(int itemId){
        Map<Integer, Item> items=cache.getItems();
        return items.get(itemId);
    }
    public boolean checkItemId(int itemId){
        return cache.getItems().containsKey(itemId);
    }
}
