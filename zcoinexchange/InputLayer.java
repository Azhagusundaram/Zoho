package zcoinexchange;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputLayer {
    public static void main(String[] args) {
        ZCoinExchangeDriver driver =new ZCoinExchangeDriver();
        Scanner scan=new Scanner(System.in);

        while (true){
            System.out.println("1.Add account\n2.Login\n3.Exit");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                System.out.println("1.User Account\n2.Admin Account");
                int decision1=scan.nextInt();
                scan.nextLine();
                if(decision1==1){
                    System.out.println("Name");
                    String name=scan.nextLine();
                    System.out.println("Email Id :");
                    String mailId=scan.nextLine();
                    System.out.println("Password");
                    String password=scan.nextLine();
                    password=checkPassword(password,scan,name,mailId);
                    System.out.println("H_Id");
                    int hid=scan.nextInt();
                    System.out.println("Mobile Number");
                    long mobile=scan.nextLong();
                    System.out.println("Initial Rc Deposit");
                    int amount=scan.nextInt();
                    scan.nextLine();
                    User user = getUser(name, mailId, password, hid, mobile,amount);
                    driver.addUserAccount(user);
                }else if(decision1==2){
                    System.out.println("Name");
                    String name=scan.nextLine();
                    System.out.println("Email Id :");
                    String mailId=scan.nextLine();
                    System.out.println("Password");
                    String password=scan.nextLine();
                    password=checkPassword(password,scan,name,mailId);
                    System.out.println("H_Id");
                    int hid=scan.nextInt();
                    System.out.println("Mobile Number");
                    long mobile=scan.nextLong();
                    scan.nextLine();
                    Admin admin = getAdmin(name, mailId, password, hid, mobile);
                    driver.addAdminAccount(admin);
                }

            }else if(decision==2){
                while (true){
                    System.out.println("1.User\n2.Admin\n3.GoBack");
                    int decision1=scan.nextInt();
                    scan.nextLine();
                    if(decision1==1){
                        System.out.println("mail Id");
                        String mailId=scan.nextLine();
                        System.out.println("Password");
                        String password=scan.nextLine();
                        if(driver.checkUserLoginCredentials(mailId,password)){
                            String status=driver.checkStatus(mailId);
                            System.out.println(status);
                            if(status.startsWith("A")){
                                int zid=driver.getZid(mailId);
                                System.out.println("User Zid is "+zid);
                                while (true){
                                    System.out.println("1.AccountDetails\n2.Transaction History\n3.change Password\n4.RC Transaction\n5.ZCoin transaction\n6.Go Back");
                                    int decision2=scan.nextInt();
                                    scan.nextLine();
                                    if(decision2==1){
                                        accountDetails(driver, scan,mailId);
                                    }else if(decision2==2){
                                        viewTransaction(driver, mailId);
                                    }else if(decision2==3){
                                        changePassword(driver, scan, mailId, password);

                                    }else if(decision2==4){
                                        doRcTransaction(driver, scan, mailId);
                                    }else if(decision2==5){
                                        doZcTransaction(driver, scan, mailId);
                                    }else if(decision2==6){
                                        break;
                                    }

                                }
                            }

                        }else {
                            System.out.println("wrong password or email Id");
                        }
                    }else if(decision1==2){
                        System.out.println("mail Id");
                        String mailId=scan.nextLine();
                        System.out.println("Password");
                        String password=scan.nextLine();
                        if(driver.checkAdminLoginCredentials(mailId,password)){
                            while (true){
                                System.out.println("1.ZE Approval\n2.view All Transaction\n3.set Rc to Zc Conversion Rate\n4.Go BAck ");
                                int decision2=scan.nextInt();
                                if(decision2==1){
                                    int decision3=1;
                                    while (decision3==1){
                                        User user=driver.getUser();
                                        if(user==null){
                                            System.out.println("No user for approval");
                                            break;
                                        }
                                        System.out.println(user);
                                        System.out.println("Are you approve User(1=yes/2=No)");
                                        decision3=scan.nextInt();
                                        if(decision3==1){
                                            driver.allocateZid(user,true);
                                        }else {
                                            driver.allocateZid(user,false);
                                        }
                                        System.out.println("Are yo continue approve user(1=Yes/2=No)");
                                        decision3=scan.nextInt();
                                    }

                                }else if(decision2==2){
                                    System.out.println("Enter the Zid of user");
                                    int zid=scan.nextInt();
                                    scan.nextLine();
                                    if(driver.checkZid(zid)){
                                        System.out.println("RC Transaction :");
                                        List<String>rcTransaction=driver.getAllTransactions(zid,1);
                                        printTransaction(rcTransaction);
                                        System.out.println("ZC Transaction:");
                                        List<String >zcTransaction=driver.getAllTransactions(zid,0);
                                        printTransaction(zcTransaction);
                                    }

                                }else if(decision2==3){
                                    System.out.println("Enter the Conversion Rate");
                                    double rate=scan.nextDouble();
                                    scan.nextLine();
                                    driver.setRcToZcConversionRate(rate);
                                }else if(decision2==4){
                                    break;
                                }else {
                                    System.out.println("Invalid Input");
                                }
                            }

                        }else {
                            System.out.println("wrong password or email Id");
                        }

                    }else if(decision1==3){
                        break;
                    }
                }

            }else if(decision==3){
                break;
            }else {
                System.out.println("Invalid Input");
            }
        }

    }

    private static void doZcTransaction(ZCoinExchangeDriver driver, Scanner scan, String mailId) {
        System.out.println("1.Deposit\n2.withdraw");
        int decision3= scan.nextInt();
        scan.nextLine();
        if(decision3==1){
            System.out.println("Deposit to(Zid)");
            int zid2= scan.nextInt();
            scan.nextLine();
            if(driver.checkZid(zid2)){
                System.out.println("Enter the amount");
                int amount = scan.nextInt();
                boolean result= driver.transferZc(zid2, mailId,amount);
                if(result){
                    System.out.println("Transfer successfully");
                }else {
                    System.out.println("You don't have enough money");
                }
            }else {
                System.out.println("Invalid zid");
            }
        }else if(decision3==2){
            System.out.println("Enter the amount");
            int amount = scan.nextInt();
            Double result= driver.withdrawZcToRc(mailId,amount);
            if(result==null){
                System.out.println("You don't have enough money");
            }else {
                System.out.println("Withdraw amount is "+result);
            }

        }else {
            System.out.println("Invalid input");
        }
    }

    private static void doRcTransaction(ZCoinExchangeDriver driver, Scanner scan, String mailId) {
        System.out.println("1.Deposit\n2.Withdraw\n3.Rc to Zc conversion");
        int decision3= scan.nextInt();
        scan.nextLine();
        if(decision3==1){
            System.out.println("Entre the amount");
            int amount= scan.nextInt();
            scan.nextLine();
            boolean result= driver.depositRc(mailId,amount);
            if(result){
                System.out.println("Deposit successfully");
            }else {
                System.out.println("deposit failure");
            }
        }else if(decision3==2){
            System.out.println("Enter the amount");
            int amount= scan.nextInt();
            scan.nextLine();
            boolean result= driver.withdrawRc(mailId,amount);
            if(result){
                System.out.println("withdraw successfully");
            }else {
                System.out.println("Amount exceed");
            }
        }else if(decision3==3){
            System.out.println("Enter the amount");
            int amount= scan.nextInt();
            scan.nextLine();
            boolean result=driver.convertRcToZc(mailId,amount);
            if(result){
                System.out.println("SuccessFully");
            }else {
                System.out.println("You don't have enough money");
            }
        }
        else {
            System.out.println("Invalid Input");
        }
    }

    private static void changePassword(ZCoinExchangeDriver driver, Scanner scan, String mailId, String password) {
        System.out.println("Enter Old Password");
        String oldPassword= scan.nextLine();
        if(driver.checkUserLoginCredentials(mailId,oldPassword)){
            String newPassword= scan.nextLine();
            newPassword=checkPassword(password, scan,newPassword, mailId);
            driver.changePassword(newPassword, mailId);
        }else {
            System.out.println("Password is wrong");
        }
    }

    private static void viewTransaction(ZCoinExchangeDriver driver, String mailId) {
        List<String>rcTransaction= driver.viewTransaction(mailId,1);
        System.out.println("Rc Transactions");
        printTransaction(rcTransaction);
        List<String>zcTransaction= driver.viewTransaction(mailId,0);
        System.out.println("Zc Transaction");
        printTransaction(zcTransaction);
    }

    private static void accountDetails(ZCoinExchangeDriver driver, Scanner scan, String mailId) {
        int zid;
        System.out.println("Enter the Zid");
        zid= scan.nextInt();
        if(driver.checkZid(zid,mailId)){
            double rcBalance= driver.getBalance(zid,1);
            System.out.println("Rc balance is "+rcBalance);
            double zcBalance= driver.getBalance(zid,0);
            System.out.println("Zc balance is "+zcBalance);
        }else {
            System.out.println("Invalid Zid");
        }
    }

    private static void printTransaction(List<String> rcTransaction) {
        for (String str: rcTransaction){
            System.out.println(str);
        }
    }

    private static Admin getAdmin(String name, String mailId, String password, int hid, long mobile) {
        Admin admin=new Admin();
        admin.setName(name);
        admin.setMailId(mailId);
        admin.setPassword(password);
        admin.setH_Id(hid);
        admin.setMobileNumber(mobile);
        return admin;
    }

    private static User getUser(String name, String mailId, String password, int hid, long mobile,int amount) {
        User user=new User();
        user.setH_Id(hid);
        user.setName(name);
        user.setMobileNumber(mobile);
        user.setMailId(mailId);
        user.setPassword(password);
        user.setStatus("Pending");
        user.setRcAmount(amount);
        return user;
    }
    private static String checkPassword(String password,Scanner scan,String name,String mailId){
        String[]userName=mailId.split("@");
        while (password.equals(name)||password.equals(userName[0])) {
            System.out.println("Pass word not contain name or user name");
            System.out.println("Enter the password :");
            password=scan.nextLine();
        }
        while (!Pattern.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!#%<>&\\*]).{8,}",password)){
            System.out.println("Password should be in alphanumeric with mixed case and special character(!#%<>*&)and length not less than 8");
            System.out.println("Enter the password :");
            password=scan.nextLine();
        }
        return password;
    }
}
