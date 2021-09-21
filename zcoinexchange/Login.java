package zcoinexchange;

public class Login {
    private String Name;
    private String mailId;
    private long mobileNumber;
    private int H_Id;
    private String password;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getH_Id() {
        return H_Id;
    }

    public void setH_Id(int h_Id) {
        H_Id = h_Id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
