package ZCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZCartManagement {
    private Map<String,Customer>customerDetails=new HashMap<>();
    private Map<String, Map<Integer,Product>>productDetails=new HashMap<>();
    private Map<Integer,Invoice>invoiceDetails=new HashMap<>();
//    private List<String> coupons=new ArrayList<>();
//
//    public List<String> getCoupons() {
//        return coupons;
//    }
//
//    public void setCoupons(String coupon) {
//        coupons.add(coupon);
//    }

    public Map<String, Customer> getCustomerDetails() {
        return customerDetails;
    }

    public Map<String, Map<Integer, Product>> getProductDetails() {
        return productDetails;
    }

    public Map<Integer, Invoice> getInvoiceDetails() {
        return invoiceDetails;
    }
    public void setCustomerDetails(Customer customer){
        String userName=customer.getUserName();
        customerDetails.put(userName,customer);
    }
    public void setProductDetails(Product product){
        String category= product.getCategory();
        int productId= product.getItemId();
        Map<Integer,Product>items=productDetails.get(category);
        if(items==null){
            items=new HashMap<>();
            productDetails.put(category,items);
        }
        items.put(productId, product);

    }
    public void setInvoiceDetails(Invoice invoice){
        int invoiceNumber=invoice.getInvoiceNumber();
        invoiceDetails.put(invoiceNumber,invoice);
    }
}
