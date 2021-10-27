package ZCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZCartManagement {
    private Map<String,Customer>customerDetails=new HashMap<>();
    private Map<Integer, Category> categoryDetails=new HashMap<>();
    private Map<Integer, Brand> brandDetails=new HashMap<>();
    private Map<String, Map<Integer,Product>>productDetails=new HashMap<>();
//    private Map<Integer,Invoice>invoiceDetails=new HashMap<>();
    private List<String>customers=new ArrayList<>();
    private static final int CUSTOMERMAP=100;
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

    public Map<Integer, Brand> getBrandDetails() {
		return brandDetails;
	}

	public Map<Integer, Category> getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(Category category) {
		int categoryId=category.getCategoryId();
		this.categoryDetails.put(categoryId, category);
	}

	public void setBrandDetails(Brand brand) {
		int brandId=brand.getBrandId();
		this.brandDetails.put(brandId, brand);
	}
	public void setCategoryDetails(Map<Integer, Category>categories) {
		this.categoryDetails=categories;
	}

	public void setBrandDetails(Map<Integer, Brand>brands) {
		this.brandDetails=brands;
	}

	public Map<String, Map<Integer, Product>> getProductDetails() {
        return productDetails;
    }

//    public Map<Integer, Invoice> getInvoiceDetails() {
//        return invoiceDetails;
//    }
    public void setCustomerDetails(Customer customer){
        String userName=customer.getUserName();
        if(customerDetails.size()==CUSTOMERMAP) {
        	String temp=customers.get(0);
        	customers.remove(0);
        	customerDetails.remove(temp);
        }
        customers.remove(userName);
        customers.add(userName);
        customerDetails.put(userName,customer);
    }
//    public void setProductDetails(Product product){
//        int categoryId= product.getCategoryId();
//        int productId= product.getProductId();
//        Map<Integer,Product>items=productDetails.get(category);
//        if(items==null){
//            items=new HashMap<>();
//            productDetails.put(category,items);
//        }
//        items.put(productId, product);
//
//    }
//    public void setInvoiceDetails(Invoice invoice){
//        int invoiceNumber=invoice.getInvoiceNumber();
//        if(invoiceDetails.size()==INVOICEMAP) {
//        	int temp=invoices.get(0);
//        	invoices.remove(0);
//        	invoiceDetails.remove(temp);
//        }
//        invoices.remove(invoiceNumber);
//        invoices.add(invoiceNumber);
//        invoiceDetails.put(invoiceNumber,invoice);
//    }
}
