package ZCart;

import java.sql.SQLException;
import java.util.List;

public interface PersistenceLayer {
	void setConnection() throws SQLException;
	int addCustomerDetails(Customer customer)throws SQLException;
	Customer getCustomerDetails(String userName)throws SQLException;
	List<Product>getProductDetails(String category)throws SQLException;
	List<Invoice>getInvoiceDetails(int customerId) throws SQLException;
}
