package zcoinexchange;

import java.util.List;
import java.util.Map;

public class ProgramDriver {
   private ZCoinManagement cache=new ZCoinManagement();
    private int zId=1;
    private double rcToZcConversionRate=0.5;
    public void addUserAccount(User user){
        cache.setUser(user);
    }
    public void addAdminAccount(Admin admin){
        cache.setAdmin(admin);
    }
    public boolean checkUserLoginCredentials(String mailId, String password) {
        User user=cache.getUserDetails().get(mailId);
        return checkCredentials(password, user);
    }
    public boolean checkAdminLoginCredentials(String mailId,String password){
        Admin admin=cache.getAdminDetails().get(mailId);
        return checkCredentials(password,admin);
    }

    private boolean checkCredentials(String password,Login user) {
        if(user ==null){
            return false;
        }
        String oldPassword= user.getPassword();
        return oldPassword.equals(password);
    }
    public String checkStatus(String mailId){
        User user=cache.getUserDetails().get(mailId);
        String status=user.getStatus();
        return status;
    }
    public int getZid(String mailId){
        User user=cache.getUserDetails().get(mailId);
        return user.getzId();
    }
    public User getUser(){
        List<String>emailIds=cache.getUsersForApproval();
        Map<String,User>userDetails= cache.getUserDetails();
        for (String emailId:emailIds){
            User user=userDetails.get(emailId);
            return user;
        }
        return null;
    }
    public void allocateZid(User user,boolean status){
        List<String>emailIds=cache.getUsersForApproval();
        String mailId=user.getMailId();
        if(status){
            user.setZId(zId);
            user.setStatus("Approved");
            cache.setZidAccounts(zId,mailId);
            zId++;
        }else {
            user.setStatus("Rejected");
        }
        emailIds.remove(mailId);
    }
    public List<String> getAllTransactions(int zid, int currency){
        String emailId=cache.getZidAccounts().get(zid);
        if(emailId==null){
            return null;
        }
        User user=cache.getUserDetails().get(emailId);
        if(currency ==1){
            return user.getRcTransaction();
        }else {
            return user.getZcTransaction();
        }
    }
    public double getBalance(int zid, int currency){
        String emailId=cache.getZidAccounts().get(zid);
        User user=cache.getUserDetails().get(emailId);
        if(currency==1){
           return  user.getRcAmount();
        }
        else {
            return user.getZcAmount();
        }
    }
    public void changePassword(String password,String mailId){
        User user=cache.getUserDetails().get(mailId);
        user.setPassword(password);
    }
    public List<String> viewTransaction(String mailId, int currency){
        User user=cache.getUserDetails().get(mailId);
        if(currency==1){
            return user.getRcTransaction();
        }

        else {
            return user.getZcTransaction();
        }
    }
    public boolean depositRc(String mailId, double amount){
        User user=cache.getUserDetails().get(mailId);
        int zid= user.getzId();
        user.setRcAmount(amount);
        user.setRcTransaction(" --- \t"+zid+"\t+"+amount);
        return true;
    }
    public boolean withdrawRc(String mailId, double amount){
        User user=cache.getUserDetails().get(mailId);
        int zid= user.getzId();
        double totalAmount=user.getRcAmount();
        if(totalAmount>amount){
            user.setRcAmount(-amount);
            user.setRcTransaction(zid+"\t --- "+"\t-"+amount);
            return true;
        }
        return false;
    }
    public void setRcToZcConversionRate(double rate){
        rcToZcConversionRate=rate;
    }
    public boolean transferZc(int zId2, String mailId1, int amount){
        User user1=cache.getUserDetails().get(mailId1);
        int zId1=user1.getzId();
        double totalAmount=user1.getZcAmount();
        String mailId2=cache.getZidAccounts().get(zId2);
        User user2=cache.getUserDetails().get(mailId2);
        if(user2==null){
            return false;
        }
        if(totalAmount>amount){
            user2.setZcAmount(amount);
            user1.setZcTransaction(zId1+"\t"+zId2+"\t-"+amount);
            user2.setZcTransaction(zId1+"\t"+zId2+"\t+"+amount);
            return true;
        }
        return false;
    }
    public Double withdrawZcToRc(String emailId, int amount){
        User user=cache.getUserDetails().get(emailId);
        double totalAmount=user.getZcAmount();
        int zId=user.getzId();
        if(totalAmount>amount){
            double conversionAmount=amount/rcToZcConversionRate;
            double commissionAmount=conversionAmount*0.15;
            double finalAmount=conversionAmount-commissionAmount;
            user.setZcTransaction(zId+"\t---"+"\t-"+amount);
            return finalAmount;
        }
        return null;
    }
    public boolean convertRcToZc(String mailId, int amount){
        User user=cache.getUserDetails().get(mailId);
        double totalAmount=user.getRcAmount();
        int zId=user.getzId();
        if(totalAmount>amount){
            double conversionZcAmount=amount*rcToZcConversionRate;
            user.setRcAmount(-amount);
            user.setZcAmount(conversionZcAmount);
            user.setRcTransaction(zId+"\t"+zId+"\t-"+amount);
            user.setZcTransaction(zId+"\t"+zId+"\t+"+conversionZcAmount);
            return true;
        }
        return false;
    }
    public boolean checkZid(int zid){
        return cache.getZidAccounts().containsKey(zid);
    }
    public boolean checkZid(int zid,String mailId){
        User user=cache.getUserDetails().get(mailId);
        return zid== user.getzId();
    }

}
