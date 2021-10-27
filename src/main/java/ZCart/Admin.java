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
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class Admin extends HttpServlet {
	ZCartDriver driver = ZCartDriver.getInstance();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter writter=response.getWriter();
		String path=request.getParameter("path");
		String userName=request.getParameter("username");
		if(path.equals("changePassword")) {
			boolean output= changePassword(request, response,userName);
			writter.print(output);
		}
		else if(path.equals("addProduct")) {
			int output= addProduct(request, response);
			writter.print(output);
		}else if(path.equals("addCategory")) {
			int output=addCategory(request, response);
			writter.print(output);
		}else if(path.equals("addBrand")) {
			int output= addBrand(request, response);
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
		}else if(path.equals("deleteProduct")) {
			boolean output= deleteProduct(request, response);
			writter.print(output);	
		}else if(path.equals("editProduct")) {
			int output= editProduct(request, response);
			writter.print(output);
		}else if(path.equals("reOrderProduct")) {
			boolean output= reOrderProduct(request, response);
			writter.print(output);
		}
	}
	private boolean deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		String jsonObj=request.getParameter("jsonObject");
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json;
		try {
			json = new JSONObject(jsonObj);
			int productId=json.getInt("productId");
			return driver.deleteProduct(productId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
		
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
			if(function.equals("removeproduct")) {
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
	private boolean reOrderProduct(HttpServletRequest request, HttpServletResponse response) {
		String jsonObj=request.getParameter("jsonObject");
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = null;
		try {
			json = new JSONObject(jsonObj);
			int productId=json.getInt("productId");
			System.out.println(json.getString("stock"));
			int stock=Integer.parseInt(json.getString("stock"));
			return driver.reOrderProduct(productId,stock);
			
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return false;
	}
	private int addProduct(HttpServletRequest request, HttpServletResponse response) {
		String jsonObj=request.getParameter("jsonObject");
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = null;
		try {
			json = new JSONObject(jsonObj);
			int categoryId=json.getInt("categoryId");
			int brandId=json.getInt("brandId");
			String model=json.getString("model");
			int price=Integer.parseInt(json.getString("price"));
			int stock=Integer.parseInt(json.getString("stock"));
			Product product=Helper.getProduct(categoryId, brandId, model, price, stock);
			return driver.addProduct(product);
			
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return 0;
	}
	private int editProduct(HttpServletRequest request, HttpServletResponse response) {
		String jsonObj=request.getParameter("jsonObject");
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = null;
		try {
			json = new JSONObject(jsonObj);
			int productId=json.getInt("productId");
			int categoryId=json.getInt("categoryId");
			int brandId=json.getInt("brandId");
			String model=json.getString("model");
			int price=Integer.parseInt(json.getString("price"));
			int stock=Integer.parseInt(json.getString("stock"));
			Product product=Helper.getProduct(productId,categoryId, brandId, model, price, stock);
			return driver.editProduct(product);
			
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return 0;
	}
	private int addCategory(HttpServletRequest request, HttpServletResponse response) {
		String jsonObj=request.getParameter("jsonObject");
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = null;
		try {
			json=new JSONObject(jsonObj);
			String categoryName=json.getString("categoryName");
			Category category=Helper.getCategory(categoryName);
			return driver.addCategory(category);
		}catch(JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	private int addBrand(HttpServletRequest request, HttpServletResponse response) {
		String jsonObj=request.getParameter("jsonObject");
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = null;
		try {
			json=new JSONObject(jsonObj);
			String brandName=json.getString("brandName");
			Brand brand=Helper.getBrand(brandName);
			return driver.addBrand(brand);
		}catch(JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}
	private boolean changePassword(HttpServletRequest request, HttpServletResponse response,String userName) {
		String jsonObj=request.getParameter("jsonObject");
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = null;
		try {
			json = new JSONObject(jsonObj);
			String newPassword=json.getString("newPassword"); 
			String oldPassword=json.getString("oldPassword"); 
			return driver.setAdminPassword(newPassword,oldPassword);
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}
}