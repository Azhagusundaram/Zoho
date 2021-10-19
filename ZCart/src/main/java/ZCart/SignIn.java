package ZCart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends HttpServlet {
	
	ZCartDriver driver=ZCartDriver.getInstance();
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter writter=response.getWriter();
		String jsonString=request.getParameter("jsonObject");
		JSONObject json;
		try {
			json = new JSONObject(jsonString);
			String userName=json.getString("userName");
			String password=json.getString("password");
			boolean output;
			if(userName.equals("admin@zoho.com")){
				output=driver.adminLogin(userName, password);
				if(output){
					 writter.print("admin");
					 HttpSession session=request.getSession();
					session.setAttribute("userName",userName);
					
				}else{
					 writter.print(output);
				}
			}else{
				output=driver.userLogin(userName, password);	
				if(output){
					writter.print("user");
					HttpSession session=request.getSession();
					session.setAttribute("userName",userName);
				}else{
					 writter.print(output);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
