package ZCart;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class ZCartDriver {
    private int productId=1;
    private int invoiceNumber=1;
    private List<Integer> highStockProductId=new ArrayList<>();
    private int highStocks=0;
    private static final String adminUser="admin@zoho.com";
    private   static  String adminPassword="xyzzy";
    ZCartManagement cache=new ZCartManagement();
    public void initialSetUp(){
        try {
            setCustomerDetails();
            setProductDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAdminPassword(String password){
        adminPassword=password;
    }
    private void setProductDetails() throws IOException {
        FileReader file=new FileReader("products.txt");
        BufferedReader bufferedReader1=new BufferedReader(file);
        String str1;
        while ((str1=bufferedReader1.readLine())!=null){
            String[]array=str1.split(":");
            int itemId=Integer.parseInt(array[0]);
            String category=array[1];
            String brand=array[2];
            String model=array[3];
            double price=Double.parseDouble(array[4]);
            int stock=Integer.parseInt(array[5]);
            Product item = getProduct(itemId, category, brand, model, price, stock);
            cache.setProductDetails(item);
        }
        setHighStockProductId();
        file.close();
        bufferedReader1.close();
    }

    private void setCustomerDetails() throws IOException {
        FileReader fileReader=new FileReader("customer.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String str;
        while ((str=bufferedReader.readLine())!=null){
            String[]array=str.split(":");
            String name=array[0];
            String userName=array[1];
            String password=array[2];
            long mobile=Long.parseLong(array[3]);
            Customer customer = getCustomer(name, userName, password, mobile);
            customer=getPurchaseDetails(customer);
            cache.setCustomerDetails(customer);
        }
        fileReader.close();
        bufferedReader.close();
    }
    private void setHighStockProductId(){
        Collection<Map<Integer, Product>> allProducts=cache.getProductDetails().values();
        for (Map<Integer,Product>map:allProducts){
            Collection<Product>products=map.values();
            for (Product product:products){
                if(product.getStock()>highStocks){
                    highStocks=product.getStock();
                    highStockProductId.clear();
                }
                if(product.getStock()==highStocks){
                    highStockProductId.add(product.getItemId());
                }
            }
        }
    }
    private Customer getPurchaseDetails(Customer customer){
        String username=customer.getUserName();
        try {
            FileReader fileReader=new FileReader(username+".txt");
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String str;
            List<Integer> invoices=new ArrayList<>();
            Map<String,Integer>couponCodes=new HashMap<>();
            while ((str=bufferedReader.readLine())!=null){
                String[]array=str.split(":");
                if(array[0].equals("Invoice")){
                   invoices.add(Integer.parseInt(array[1]));
                }else {
                    String[]codes=array[1].split("=");
                    String code=codes[0];
                    int usage=Integer.parseInt(codes[1]);
                    couponCodes.put(code,usage);
                }
            }
            customer.setInvoices(invoices);
            customer.setCoupon(couponCodes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public boolean signUp(Customer customer){
        Map<String,Customer>customerDetails= cache.getCustomerDetails();
        String userName=customer.getUserName();
        if(customerDetails.containsKey(userName)){
            return false;
        }
        String password=customer.getPassword();
        String encrypted=encryptPassword(password);
        customer.setPassword(encrypted);
        cache.setCustomerDetails(customer);
        updateFile(customer);
        createFile(userName);
        return true;
    }
    private void createFile(String userName){
        File file=new File(userName+".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateFile(Customer customer){
        String name=customer.getName();
        String userName=customer.getUserName();
        String password=customer.getPassword();
        long mobile=customer.getMobileNumber();
        String str=name+":"+userName+":"+password+":"+mobile;
        try {
            FileWriter fileWriter=new FileWriter("customer.txt",true);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(str);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateFile(Product product){
        int productId=product.getItemId();
        String category=product.getCategory();
        String brand=product.getBrand();
        String model=product.getModel();
        int stock=product.getStock();
        double price= product.getPrice();
        String str=productId+":"+category+":"+brand+":"+model+":"+price+":"+stock;
        try {
            FileWriter fileWriter=new FileWriter("products.txt",true);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(str);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteProductFile(){
        File file=new File("products.txt");
        file.delete();
    }
    public void updateProductFile(){
        Collection<Map<Integer, Product>> allProducts=cache.getProductDetails().values();
        for (Map<Integer,Product>map:allProducts){
            Collection<Product>products=map.values();
            for (Product product:products){
                updateFile(product);
            }
        }
    }
    public void updateFile(Invoice invoice,String userName){
        int invoiceNumber=invoice.getInvoiceNumber();
        String str="Invoice:"+invoiceNumber;
        try {
            FileWriter fileWriter=new FileWriter(userName+".txt",true);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(str);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addProduct(Product product){
        product.setItemId(productId);
        updateFile(product);
        cache.setProductDetails(product);
        productId++;
    }
    public Collection<Product> getProduct(String category){
        Map<Integer,Product> products=cache.getProductDetails().get(category);
        if(products==null){
            return null;
        }
        return products.values();
    }
    public Product shopProduct(int productId, String  category){
        Map<Integer,Product> products=cache.getProductDetails().get(category);
        Product product=products.get(productId);
        if(product==null){
            return null;
        }
        int stock=product.getStock();
        if(stock==0){
            return null;
        }
        return product;
    }
    public Invoice checkOut(List<Product>products, String userName, String couponCode){
        Customer customer=cache.getCustomerDetails().get(userName);
        Invoice invoice=new Invoice();
        invoice.setInvoiceNumber(invoiceNumber);
        for(Product product:products){
            int productId=product.getItemId();
            double amount=product.getPrice();
            int stock=product.getStock();
            if(stock==0){
                continue;
            }
            product.setStock(-1);
            invoice.setItems(productId,1);
            invoice.setTotalAmount(amount);
            double discount=checkCouponCode(couponCode,userName);
            if(highStockProductId.contains(productId)){
                discount+=product.getPrice()*0.1;
            }
            invoice.setSavingAmount(discount);
        }
        int numOfPurchases=customer.getInvoices().size();
        if(numOfPurchases==3||invoice.getTotalAmount()>=20000){
            String code=createCouponCode(customer);
            customer.setCoupon(code);
        }

        deleteProductFile();
        updateProductFile();
        setHighStockProductId();
        customer.setInvoices(invoiceNumber);
        cache.setInvoiceDetails(invoice);
        updateFile(invoice,userName);
        invoiceNumber++;
        return invoice;
    }
    public List<Product> getLessStocks(){
        List<Product>lessStocks=new ArrayList<>();
        Collection<Map<Integer, Product>> allProducts=cache.getProductDetails().values();
        for (Map<Integer,Product>map:allProducts){
            Collection<Product>products=map.values();
            for (Product product:products){
                if(product.getStock()<10){
                    lessStocks.add(product);
                }
            }
        }
        return lessStocks;
    }
    public List<Invoice> getInvoices(String userName){
        Customer customer=cache.getCustomerDetails().get(userName);
        List<Integer>invoiceNumbers=customer.getInvoices();
        List<Invoice>invoices=new ArrayList<>();
        for (int invoiceNumber:invoiceNumbers){
            Invoice invoice=cache.getInvoiceDetails().get(invoiceNumber);
            invoices.add(invoice);
        }
        return invoices;
    }
    public void reOrder(List<Product>products){
        for (Product product:products){
            product.setStock(10);
        }
        deleteProductFile();
        updateProductFile();
    }
    public String createCouponCode(Customer customer){
        String alphaNumeric="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder code=new StringBuilder();
        for (int i=0;i<6;i++){
            int index= (int) (alphaNumeric.length()*Math.random());
            code.append(alphaNumeric.charAt(index));
        }
        return code.toString();
    }
    public int checkCouponCode(String couponCode,String userName){
        Customer customer=cache.getCustomerDetails().get(userName);
        Map<String,Integer>coupon=customer.getCoupon();
        if(coupon!=null&&!coupon.isEmpty()){
            int number=coupon.get(couponCode);
            if(number==0){
                return 0;
            }
            coupon.put(couponCode,number-1);

            int discount= (int) (Math.random() * (30 - 20 + 1)+20);
            return discount;
        }
        return 0;
    }
    public boolean checkPassword(String password){
        if (Pattern.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,20}",password)){
            return true;
        }
        return false;
    }
    private String encryptPassword(String password){
        char[]chars=password.toCharArray();
        StringBuilder encryptedPassword=new StringBuilder();
        char k;
        for(char c:chars){
            if(c=='Z'){
                k='A';
            }else if(c=='z'){
                k='a';
            }
            else if(c=='9'){
                k='0';
            }else {
                k= (char) (c+1);
            }
            encryptedPassword.append(k);
        }
        return encryptedPassword.toString();
    }
    private String decryptPassword(String password){
        char[]chars=password.toCharArray();
        StringBuilder decrypted =new StringBuilder();
        char k;
        for(char c:chars){
            if(c=='A'){
                k='Z';
            }else if(c=='a'){
                k='z';
            }
            else if(c=='0'){
                k='9';
            }else {
                k= (char) (c-1);
            }
            decrypted.append(k);
        }
        return decrypted.toString();
    }
    public boolean userLogin(String userName,String password){
        Customer customer= cache.getCustomerDetails().get(userName);
        if(customer==null){
            return false;
        }
        String encrypted=customer.getPassword();
        String decrypted=decryptPassword(encrypted);
        if(decrypted.equals(password)){
            return true;
        }
        return false;
    }
    public  boolean adminLogin(String userName,String password){
        return userName.equals(adminUser)&&password.equals(adminPassword);
    }

    public Product getProduct(int itemId, String category, String brand, String model, double price, int stock) {
        Product item=new Product();
        item.setItemId(itemId);
        item.setCategory(category);
        item.setBrand(brand);
        item.setModel(model);
        item.setStock(stock);
        item.setPrice(price);
        return item;
    }

    public Customer getCustomer(String name, String userName, String password, long mobile) {
        Customer customer=new Customer();
        customer.setName(name);
        customer.setPassword(password);
        customer.setUserName(userName);
        customer.setMobileNumber(mobile);
        return customer;
    }
}
