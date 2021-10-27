package ZCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
		if(path.equals("checksession")) {
			HttpSession session=request.getSession();
			Object sessionName=session.getAttribute("userName");
			System.out.println(sessionName);
			if(sessionName==null) {
				writter.print(false);
			}else {
				writter.print(true);
			}
		}else if(path.equals("initialSetUp")) {
			driver.initialSetUp();
		}else if(path.equals("addAccount")) {
			 boolean output = false;
			try {
				output = addAccount(request,response);
			} catch (IOException | ServletException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 writter.print(output);
		}else if(path.equals("removeAttribute")) {
			HttpSession session=request.getSession();
			Object sessionName=session.getAttribute("userName");
			sessionName=null;
			System.out.println(sessionName);
		}
		
	 
	}
	private boolean addAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException{
		String jsonObj=request.getParameter("jsonObject");
		System.out.println(jsonObj);
		 response.setContentType("application/json;charset=UTF-8");
		JSONObject json = null;
		try {
			json = new JSONObject(jsonObj);
			String name=json.getString("name");
			 String password=json.getString("password"); 
			 String emailId=json.getString("emailId"); String mobile=json.getString("mobile");
			 Customer customer=new Customer(); 
			 customer.setName(name);
			 customer.setPassword(password); 
			 customer.setUserName(emailId);
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