package ZCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class Home extends HttpServlet {
	ZCartDriver driver = ZCartDriver.getInstance();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter writter=response.getWriter();
		String path=request.getParameter("path");
		String userName=request.getParameter("username");
		if(path.equals("changePassword")) {
			boolean output= changePassword(request, response,userName);
			writter.print(output);
		}else if(path.equals("getCategory")) {
			String output= getCategory(request, response);
			writter.print(output);		
		}else if(path.equals("getBrand")) {
			String output= getBrand(request, response);
			writter.print(output);		
		}else if(path.equals("getProduct")) {
			String output= getProduct(request, response);
			writter.print(output);	
		}else if(path.equals("orderhistory")) {
			List<Invoice>invoices=driver.getInvoices(userName);
			if(invoices==null) {
				writter.print("null");
			}
			else if(invoices.isEmpty()) {
				writter.print("No orders");
			
			}else {
				for(Invoice invoice:invoices) {
					writter.print(invoice);
				}
			}
		}else if(path.equals("mobile")) {
			Collection<Product>products= driver.getProduct("Mobile");
			System.out.println(products);
			if(products!=null&&!products.isEmpty()) {
				for(Product product:products) {
					System.out.println(product);
					writter.print(product);
				}
			}else{
				writter.print("false");
			}
		}else if(path.equals("tablet")) {
			Collection<Product>products= driver.getProduct("Tablet");
			System.out.println(products);
			if(products!=null&&!products.isEmpty()) {
				for(Product product:products) {
					System.out.println(product);
					writter.print(product);
				}
			}else{
				writter.print("false");
			}
		}else if(path.equals("laptop")) {
			Collection<Product>products= driver.getProduct("Laptop");
			System.out.println(products);
			if(products!=null&&!products.isEmpty()) {
				for(Product product:products) {
					System.out.println(product);
					writter.print(product);
				}
			}else{
				writter.print("false");
			}
		}
		
	}
	private String getProduct(HttpServletRequest request, HttpServletResponse response) {
		String jsonObj=request.getParameter("jsonObject");
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json;
		Map<Integer, Product> products=null;
		Gson gson = new Gson();
		try {
			json = new JSONObject(jsonObj);
			String function=json.getString("functionname");
			if(function.equals("shopproduct")) {
				String search=json.getString("search");
				products=driver.getAllProducts(search);
			}else {
				int stock=json.getInt("search");
				products=driver.getAllProducts(stock);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String sg=gson.toJson(products);
		
		System.out.print("json"+sg);
		return sg;
		
	}
	private String getCategory(HttpServletRequest request, HttpServletResponse response) {
		Map<Integer, Category> categories=driver.getAllCategories();
		Gson gson = new Gson();
		return gson.toJson(categories);
	}
	private String getBrand(HttpServletRequest request, HttpServletResponse response) {
		Map<Integer, Brand> brands=driver.getAllBrands();
		Gson gson = new Gson();
		return gson.toJson(brands);
	}
	private boolean changePassword(HttpServletRequest request, HttpServletResponse response,String userName) {
		String jsonObj=request.getParameter("jsonObject");
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = null;
		try {
			json = new JSONObject(jsonObj);
			String newPassword=json.getString("newPassword"); 
			String oldPassword=json.getString("oldPassword"); 
			return driver.changePassword(userName, newPassword, oldPassword);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}
}
		