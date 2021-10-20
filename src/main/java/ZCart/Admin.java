package ZCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

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
			boolean output= addProduct(request, response);
			writter.print(output);
		}
		
	}
	private boolean addProduct(HttpServletRequest request, HttpServletResponse response) {
		String jsonObj=request.getParameter("jsonObject");
		response.setContentType("application/json;charset=UTF-8");
		JSONObject json = null;
		try {
			json = new JSONObject(jsonObj);
			String category=json.getString("category");
			String brand=json.getString("brand");
			String model=json.getString("model");
			int price=Integer.parseInt(json.getString("price"));
			int stock=Integer.parseInt(json.getString("stock"));
			Product product=driver.getProduct(category, brand, model, price, stock);
			return driver.addProduct(product);
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
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