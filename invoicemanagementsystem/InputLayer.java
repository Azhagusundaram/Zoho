package invoicemanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ProgramDriver driver=new ProgramDriver();
        System.out.println("Enter number of items to add");
        int num=scan.nextInt();
        scan.nextLine();
        List<Item> items=new ArrayList<>();
        for(int i=0;i<num;i++){
            System.out.println("Item Name : ");
            String itemName=scan.nextLine();
            System.out.println("Price : ");
            int price=scan.nextInt();
            scan.nextLine();
            Item item=new Item();
            item.setName(itemName);
            item.setPrice(price);
            item.setItemId(i+1);
            items.add(item);
        }
        driver.addItem(items);
        while (true){
            System.out.println("1.Add customer\n2.Add Invoice\n3.Add item to invoice\n4.All Customers\n5.All Invoice\n6.All invoices of a customer\n7.Display an Invoice\n8.Exit");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                createCustomer(scan, driver);
            }else if(decision==2){
                System.out.println("1.Existing Customer\n2.New Customer");
                int decision1=scan.nextInt();
                scan.nextLine();
                if(decision1==1){
                    createInvoice(scan, driver, items);
                }else if(decision1==2){
                    createCustomer(scan,driver);
                    createInvoice(scan, driver, items);
                }
            }else if(decision==3){
                System.out.println("Enter the Customer id :");
                int customerId=scan.nextInt();
                System.out.println("Enter the invoice number :");
                int invoiceNumber=scan.nextInt();
                scan.nextLine();
                List<Integer> allItem = chooseItem(scan, driver, items);
                String result=driver.addItemToInvoice(allItem,invoiceNumber,customerId);
                System.out.println(result);
            }else if(decision==4){
                StringBuilder result=driver.getAllCustomers();
                System.out.println(result);
            }else if(decision==5){
                StringBuilder result=driver.getAllInvoices();
                System.out.println(result);
            }else if(decision==6){
                System.out.println("Enter the Customer id :");
                int customerId=scan.nextInt();
                StringBuilder result=driver.getInvoices(customerId);
                System.out.println(result);
            }else if(decision==7){
                System.out.println("Enter the Invoice Number :");
                int invoiceNumber=scan.nextInt();
                String result=driver.getInvoice(invoiceNumber);
                System.out.println(result);
            }else if(decision==8){
                break;
            }else {
                System.out.println("Invalid Input");
            }
        }

    }

    private static void createInvoice(Scanner scan, ProgramDriver driver, List<Item> items) {
        System.out.println("Enter customer id :");
        int customerId= scan.nextInt();

        List<Integer> allItem = chooseItem(scan, driver, items);
        int result= driver.addInvoice(allItem,customerId);
        if(result!=0){
            System.out.println("Invoice Number is "+result);
        }else {
            System.out.println("Invalid customer Id");
        }
    }

    private static List<Integer> chooseItem(Scanner scan, ProgramDriver driver, List<Item> items) {
        int flag=1;
        List<Integer>allItem=new ArrayList<>();
        while (flag ==1){
            System.out.println("Select the Item Id");
            List<Item> allItems= driver.getItems();
            System.out.println(allItems);
            int itemId = scan.nextInt();
            while (!driver.checkItemId(itemId)){
                System.out.println("Enter Correct Item Id :");
                itemId =scan.nextInt();
            }
            allItem.add(itemId);
            System.out.println("1.Continue to add item\n2.Exit");
            flag = scan.nextInt();
        }
        return allItem;
    }

    private static void createCustomer(Scanner scan, ProgramDriver driver) {
        System.out.println("Name");
        String name= scan.nextLine();
        System.out.println("Address : ");
        String address= scan.nextLine();
        System.out.println("Mobile Number : ");
        Long mobileNumber= scan.nextLong();
        Customer customer=customerDetails(name, address, mobileNumber);
        int result= driver.addCustomer(customer);
        System.out.println("Customer id is "+result);
    }

    private static Customer customerDetails(String name, String address, Long mobileNumber) {
        Customer customer=new Customer();
        customer.setAddress(address);
        customer.setName(name);
        customer.setMobileNumber(mobileNumber);
        return customer;
    }


}
