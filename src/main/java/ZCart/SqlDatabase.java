package ZCart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class SqlDatabase implements PersistenceLayer{
	ZCartDriver driver=ZCartDriver.getInstance();
	private Connection connect;
	public SqlDatabase() {
		try {
			setConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void setConnection() throws SQLException {
		try{
			if(connect==null) {
				System.out.println("hi");
				 Class.forName("com.mysql.jdbc.Driver");
				connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/ZCart","root","1234");
			}
		}
		catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public int addCustomerDetails(Customer customer) throws SQLException {
		setConnection();
		String sql="insert into customer_table(name,user_name,password,mobile_number)values(?,?,?,?)";
		PreparedStatement prepState=connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet result=null;
		try {
			prepState.setString(1,customer.getName());
			prepState.setString(2, customer.getUserName());
			prepState.setString(3, customer.getPassword());
			prepState.setString(4, customer.getMobileNumber());
			prepState.execute();
			result=prepState.getGeneratedKeys();
			result.next();
			int customerId=result.getInt(1);
			return customerId;
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			prepState.close();
			result.close();
		}
		return 0;
	}
	public Customer getCustomerDetails(String userName) throws SQLException {
		setConnection();
		String sql="Select * from customer_table where user_name = "+userName;
		try(PreparedStatement prepState=connect.prepareStatement(sql);
				ResultSet result=prepState.executeQuery()){
			while(result.next()) {
				int customerId=result.getInt("customer_id");
				String name=result.getString("name");
				String password=result.getString("password");
				String mobile=result.getString("mobile_number");
				Customer customer=Helper.getCustomer(name, userName, password, mobile,customerId);
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	public List<Product> getProductDetails(String category) throws SQLException{
		setConnection();
		List<Product>products=new ArrayList();
		String sql="Select * from product_table where category ="+category;
		try(PreparedStatement prepState=connect.prepareStatement(sql);
				ResultSet result=prepState.executeQuery()){
			while(result.next()) {
				int productId=result.getInt("product_id");
				String brand=result.getString("brand");
				String model=result.getString("model");
				double price=result.getInt("price");
				int stock=result.getInt("stock");
				Product product=Helper.getProduct(productId, category, brand, model, price, stock);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	public List<Invoice>getInvoiceDetails(int customerId) throws SQLException{
		setConnection();
		List<Invoice>invoices=new ArrayList();
		String sql="Select * from invoice_table where customer_id = "+customerId;
		try(PreparedStatement prepState=connect.prepareStatement(sql);
				ResultSet result=prepState.executeQuery()){
			while(result.next()) {
				int invoiceNumber=result.getInt("invoice_number");
				int totalAmount=result.getInt("total_amount");
				int discountAmount=result.getInt("discount_amount");
				int balanceAmount=result.getInt("balance_amount");
				Invoice invoice=Helper.getInvoice(invoiceNumber, customerId, totalAmount, discountAmount, balanceAmount);
				invoice=addItemDetails(invoiceNumber,invoice);
				invoices.add(invoice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return invoices;
	}
	public Invoice addItemDetails(int invoiceNumber,Invoice invoice) throws SQLException{
		setConnection();
		String sql="Select * from order_item_table where invoice_number="+invoiceNumber;
		try(PreparedStatement prepState=connect.prepareStatement(sql);
				ResultSet result=prepState.executeQuery()){
			while(result.next()) {
				int productId=result.getInt("product_id");
				int quantity=result.getInt("quantity");
				invoice.setItems(productId, quantity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return invoice;
	}
	public void cleanUp() throws SQLException {
		try {
			connect.close();
		}catch (SQLException e){
			throw e;
		}
	}
	
	
}