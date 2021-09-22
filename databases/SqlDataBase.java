package com.databases;

import java.sql.*;
import java.util.ArrayList;

import com.bankaccountmanagement.CustomerInfo;
import com.bankaccountmanagement.AccountInfo;
import com.bankaccountmanagement.Helper;


public class SqlDataBase implements PersistenceLayer {
	private Connection connect;
	public SqlDataBase(){
		try {
			setConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<Integer> uploadCustomerInfo(ArrayList<ArrayList> customer) throws SQLException {
			setConnection();
			try {
				int number = customer.size();
				ArrayList<Integer> customerIds = new ArrayList<>(number);
				String sql = "insert into Customer_Info (Name,Address, PhoneNumber,ActiveStatus) values(?,?,?,?);";
				PreparedStatement prepState = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ResultSet result = null;
				try {
					for (int i = 0; i < number; i++) {
						try {
							ArrayList totalCustomerInfo = customer.get(i);
							CustomerInfo tempCustomer = (CustomerInfo) totalCustomerInfo.get(0);
							prepState.setString(1, tempCustomer.getName());
							prepState.setString(2, tempCustomer.getAddress());
							prepState.setLong(3, tempCustomer.getPhoneNumber());
							prepState.setInt(4,1);
							prepState.addBatch();
							prepState.executeUpdate();
							result = prepState.getGeneratedKeys();
							result.next();
							int id = result.getInt(1);
							customerIds.add(id);
						} catch (SQLException e) {
							customerIds.add(0);
						}
					}
				} finally {
					prepState.close();
					result.close();
				}
				return customerIds;
			}
			catch (SQLException e){
				System.out.println(e);
				throw e;
			}

	}

	public void deleteCustomerInfo(int customerId)throws SQLException{
		setConnection();
		String sql="Delete from Customer_Info where CustomerId=?";

		try (PreparedStatement prepState =connect.prepareStatement(sql)){
			prepState.setInt(1,customerId);
			prepState.executeUpdate();
		}
		catch (SQLException e){
			throw e;
		}
	}
	public void deactivateAccount(long accountNumber)throws SQLException{
		setConnection();
		String sql="update Account_Info set ActiveStatus=0 where AccountNumber="+accountNumber;
		try( Statement state = connect.createStatement();){
			state.executeUpdate(sql);
		}
		catch (SQLException e){
			throw e;
		}
	}
	public void activateAccount(long accountNumber)throws SQLException{
		setConnection();
		String sql="update Account_Info set ActiveStatus=1 where AccountNumber="+accountNumber;
		try( Statement state = connect.createStatement();){
			state.executeUpdate(sql);
		}
		catch (SQLException e){
			throw e;
		}
	}
	public void deactivateCustomerId(int customerId)throws SQLException{
		setConnection();
		String sql="update Customer_Info set ActiveStatus=0 where CustomerId="+customerId;
		try( Statement state = connect.createStatement();){
			state.executeUpdate(sql);
		}
		catch (SQLException e){
			throw e;
		}
	}
	public void activateCustomerId(int customerId)throws SQLException{
		setConnection();
		String sql="update Customer_Info set ActiveStatus=1 where CustomerId="+customerId;
		try( Statement state = connect.createStatement();){
			state.executeUpdate(sql);
		}
		catch (SQLException e){
			throw e;
		}
	}
	public void depositAmount(long accountNumber,double amount)throws SQLException{
		setConnection();
		String sql="update Account_Info set Balance=Balance+? where AccountNumber=?";
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setDouble(1,amount);
			prepState.setLong(2,accountNumber);
			prepState.executeUpdate();
		}
		catch (SQLException e){
			throw e;
		}
	}
	public void withdrawlAmount(long accountNumber,double amount)throws SQLException{
		setConnection();
		String sql="update Account_Info set Balance=Balance-? where AccountNumber=?";
		try(PreparedStatement prepState=connect.prepareStatement(sql)){
			prepState.setDouble(1,amount);
			prepState.setLong(2,accountNumber);
			prepState.executeUpdate();
		}
		catch (SQLException e){
			throw e;
		}
	}
	public long uploadAccountInfo(AccountInfo account) throws SQLException {
		setConnection();
		String sql="insert into Account_Info (CustomerId, Balance,ActiveStatus) values(?,?,?)";
		long accountNumber;
		PreparedStatement prepState=connect.prepareStatement (sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet result=null;
		try{
				prepState.setInt(1,account.getCustomerId() );
				prepState.setDouble(2,account.getBalance());
				prepState.setInt(3,1);
				prepState.executeUpdate();
				result=prepState.getGeneratedKeys();
				result.next();
				accountNumber=result.getInt(1);
		}
		catch (SQLException e){
			return 0;
		}
		finally {

			try{
				prepState.close();
				result.close();
			}
			catch (SQLException e){

			}
		}
		return accountNumber;
	}
	public ArrayList<CustomerInfo> setCustomerInfo() throws SQLException {
		setConnection();
		ArrayList<CustomerInfo> customer=new ArrayList<>();
		try(PreparedStatement prepState=connect.prepareStatement("Select * from Customer_Info where ActiveStatus=1");
			ResultSet result=prepState.executeQuery()){
			while(result.next()) {
				String name=result.getString("Name");
				int id=result.getInt("CustomerId");
				String address=result.getString("Address");
				long phone=result.getLong("PhoneNumber");
				customer.add(Helper.getCustomerInfo(name, id, address, phone));
			}
		}
		catch (SQLException e){
			throw e;
		}
		return customer;
	}
	public ArrayList<AccountInfo> setAccountInfo() throws SQLException {
		setConnection();
		ArrayList<AccountInfo> account=new ArrayList<>();
		try(PreparedStatement prepState=connect.prepareStatement("Select * from Account_Info where ActiveStatus=1");
			ResultSet result=prepState.executeQuery()){
			while(result.next()) {
				long number=result.getLong("AccountNumber");
				int id=result.getInt("CustomerId");
				double balance=result.getDouble("Balance");
				account.add(Helper.getAccountInfo(number, id, balance));
			}
		}
		catch (SQLException e){
			throw e;
		}
		return account;
	}
	public ArrayList<AccountInfo> setDeactivatedAccountInfo() throws SQLException {
		setConnection();
		ArrayList<AccountInfo> account=new ArrayList<>();
		try(PreparedStatement prepState=connect.prepareStatement("Select * from Account_Info where ActiveStatus=0");
			ResultSet result=prepState.executeQuery()){
			while(result.next()) {
				long number=result.getLong("AccountNumber");
				int id=result.getInt("CustomerId");
				double balance=result.getDouble("Balance");
				account.add(Helper.getAccountInfo(number, id, balance));
			}
		}
		catch (SQLException e){
			throw e;
		}
		return account;
	}
	public void setConnection() throws SQLException {
		try{
			if(connect==null) {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdatabase","root","Root@123");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public void cleanUp() throws SQLException {
		try {
			connect.close();
		}catch (SQLException e){
			throw e;
		}

	}

}

