package foodorderingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        ProgramDriver driver=new ProgramDriver();
        Scanner scan=new Scanner(System.in);
        while (true){
            System.out.println("1.Login\n2.signUp\n3.Exit");
            System.out.println("Enter he choice :");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                System.out.println("Email id:");
                String emailId=scan.nextLine();
                System.out.println("Password :");
                String password=scan.nextLine();
                int result=driver.signIn(emailId,password);
                if(result==1){
                    System.out.println("1.search by hotels\n2.search by foods\n3.exit");
                    int decision1=scan.nextInt();
                    if(decision1==1){
                        List<String> hotels=driver.searchHotels();
                        orderFood(driver, scan, emailId, hotels);
                    }else if(decision1==2){
                        List<String> foods=driver.searchFoods();
                        System.out.println(foods);
                        System.out.println("Enter food Number");
                        int index =scan.nextInt()-1;
                        index = getIndex(scan, foods, index);
                        String foodName=getName(foods.get(index));
                        List<String>hotels=driver.searchHotelInFood(foodName);
                       orderFood(driver,scan,emailId,hotels);
                    }else if(decision1==3) {
                        break;
                    }else {
                        System.out.println("Invalid Input");
                    }
                }else
                    System.out.println("Email id or password is wrong");
            }else if(decision==2){
                System.out.println("Name :");
                String name=scan.nextLine();
                System.out.println("Email id :");
                String mailId= scan.nextLine();
                System.out.println("Password :");
                String password=scan.nextLine();
                System.out.println("Age :");
                int age=scan.nextInt();
                System.out.println("Mobile Number :");
                long mobile=scan.nextLong();
                scan.nextLine();
                Customer customer=new Customer();
                customer.setAge(age);
                customer.setEmailId(mailId);
                customer.setPassword(password);
                customer.setName(name);
                customer.setMobile(mobile);
                driver.addAccount(customer);

            }
        }

    }


    private static void orderFood(ProgramDriver driver, Scanner scan, String emailId, List<String> hotels) {
        System.out.println(hotels);
        System.out.println("Enter hotel Number");
        int index = scan.nextInt()-1;
        index = getIndex(scan, hotels, index);
        String hotelName=getName(hotels.get(index));
        List<String>foods= driver.searchFoodInHotel(hotelName);
        System.out.println(foods);
        System.out.println("Are you order food(yes=1/no=0)");
        int flag= scan.nextInt();
        List<String>foodNames=new ArrayList<>();
        while (flag==1){
            System.out.println("Enter food Number");
            index= scan.nextInt()-1;
            index=getIndex(scan,foods,index);
            String foodName=getName(foods.get(index));
            foodNames.add(foodName);
            System.out.println("1.continue order food\n2.exit");
            flag= scan.nextInt();
        }
        if(foodNames.isEmpty()){
            int amount= driver.orderFood(foodNames,hotelName);
            System.out.println("Total amount is "+amount);
            System.out.println("Are you confirm Order(yes=1/no=0)");
            int status= scan.nextInt();
            if(status==1){
                String output= driver.confirmOrder(hotelName, emailId,amount,foods);
                System.out.println(output);
            }
        }
    }

    private static int getIndex(Scanner scan, List<String> list, int index) {
        while (index >= list.size()|| index <0){
            System.out.println("Re enter the number");
            index = scan.nextInt()-1;
        }
        return index;
    }

    private static String getName(String name){
        String[]array1=name.split("\\.");
        String[] array2=array1[1].split("-");
        return array2[0];
    }
}
