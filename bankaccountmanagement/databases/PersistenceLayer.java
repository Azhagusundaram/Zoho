package com.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import com.bankaccountmanagement.AccountInfo;
import com.bankaccountmanagement.CustomerInfo;
public interface PersistenceLayer{
    ArrayList<Integer> uploadCustomerInfo(ArrayList<ArrayList> customer)throws SQLException ;
    void deleteCustomerInfo(int customerId)throws SQLException;
    long uploadAccountInfo(AccountInfo account) throws SQLException;
    ArrayList<CustomerInfo> setCustomerInfo() throws SQLException;
    ArrayList<AccountInfo> setAccountInfo() throws SQLException;
    ArrayList<AccountInfo>setDeactivatedAccountInfo()throws SQLException;
    void cleanUp() throws SQLException;
    void deactivateAccount(long accountNumber)throws SQLException;
    void deactivateCustomerId(int customerId)throws SQLException;
    void activateAccount(long accountNumber) throws SQLException;
    void activateCustomerId(int customerId)throws SQLException;
    void depositAmount(long accountNumber,double amount)throws SQLException;
    void withdrawlAmount(long accountNumber,double amount)throws SQLException;
}
