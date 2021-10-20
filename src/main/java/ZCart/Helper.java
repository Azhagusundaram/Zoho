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
	public static Product getProduct(int productId, String category, String brand, String model, double price, int stock) {
        Product item=new Product();
        item.setItemId(productId);
        item.setCategory(category);
        item.setBrand(brand);
        item.setModel(model);
        item.setStock(stock);
        item.setPrice(price);
        return item;
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
}