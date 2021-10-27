package ZCart;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PersistenceLayer {
	void setConnection();
	int addCustomerDetails(Customer customer);
	Customer getCustomerDetails(String userName);
	//Map<Integer, Product> getCategoryBasedProductDetails(String category);
//	Map<Integer, Product> getBrandBasedProductDetails(String brand);	
	Map<Integer, Invoice> getInvoiceDetails(int customerId);
	Map<Integer, Product> getproducts(int stock);
	boolean reOrderProduct(int productId,int stock);
	boolean changePassword(String userName,String newPassword);
	int addProductDetails(Product product);
	int addCategory(String category);
	int addBrand(String brand);
	int editProductDetails(Product product);
	Map<Integer, Product> getproducts(String search);
	Map<Integer, Category> getCategoryDetails();
	Map<Integer, Brand> getBrandDetails();
	boolean deleteProduct(int productId);
	int updateProductDetails(Product product);
}
