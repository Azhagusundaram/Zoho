package ZCart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*; 


public class ServletFunctions extends HttpServlet {
	
	ZCartDriver driver = ZCartDriver.getInstance();
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		 PrintWriter writter=response.getWriter();
		String path=request.getParameter("path");
		if(path.equals("initialSetUp")) {
			driver.initialSetUp();
		}else if(path.equals("addAccount")) {
			 boolean output=addAccount(request,response);
			 
			 writter.print(output);
		}
		
	 
	}
	private boolean addAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String jsonObj=request.getParameter("jsonObject");
		System.out.println(jsonObj);
		 response.setContentType("application/json;charset=UTF-8");
		JSONObject json = null;
		try {
			json = new JSONObject(jsonObj);
			String name=json.getString("name");
			 String password=json.getString("password"); 
			 String emailId=json.getString("emailId"); String mobile=json.getString("mobile");
			 Customer customer=new Customer(); customer.setName(name);
			 customer.setPassword(password); customer.setUserName(emailId);
			 customer.setMobileNumber(mobile);
			boolean output= driver.signUp(customer); 
			if(output) {
				HttpSession session=request.getSession();
				session.setAttribute("userName",emailId);
			}
			return output;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}