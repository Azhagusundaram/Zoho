package com.bankaccountmanagement;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public enum AccountManagement {
    OBJECT;
    private  HashMap <Integer,CustomerInfo> customerHashMap =new HashMap<>();
    private  HashMap<Integer,HashMap<Long,AccountInfo>> accountHashMap =new HashMap<>();
    private  HashMap<Integer, HashMap<Long,AccountInfo>>deactivateAccountHashMap=new HashMap<>();
    private List <CustomerInfo> customerList=new ArrayList<>();
    private List<AccountInfo>accountList=new ArrayList<>();
    public void setUserDetails(CustomerInfo customer) {
        customerHashMap.put(customer.getCustomerId(),customer);
        customerList.add(customer);
    }
    public HashMap<Long,AccountInfo> getAccount(int customerId) throws DataErrorException{
    	
        HashMap<Long,AccountInfo>account=accountHashMap.get(customerId);
        System.out.println(accountHashMap.toString());
        System.out.println(account);
        if(account==null){
            throw new DataErrorException("Error in Account Retrive");
        }
        return account;
    }
    public List getAccountList() {
    
    	 return accountList;
    }
    public List getCustomerList() {
    	return customerList;
    }
    public HashMap<Integer,CustomerInfo> getCustomerAccount() throws DataErrorException{
        if(customerHashMap==null){
            throw new DataErrorException("Error in Customer Details Retrive");
        }
        return customerHashMap;
    }
    public HashMap<Integer, HashMap<Long,AccountInfo>>getDeactivatedCustomer()throws DataErrorException{
        if(deactivateAccountHashMap==null){
            throw new DataErrorException("Error in deactivated customer details retrive");
        }
        return deactivateAccountHashMap;
    }
    public HashMap<Long,AccountInfo>getDeactivatedAccount(int customerId)throws DataErrorException{
        HashMap<Long,AccountInfo>deactivatedAccount=deactivateAccountHashMap.get(customerId);
        if(deactivatedAccount==null){
            throw new DataErrorException("Error in deactivated account details retrive");
        }
        return deactivatedAccount;
    }
    public void setAccountDetails(AccountInfo account){
        int tempCustomerId= account.getCustomerId();
        HashMap<Long,AccountInfo> accountDetails=accountHashMap.get(tempCustomerId);
        if(accountDetails==null) {
            accountDetails=new HashMap<>();
            accountHashMap.put(account.getCustomerId(),accountDetails);
        }
        accountDetails.put(account.getAccountNumber(),account);
        accountList.add(account);

    }
    public void deleteAccountDetails(long accountNumber,int customerId){
        accountHashMap.get(customerId).remove(accountNumber);
    }
    public void addDeactivateAccountDetails(AccountInfo account){
        int tempCustomerId= account.getCustomerId();
        HashMap<Long,AccountInfo> accountDetails=deactivateAccountHashMap.get(tempCustomerId);
        if(accountDetails==null){
            accountDetails=new HashMap<>();
            deactivateAccountHashMap.put(account.getCustomerId(),accountDetails);
        }
        accountDetails.put(account.getAccountNumber(),account);
    }
    public void deleteActivatedAccount(int customerId,long accountNumber){
        HashMap<Long,AccountInfo>hashMap=deactivateAccountHashMap.get(customerId);
        hashMap.remove(accountNumber);
        if(hashMap.isEmpty()){
            deactivateAccountHashMap.remove(customerId);
        }
    }
    public void deleteCustomerDetails(int customerId){
        customerHashMap.remove(customerId);
        accountHashMap.remove(customerId);
    }
}
