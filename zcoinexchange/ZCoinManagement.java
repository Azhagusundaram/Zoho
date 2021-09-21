package zcoinexchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZCoinManagement {
    private Map<String,User> userDetails =new HashMap<>();
    private Map<String,Admin>adminDetails=new HashMap<>();
    private Map<Integer,String>zidAccounts=new HashMap<>();
    private List<String>usersForApproval=new ArrayList<>();

    public List<String> getUsersForApproval() {
        return usersForApproval;
    }

    public Map<String, User> getUserDetails() {
        return userDetails;
    }

    public Map<String, Admin> getAdminDetails() {
        return adminDetails;
    }

    public Map<Integer, String> getZidAccounts() {
        return zidAccounts;
    }
    public void setUser(User user){
        String mailId=user.getMailId();
        userDetails.put(mailId,user);
        usersForApproval.add(mailId);
    }
    public void setAdmin(Admin admin){
        String mailId=admin.getMailId();
        adminDetails.put(mailId,admin);
    }
    public void setZidAccounts(int zid,String mailId){
        zidAccounts.put(zid,mailId);
    }
}
