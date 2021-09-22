

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankaccountmanagement.AccountInfo;
import com.bankaccountmanagement.AccountManagement;
import com.bankaccountmanagement.CustomerInfo;
import com.bankaccountmanagement.DataErrorException;
import com.bankaccountmanagement.Helper;
import com.bankaccountmanagement.ProgramDriver;

public class Servlets extends HttpServlet {
	ProgramDriver driver= new ProgramDriver();
	public Servlets() {
		try {
			driver.setDataBase();
		} catch (DataErrorException e1) {
			e1.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		String  str=req.getParameter("path");
		if(str.equals("Customers")) {
			
			RequestDispatcher request=req.getRequestDispatcher("Customers.jsp");
			try {
				addAttribute(req,res,str);
			} catch (DataErrorException e) {
				e.printStackTrace();
			}
			request.forward(req,res);
		}else if(str.equals("Accounts")) {
			RequestDispatcher request=req.getRequestDispatcher("Accounts.jsp");
			try {
				addAttribute(req,res,str);
			} catch (DataErrorException e) {
				e.printStackTrace();
			}
			request.forward(req,res);
		}
		else if(str.equals("Transactions")) {
			RequestDispatcher request=req.getRequestDispatcher("Transactions.jsp");
			
		request.forward(req,res);
		}
		else if(str.equals("AddAccount")) {
			RequestDispatcher request=req.getRequestDispatcher("AddAccount.jsp");
			request.forward(req, res);
		}
		else if(str.equals("AddCustomer")) {
			RequestDispatcher request=req.getRequestDispatcher("AddCustomer.jsp");
			
			request.forward(req, res);
		}
		else if(str.equals("addCustomer")) {
			addCustomer(req,res);
		}else if(str.equals("addAccount")) {
			addAccount(req,res);
		}
		else if(str.equals("DeleteCustomer")) {
			deleteCustomer(req,res);
		}else if(str.equals("DeleteAccount")) {
			deleteAccount(req,res);
			try {
				driver.setDataBase();
			} catch (DataErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void deleteCustomer(HttpServletRequest req,HttpServletResponse res)throws IOException, ServletException {
		String[] allCustomerIds=req.getParameterValues("customerId");
		PrintWriter writter=res.getWriter();
		
		
		for(int i=0;i<allCustomerIds.length;i++) {
			int customerId=Integer.parseInt(allCustomerIds[i]);
			try {
				
				driver.deleteAllAccounts(customerId);
				String result=driver.deleteCustomer(customerId);
				writter.print(result);
				
				
			} catch (DataErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
			}
		}
	}
	public void deleteAccount(HttpServletRequest req,HttpServletResponse res)throws IOException, ServletException {
		String[] allAccountNumbers=req.getParameterValues("accountNumber");
		PrintWriter writter=res.getWriter();
		for(int i=0;i<allAccountNumbers.length;i++) {
			long accountNumber=Long.parseLong(allAccountNumbers[i]);
			try {
				driver.deleteAccount( accountNumber);	
			} catch (DataErrorException e) {
				// TODO Auto-generated catch block
				writter.println(e);
				e.printStackTrace();
			}
		}
		
	}

	public void addAccount(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		int customerId=Integer.parseInt(req.getParameter("customerId"));
		double amount=Long.parseLong(req.getParameter("amount"));
		PrintWriter writter=res.getWriter();
		try {
			if(driver.checkCustomerId(customerId)){
				
				AccountInfo account = Helper.getAccountInfo(customerId, amount);
				HashMap<String,AccountInfo> newAccount=driver.addNewAccount(account);
				if (newAccount.isEmpty()){
					
					writter.print("Account ArrayList is Null");
				}else {
					writter.print(newAccount);
				}
			}else if(driver.checkDeactivatedCustomerId(customerId)){
				writter.print("Your customer Id is Deactivated. So  activate the customer Id");
			}
			else{
				writter.print("Invalid customer id");
			}
		} catch (DataErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addCustomer(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
			ArrayList list=new ArrayList();
			ArrayList<ArrayList> array=new ArrayList();
			String  name=req.getParameter("name");
			String emailId=req.getParameter("emailid");
			long phoneNumber=Long.parseLong(req.getParameter("phone"));
			double amount=Double.parseDouble(req.getParameter("amount"));
			CustomerInfo customer=Helper.getCustomerInfo(name, emailId, phoneNumber);
			AccountInfo account=Helper.getAccountInfo(0, amount);
			list.add(customer);
			list.add(account);
			array.add(list);
			try {
				HashMap<String,ArrayList<ArrayList>>map=driver.addNewCustomer(array);
				PrintWriter writter=res.getWriter();
				writter.print(map);
			} catch (DataErrorException e) {
				e.printStackTrace();
			}
	}
	
	public void addAttribute(HttpServletRequest req,HttpServletResponse res,String str) throws DataErrorException {
		List array=null;
		if(str.equals("Customers")) {
			req.removeAttribute("customers");
			array=AccountManagement.OBJECT.getCustomerList();
			req.setAttribute("customers", array);
			
		}
		else if(str.equals("Accounts")) {
			
			array=AccountManagement.OBJECT.getAccountList();
			req.setAttribute("accounts", array);
		}
		
	}
	

}