package ZCart;
public class Helper{
	public static Customer getCustomer(String name, String userName, String password, String mobile,int customerId) {
        Customer customer=new Customer();
        customer.setName(name);
        customer.setPassword(password);
        customer.setUserName(userName);
        customer.setMobileNumber(mobile);
        customer.setCustomerId(customerId);
        return customer;
    }
	public static Product getProduct(int productId, int category, int brand, String model, double price, int stock) {
        Product product=new Product();
        product.setProductId(productId);
        product.setCategoryId(category);
        product.setBrandId(brand);
        product.setModel(model);
        product.setStock(stock);
        product.setPrice(price);
        return product;
    }
	public static Product getProduct(int category, int brand, String model, double price, int stock) {
        Product product=new Product();
        product.setCategoryId(category);
        product.setBrandId(brand);
        product.setModel(model.toUpperCase());
        product.setStock(stock);
        product.setPrice(price);
        return product;
    }
	public static Invoice getInvoice(int invoiceNumber,int customerId,double totalAmount,double discountAmount,double balanceAmount) {
		Invoice invoice=new Invoice();
		invoice.setInvoiceNumber(invoiceNumber);
		invoice.setBalance(balanceAmount);
		invoice.setTotalAmount(totalAmount);
		invoice.setDiscountAmount(discountAmount);
		invoice.setBalance(balanceAmount);
		return invoice;
	}
	public static Category getCategory(int categoryId,String categoryName) {
		Category category=new Category();
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName.toUpperCase());
		return category;
	}
	public static Category getCategory(String categoryName) {
		Category category=new Category();
		category.setCategoryName(categoryName);
		return category;
	}
	public static Brand getBrand(int brandId,String brandName) {
		Brand brand=new Brand();
		brand.setBrandId(brandId);
		brand.setBrandName(brandName);
		return brand;
	}
	public static Brand getBrand(String brandName) {
		Brand brand=new Brand();
		brand.setBrandName(brandName.toUpperCase());
		return brand;
	}
}