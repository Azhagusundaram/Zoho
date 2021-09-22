package com.bankaccountmanagement;


import com.databases.PersistenceLayer;
import com.databases.SqlDataBase;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class ProgramDriver {
    PersistenceLayer dataBase = new SqlDataBase();
//        public void setStorage() {
//            try (FileReader fileReader = new FileReader("interfaces.properties")){
//                Properties prop=new Properties();
//                prop.load(fileReader);
//                String className=prop.getProperty("mysql");
//                Class c1 = Class.forName(className);
//                dataBase = (PersistenceLayer) c1.newInstance();
//            } catch ( IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }

    public HashMap<String,ArrayList<ArrayList>> addNewCustomer(ArrayList<ArrayList>customers) throws DataErrorException {
        HashMap<String, ArrayList<ArrayList>> newCustomer = new HashMap();
        ArrayList<ArrayList> successCustomerDetails = new ArrayList<>();
        ArrayList<ArrayList> failureCustomerDetails = new ArrayList<>();
            if(customers!=null){
                try {
                    ArrayList<Integer> customerIds = dataBase.uploadCustomerInfo(customers);
                    for (int i = 0; i < customerIds.size(); i++) {
                        ArrayList temp = customers.get(i);
                        CustomerInfo tempCustomer = (CustomerInfo) temp.get(0);
                        AccountInfo tempAccount = (AccountInfo) temp.get(1);
                        ArrayList customerAccount = new ArrayList();
                        if (customerIds.get(i) != 0) {
                            int customerId = customerIds.get(i);
                            tempCustomer.setCustomerId(customerId);
                            tempAccount.setCustomerId(customerId);
                            Long accountNumber = dataBase.uploadAccountInfo(tempAccount);
                            if (accountNumber != 0) {
                                tempAccount.setAccountNumber(accountNumber);
                                setCustomerHashMap(tempCustomer);
                                setAccountHashMap(tempAccount);
                                customerAccount.add(tempCustomer);
                                customerAccount.add(tempAccount);
                                successCustomerDetails.add(customerAccount);
                                newCustomer.put("Success", successCustomerDetails);
                            } else {
                                try{
                                    dataBase.deleteCustomerInfo(customerId);
                                    tempCustomer.setCustomerId(0);
                                    tempAccount.setCustomerId(0);
                                    customerAccount.add(tempCustomer);
                                    customerAccount.add(tempAccount);
                                    failureCustomerDetails.add(customerAccount);
                                    newCustomer.put("Failure", failureCustomerDetails);
                                }catch (SQLException e){
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            customerAccount.add(tempCustomer);
                            customerAccount.add(tempAccount);
                            failureCustomerDetails.add(customerAccount);
                            newCustomer.put("Failure", failureCustomerDetails);
                        }
                    }

                } catch (SQLException e){
                    e.printStackTrace();
                    throw new DataErrorException("Error in Customer Details");
                }
            }
        return newCustomer;
    }
    public HashMap<String,AccountInfo> addNewAccount(AccountInfo account) throws DataErrorException{
        HashMap<String,AccountInfo> newAccount=new HashMap<>();
        if(account!=null) {
            try {
                Long accountNumber = dataBase.uploadAccountInfo(account);
                if (accountNumber != 0) {
                    account.setAccountNumber(accountNumber);
                    setAccountHashMap(account);
                    newAccount.put("Success", account);
                } else {
                    newAccount.put("Failure", account);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DataErrorException("Error in Add Account");
            }
        }
        return newAccount;
    }
    public void setDataBase() throws DataErrorException{
            try{
                ArrayList<CustomerInfo> customer=dataBase.setCustomerInfo();
                if(customer.isEmpty()){
                    throw new DataErrorException("Customer database is empty");
                }
                setCustomerHashMap(customer);

            }catch (SQLException e){
                e.printStackTrace();
                throw new DataErrorException("Error in retrive details from customer database");
            }
            try{
                ArrayList<AccountInfo> account=dataBase.setAccountInfo();
                if (account.isEmpty()){
                    throw new DataErrorException("Account database is empty");

                }
                setAccountHashMap(account);

            }catch (SQLException e){
                e.printStackTrace();
                throw new DataErrorException("Error in retrive details from activated account database");
            }
//            try{
//                ArrayList<AccountInfo>deactivatedAccount=dataBase.setDeactivatedAccountInfo();
//                if(deactivatedAccount.isEmpty()){
//                    throw new DataErrorException("There is No deactivated Account");
//
//                }
//                setDeactivatedHashMap(deactivatedAccount);
//            }catch (SQLException e){
//                e.printStackTrace();
//                throw new DataErrorException("Error in retrive details from deactivated account database");
//            }
    }
    public void setCustomerHashMap(CustomerInfo customer){
        AccountManagement.OBJECT.setUserDetails(customer);
    }
    public void setAccountHashMap(AccountInfo account){
        AccountManagement.OBJECT.setAccountDetails(account);
    }
    public void setCustomerHashMap( ArrayList<CustomerInfo>customers){
        for(CustomerInfo customer:customers){
            setCustomerHashMap(customer);
        }
    }
    public void setAccountHashMap(ArrayList<AccountInfo>accounts){
        for(AccountInfo account:accounts){
            setAccountHashMap(account);
        }
    }
    public void setDeactivatedHashMap(ArrayList<AccountInfo>accounts){
            for(AccountInfo account:accounts){
                setDeactivatedHashMap(account);
            }
    }
    public void setDeactivatedHashMap(AccountInfo account){
            AccountManagement.OBJECT.addDeactivateAccountDetails(account);
    }
    public void depositAmountDataBase(long accountNumber,double amount)throws DataErrorException{
        try {
            dataBase.depositAmount(accountNumber,amount);
        }catch (SQLException e){
            e.printStackTrace();
            throw new DataErrorException("Error in deposit Amount");
        }
    }
    public void withdrawlAmountDataBase(long accountNumber,double amount)throws DataErrorException{
            try {
                dataBase.withdrawlAmount(accountNumber,amount);
            }catch (SQLException e){
                e.printStackTrace();
                throw new DataErrorException("Error in Withdrawl amount");
            }
    }
    public void depositAmount(long accountNumber,int customerId,double amount)throws DataErrorException{
        HashMap<Long,AccountInfo> tempAccount=AccountManagement.OBJECT.getAccount(customerId);
        AccountInfo account=tempAccount.get(accountNumber);
        account.setBalance(amount);
    }
    public void withdrawlAmount(long accountNumber,int customerId,double amount)throws DataErrorException{
        HashMap<Long,AccountInfo> tempAccount=AccountManagement.OBJECT.getAccount(customerId);
        AccountInfo account=tempAccount.get(accountNumber);
        account.setBalance(-amount);
    }
    public boolean checkWithdrawl(long accountNumber,int customerId,double amount)throws DataErrorException{
        HashMap<Long,AccountInfo> tempAccount=AccountManagement.OBJECT.getAccount(customerId);
        AccountInfo account=tempAccount.get(accountNumber);
        double balance=account.getBalance();
        return balance<amount;
    }
    public void deleteAccount(long accountNumber)throws DataErrorException{
            try {
                dataBase.deactivateAccount(accountNumber);
//                deleteAccountHashMap(accountNumber,customerId);
            }catch (SQLException e){
                e.printStackTrace();
                throw new DataErrorException("Account is not closed");
            }
    }
    public void deleteAllAccounts(int customerId)throws DataErrorException{
            try{
                HashMap<Long,AccountInfo>accountInfoHashMap=AccountManagement.OBJECT.getAccount(customerId);
                Set<Long> accountNumbers=new HashSet<>();
                accountNumbers.addAll(accountInfoHashMap.keySet());
                for (Long accountNumber:accountNumbers){
                    deleteAccount(accountNumber);
                }
            }catch (Exception e){
                e.printStackTrace();
                throw new DataErrorException("Account is not closed");
            }
    }

    public void deleteAccountHashMap(long accountNumber,int customerId)throws DataErrorException{
         AccountInfo account=AccountManagement.OBJECT.getAccount(customerId).get(accountNumber);
        AccountManagement.OBJECT.deleteAccountDetails(accountNumber,customerId);
        AccountManagement.OBJECT.addDeactivateAccountDetails(account);
    }
    public void closeConnection()throws DataErrorException{
            try {
                dataBase.cleanUp();
            }catch(SQLException e){
                e.printStackTrace();
                throw new DataErrorException("Connection not Closed");
            }
    }
    public String deleteCustomer(int customerId)throws DataErrorException{
        HashMap<Long,AccountInfo> tempAccount=AccountManagement.OBJECT.getAccount(customerId);
        System.out.print(tempAccount.toString());
       try{
           if(tempAccount.isEmpty()){
               dataBase.deactivateCustomerId(customerId);
               deleteCustomerHashMap(customerId);
               return "customer id deleted";
           }
       }catch (SQLException e){
           e.printStackTrace();
           throw new DataErrorException("CustomerId is not closed");
       }
	return null;
    }
    public void activateAccount(int customerId,long accountNumber)throws DataErrorException{
           try{
               dataBase.activateAccount(accountNumber);
               activateAccountHashMap(customerId,accountNumber);
           }catch (SQLException e){
               e.printStackTrace();
               throw new DataErrorException("Error in Activate Account");
           }
    }
    public void activateCustomerId(int customerId)throws DataErrorException{
            try{
                dataBase.activateCustomerId(customerId);
            }catch (SQLException e){
                e.printStackTrace();
                throw new DataErrorException("Error in Activate Customer ID");
            }
    }
    public void activateAccountHashMap(int customerId, long accountNumber)throws DataErrorException{
        AccountInfo account=AccountManagement.OBJECT.getDeactivatedAccount(customerId).get(accountNumber);
        setAccountHashMap(account);
        AccountManagement.OBJECT.deleteActivatedAccount(customerId,accountNumber);
    }

    public void deleteCustomerHashMap(int customerId){
        AccountManagement.OBJECT.deleteCustomerDetails(customerId);
    }
    public boolean checkCustomerId(int customerId)throws DataErrorException{
        return AccountManagement.OBJECT.getCustomerAccount().containsKey(customerId);
    }
    public boolean checkDeactivatedCustomerId(int customerId)throws DataErrorException{

            return AccountManagement.OBJECT.getDeactivatedCustomer().containsKey(customerId);
    }
    public boolean checkAccountNumber(int customerId,long accountNumber)throws DataErrorException{
            HashMap<Long,AccountInfo>account=AccountManagement.OBJECT.getAccount(customerId);
        return account.containsKey(accountNumber);
    }
    public boolean checkDeactivatedAccount(int customerId,long accountNumber)throws DataErrorException{
            return AccountManagement.OBJECT.getDeactivatedAccount(customerId).containsKey(accountNumber);
    }
    public String getAccount(long accountNumber,int customerId) throws DataErrorException{
        HashMap <Long,AccountInfo> individualAccount = AccountManagement.OBJECT.getAccount(customerId);
        AccountInfo temp= individualAccount.get(accountNumber);
        if(temp==null) {
            return "Invalid Account Number";
        }
        return temp.toString();
    }
    public ArrayList<String> getAccount(int customerId)throws DataErrorException {
        HashMap <Long,AccountInfo> individualAccount = AccountManagement.OBJECT.getAccount(customerId);
        ArrayList<String> array=new ArrayList<>(individualAccount.size());
        if(individualAccount.size()==0){
            array.add("No account for this customer Id");
            return array;
        }
        Iterator iterate=individualAccount.entrySet().iterator();
        while(iterate.hasNext()){
            Map.Entry<Long,AccountInfo> map = (Map.Entry<Long, AccountInfo>) iterate.next();
            array.add(map.getValue().toString());
        }
        return array;
    }

}
