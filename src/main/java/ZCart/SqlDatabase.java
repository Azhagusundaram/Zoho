package ZCart;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class SqlDatabase implements PersistenceLayer{
	ZCartDriver driver=ZCartDriver.getInstance();
	private Connection connect;
	public SqlDatabase() {
		setConnection();
	}
	public void setConnection() {
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
	public int addCustomerDetails(Customer customer)  {
		setConnection();
		String sql="insert into customer_table(name,user_name,password,mobile_number)values(?,?,?,?)";
		
	
		try(PreparedStatement prepState=connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			prepState.setString(1,customer.getName());
			prepState.setString(2, customer.getUserName());
			prepState.setString(3, customer.getPassword());
			prepState.setString(4, customer.getMobileNumber());
			prepState.execute();
			ResultSet result=prepState.getGeneratedKeys();
			result.next();
			int customerId=result.getInt(1);
			result.close();
			return customerId;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	public int addProductDetails(Product product){
		setConnection();
		int check=checkProduct(product);
		System.out.println(check);
		if(check==-2) {
			return 0;
		}else if(check==-1) {
			String sql="insert into product_table (category_id,brand_id,model,price,stock)values(?,?,?,?,?)";
			try(PreparedStatement prepState=connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
				prepState.setInt(1,product.getCategoryId());
				prepState.setInt(2,product.getBrandId());
				prepState.setString(3,product.getModel());
				prepState.setDouble(4,product.getPrice());
				prepState.setInt(5,product.getStock());
				prepState.execute();
				ResultSet result=prepState.getGeneratedKeys();
				result.next();
				int productId=result.getInt(1);
				result.close();
				return productId;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			return updateProductDetails(product);
		}
		return 0;
	}
	public int editProductDetails(Product product) {
		setConnection();
		return updateProductDetails(product);
	}
	public int addCategory(String category){
		setConnection();
		String sql="insert into category_table (category_name)values(?)";
		try(PreparedStatement prepState=connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
			prepState.setString(1,category);
			prepState.execute();
			ResultSet result=prepState.getGeneratedKeys();
			result.next();
			int categoryId=result.getInt(1);
			result.close();
			return categoryId;
		}catch(SQLIntegrityConstraintViolationException e) {
			return -1;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int addBrand(String brand)  {
		setConnection();
		String sql="insert into brand_table (brand_name)values(?)";
		try(PreparedStatement prepState=connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
			prepState.setString(1,brand);
			prepState.execute();
			ResultSet result=prepState.getGeneratedKeys();
			result.next();
			int brandId=result.getInt(1);
			result.close();
			return brandId;
		} catch(SQLIntegrityConstraintViolationException e) {
			return -1;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int updateProductDetails(Product product) {
		setConnection();
		String sql="update product_table set price = ? ,stock = ?,category_id = ?,brand_id = ?,model = ? where product_id=?";
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setDouble(1,product.getPrice());
			prepState.setInt(2,product.getStock());
			prepState.setInt(3, product.getCategoryId());
			prepState.setInt(4,product.getBrandId());
			prepState.setString(5, product.getModel());
			prepState.setInt(6, product.getProductId());
			prepState.execute();
			return -1;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean reOrderProduct(int productId,int stock) {
		setConnection();
		String sql="update product_table set stock =stock+ ? where product_id=?";
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setInt(1,stock);
			prepState.setInt(2,productId);
			prepState.execute();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public Map<Integer, Product> getproducts(String search) {
		Map<Integer,Product>products=new HashMap<>();
		int categoryId=getCategoryId(search);
		int brandId=getBrandId(search);
		String equation="=";
		if(search.equals("all")) {
			equation ="!=";
		}
		String sql="Select * from product_table where category_id = ? OR brand_id = ? OR model "+equation+"?";
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setInt(1,categoryId);
			prepState.setInt(2, brandId);
			prepState.setString(3,search);
			ResultSet result=prepState.executeQuery();
			while(result.next()) {
				int productId=result.getInt("product_id");
				int categoryId1=result.getInt("category_id");
				int brandId1=result.getInt("brand_id");
				String model1=result.getString("model");
				double price=result.getInt("price");
				int stock=result.getInt("stock");
				Product product=Helper.getProduct(productId, categoryId1, brandId1, model1, price, stock);
				products.put(productId,product);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	public Map<Integer, Product> getproducts(int stock) {
		Map<Integer,Product>products=new HashMap<>();
		
		String sql="Select * from product_table where stock <= ?";
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setInt(1,stock);
			ResultSet result=prepState.executeQuery();
			while(result.next()) {
				int productId=result.getInt("product_id");
				int categoryId1=result.getInt("category_id");
				int brandId1=result.getInt("brand_id");
				String model1=result.getString("model");
				double price=result.getInt("price");
				int stock1=result.getInt("stock");
				Product product=Helper.getProduct(productId, categoryId1, brandId1, model1, price, stock1);
				products.put(productId,product);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	public boolean deleteProduct(int productId) {
		setConnection();
		String sql="Delete from product_table where product_id =?";
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setInt(1, productId);
			prepState.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	private int checkProduct(Product product) {
		String sql="Select * from product_table where category_id = ? AND brand_id = ? AND model = ?";
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setInt(1, product.getCategoryId());
			prepState.setInt(2, product.getBrandId());
			prepState.setString(3,product.getModel());
			ResultSet result=prepState.executeQuery();
			int stock=-1;
			if(result.next()) {
				stock=result.getInt("stock");
				int productStock=product.getStock();
				int productId=result.getInt("product_id");
				product.setProductId(productId);
				product.setStock(productStock+stock);
			}
			result.close();
			return stock;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return -2;
	}
	public Customer getCustomerDetails(String userName)  {
		setConnection();
		String sql="Select * from customer_table where user_name =?";
		
		
		try(PreparedStatement prepState=connect.prepareStatement(sql);){
			prepState.setString(1, userName);
			ResultSet result=prepState.executeQuery();
			while(result.next()) {
				int customerId=result.getInt("customer_id");
				String name=result.getString("name");
				String password=result.getString("password");
				String mobile=result.getString("mobile_number");
				Customer customer=Helper.getCustomer(name, userName, password, mobile,customerId);
				return customer;
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
//	public Map<Integer,Product> getCategoryBasedProductDetails(String category) {
//		setConnection();
//		Map<Integer,Product>products=new HashMap();
//		int categoryId=getCategoryId(category);
//		if(categoryId>0) {
//			String sql="Select * from product_table where category_id = ?";
//			
//			try(PreparedStatement prepState=connect.prepareStatement(sql);){
//				prepState.setInt(1, categoryId);
//				ResultSet result=prepState.executeQuery();
//				while(result.next()) {
//					int productId=result.getInt("product_id");
//					int brandId=result.getInt("brand_id");
//					String model=result.getString("model");
//					double price=result.getInt("price");
//					int stock=result.getInt("stock");
//					Product product=Helper.getProduct(productId, categoryId, brandId, model, price, stock);
//					products.put(productId,product);
//				}
//				result.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return products;
//	}
//	public Map<Integer, Product> getBrandBasedProductDetails(String brand){
//		setConnection();
//		Map<Integer,Product>products=new HashMap();
//		int brandId=getBrandId(brand);
//		if(brandId>0) {
//			String sql="Select * from product_table where brand_id = ?";
//			
//			
//			try(PreparedStatement prepState=connect.prepareStatement(sql);){
//				prepState.setInt(1, brandId);
//				ResultSet result=prepState.executeQuery();
//				while(result.next()) {
//					int productId=result.getInt("product_id");
//					int categoryId=result.getInt("category_id");
//					String model=result.getString("model");
//					double price=result.getInt("price");
//					int stock=result.getInt("stock");
//					Product product=Helper.getProduct(productId, categoryId, brandId, model, price, stock);
//					products.put(productId,product);
//					result.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return products;
//	}
	private int getCategoryId(String category) {
		String sql="Select * from category_table where category_name = ?";
		
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setString(1, category);
			ResultSet result=prepState.executeQuery();
			while(result.next()) {
				int categoryId=result.getInt("category_id");
				return categoryId;
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;	
	}
	private int getBrandId(String brand) {
		String sql="Select * from brand_table where brand_name = ?";
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setString(1, brand);
			ResultSet result=prepState.executeQuery();
			while(result.next()) {
				int brandId=result.getInt("brand_id");
				return brandId;
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;	
	}	 
	public boolean changePassword(String userName,String newPassword) {
		setConnection();
		String sql="Select * from customer_table set password =? where user_name = ?";
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setString(1,newPassword);
			prepState.setString(2,userName);
			return prepState.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public Map<Integer, Invoice> getInvoiceDetails(int customerId){
		setConnection();
		Map<Integer,Invoice>invoices=new HashMap();
		String sql="Select * from invoice_table where customer_id = ?";
		try(PreparedStatement prepState=connect.prepareStatement(sql);){
			prepState.setInt(1,customerId);
			ResultSet result=prepState.executeQuery();
			while(result.next()) {
				int invoiceNumber=result.getInt("invoice_number");
				int totalAmount=result.getInt("total_amount");
				int discountAmount=result.getInt("discount_amount");
				int balanceAmount=result.getInt("balance_amount");
				Invoice invoice=Helper.getInvoice(invoiceNumber, customerId, totalAmount, discountAmount, balanceAmount);
				invoice=addItemDetails(invoiceNumber,invoice);
				invoices.put(invoiceNumber,invoice);
				result.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return invoices;
	}
	private Invoice addItemDetails(int invoiceNumber,Invoice invoice) {
		setConnection();
		String sql="Select * from order_item_table where invoice_number = ?";
		
		try(PreparedStatement prepState=connect.prepareStatement(sql);){
			prepState.setInt(1, invoiceNumber);
			ResultSet result=prepState.executeQuery();
			while(result.next()) {
				int productId=result.getInt("product_id");
				int quantity=result.getInt("quantity");
				invoice.setItems(productId, quantity);
				result.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return invoice;
	}
	public void cleanUp(){
		try {
			connect.close();
		}catch (SQLException e){
			e.printStackTrace();;
		}
	}
	public Map<Integer, Category> getCategoryDetails() {
		setConnection();
		Map<Integer,Category>categories=new HashMap<>();
		String sql="Select * from category_table";
		try(PreparedStatement prepState=connect.prepareStatement(sql);
				ResultSet result=prepState.executeQuery()){
			while(result.next()) {
				String categoryName=result.getString("category_name");
				int categoryId=result.getInt("category_id");
				Category category=Helper.getCategory(categoryId, categoryName);
				categories.put(categoryId,category);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return categories;
	}
	public Map<Integer, Brand> getBrandDetails() {
		setConnection();
		Map<Integer,Brand>brands=new HashMap<>();
		String sql="Select * from brand_table";
		try(PreparedStatement prepState=connect.prepareStatement(sql);
				ResultSet result=prepState.executeQuery()){
			while(result.next()) {
				String brandName=result.getString("brand_name");
				int brandId=result.getInt("brand_id");
				Brand brand=Helper.getBrand(brandId, brandName);
				brands.put(brandId,brand);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return brands;
	}
	
	
}