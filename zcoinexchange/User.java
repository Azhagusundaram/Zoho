package zcoinexchange;

import java.util.ArrayList;
import java.util.List;

public class User extends Login{
    private double rcAmount=0;
    private double zcAmount=0;
    private List<String> rcTransaction=new ArrayList<>();
    private List<String>zcTransaction=new ArrayList<>();
    private int zId;
    private String status;

    public double getRcAmount() {
        return rcAmount;
    }

    public void setRcAmount(double rcAmount) {
        this.rcAmount+= rcAmount;
    }

    public double getZcAmount() {
        return zcAmount;
    }

    public void setZcAmount(double zcAmount) {
        this.zcAmount+= zcAmount;
    }

    public List<String> getRcTransaction() {
        return rcTransaction;
    }

    public void setRcTransaction(String rcTransaction) {
        this.rcTransaction.add(rcTransaction);
    }

    public List<String> getZcTransaction() {
        return zcTransaction;
    }

    public void setZcTransaction(String zcTransaction) {
        this.zcTransaction.add(zcTransaction);
    }

    public int getzId() {
        return zId;
    }

    public void setZId(int zId) {
        this.zId = zId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String toString(){
        return getName()+" "+getMailId()+" "+getMobileNumber()+" "+getRcAmount()+" "+getH_Id();
    }
}
//1
//2
//asd
//asd
//Azhagu3#
//1
//1
//1
//1
//qw
//qw
//Azhagu3#
//1
//1
//500
