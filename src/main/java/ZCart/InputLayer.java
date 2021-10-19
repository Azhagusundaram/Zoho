package ZCart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

//surye.prakash@zohocorp.com
public class InputLayer {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ZCartDriver driver=ZCartDriver.getInstance();
        driver.initialSetUp();
        while (true){
            System.out.println("1.UserLogin\n2.AdminLogin\n3.Exit");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                while (true){
                    System.out.println("1.New User\n2.Existing User\n3.GoBack");
                    int decision1=scan.nextInt();
                    scan.nextLine();
                    if(decision1==1){
                        addAccount(scan, driver);
                    }else if(decision1==2){
                        System.out.println("user name");
                        String userName=scan.nextLine();
                        System.out.println("Password");
                        String password=scan.nextLine();
                        if(driver.userLogin(userName,password)){
                            while (true){
                                System.out.println("1.shop product\n2.order History\n3.change Password\n4.Go back");
                                int decision2=scan.nextInt();
                                scan.nextLine();
                                if(decision2==1){
                                    shopProducts(scan, driver, userName);
                                }else if(decision2==2){
                                    List<Invoice>invoices=driver.getInvoices(userName);
                                    if(invoices.isEmpty()){
                                        System.out.println("No orders");
                                    }else {
                                        System.out.println(invoices);
                                    }
                                }else if(decision2==3){

                                }else if(decision2==4){
                                    break;
                                }
                            }

                        }else {
                            System.out.println("Invalid userName or password");
                        }
                    }else if(decision1==3){
                        break;
                    }else {
                        System.out.println("Invalid Input");
                    }
                }

            }else if(decision==2){
                System.out.println("User Name");
                String username=scan.nextLine();
                System.out.println("Password");
                String password=scan.nextLine();
                if(driver.adminLogin(username,password)){
                    while (true){
                        System.out.println("1.add product\n2.re order stocks\n3.Go back");
                        int choice=scan.nextInt();
                        scan.nextLine();
                        if(choice==1){
                            addProduct(scan, driver);
                        }else if(choice==2){
                            List<Product>products=driver.getLessStocks();
                            printProducts(products);
                            if(products!=null&&!products.isEmpty()){
                                System.out.println("1=Re-order/otherwise=No Order");
                                int reOrder=scan.nextInt();
                                scan.nextLine();
                                if(reOrder==1){
                                    driver.reOrder(products);
                                }
                            }else {
                                System.out.println("No product for re order");
                            }

                        }else if(choice==3){
                            break;
                        }
                    }
                    String password1="";
                   if(!driver.checkPassword(password)){
                       do {
                           System.out.println("password must in Alphanumeric");
                           System.out.println("Password");
                           password= scan.nextLine();
                           System.out.println("Re enter Password");
                           password1= scan.nextLine();
                       }while (!driver.checkPassword(password)||!password.equals(password1));
						/* driver.setAdminPassword(password); */
                    }
                }

            }else if(decision==3){
                break;
            }else {
                System.out.println("Invalid Input");
            }
        }


    }

    private static void addProduct(Scanner scan, ZCartDriver driver) {
        System.out.println("Category");
        String category= scan.nextLine();
        System.out.println("Brand");
        String brand= scan.nextLine();
        System.out.println("Model");
        String model= scan.nextLine();
        System.out.println("price");
        double price= scan.nextDouble();
        System.out.println("Stock");
        int stock= scan.nextInt();
        scan.nextLine();
        Product product = getProduct(category, brand, model, price, stock);
        driver.addProduct(product);
    }

    private static Product getProduct(String category, String brand, String model, double price, int stock) {
        Product product=new Product();
        product.setStock(stock);
        product.setBrand(brand);
        product.setPrice(price);
        product.setModel(model);
        product.setCategory(category);
        return product;
    }

    private static void shopProducts(Scanner scan, ZCartDriver driver, String userName) {
        System.out.println("1.Mobile\n2.Laptop\n3.Tablet");
        int choice= scan.nextInt();
        scan.nextLine();
        if(choice==1){
            String category="Mobile";
            Collection<Product>products= driver.getProduct(category);
            printProducts(products);
            if(products!=null&&!products.isEmpty()){
                orderItems(scan, driver, userName, category);
            }

        }else if(choice==2){
            String category="Laptop";
            Collection<Product>products= driver.getProduct(category);
            printProducts(products);
            if(products!=null&&!products.isEmpty()){
                orderItems(scan, driver, userName, category);
            }
        }else if(choice==3){
            String category="Tablet";
            Collection<Product>products= driver.getProduct(category);
            printProducts(products);
            if(products!=null&&!products.isEmpty()){
                orderItems(scan, driver, userName, category);
            }
        }
    }

    private static void orderItems(Scanner scan, ZCartDriver driver, String userName, String category) {
        System.out.println("Are you Order any Item(Yes=1/No=2)");
        int orderChoice= scan.nextInt();
        scan.nextLine();
        if(orderChoice==1){
            List<Product>orderProducts=new ArrayList<>();
            while (orderChoice==1){
                System.out.println("Enter productId");
                int productId= scan.nextInt();

                Product product= driver.shopProduct(productId, category);
                if(product!=null){
                    orderProducts.add(product);
                }else {
                    System.out.println("Out of stock");
                }
                System.out.println("Continue Order(Yes=1/No=2)");
                orderChoice= scan.nextInt();
                scan.nextLine();
            }
            System.out.println("Coupon Code(yes=enter coupon code/otherwise=nil)");
            String couponCode= scan.nextLine();
            Invoice invoice= driver.checkOut(orderProducts, userName,couponCode);
            System.out.println(invoice);
        }
    }

    private static void printProducts(Collection<Product> products) {
        if(products!=null){
            if(products.isEmpty()){
                System.out.println("Products is empty");
            }else {
                for(Product product: products){
                    System.out.println(product);
                }
            }
        }else {
            System.out.println("No products");
        }
    }

    private static void addAccount(Scanner scan, ZCartDriver driver) {
        System.out.println("Name");
        String name= scan.nextLine();
        System.out.println("User Name/emailId");
        String userName= scan.nextLine();
        System.out.println("Password");
        String password= scan.nextLine();
        System.out.println("Re enter Password");
        String password1= scan.nextLine();
        while (!driver.checkPassword(password)||!password.equals(password1)){
            System.out.println("password must in Alphanumeric");
            System.out.println("Password");
            password= scan.nextLine();
            System.out.println("Re enter Password");
            password1= scan.nextLine();
        }
        System.out.println("Mobile Number");
        String mobile= scan.nextLine();
        scan.nextLine();
        Customer customer= driver.getCustomer(name,userName,password,mobile);
        boolean result= driver.signUp(customer);
        if (result){
            System.out.println("Sign up successfully");
        }else {
            System.out.println("Account already exists");
        }
    }
}
