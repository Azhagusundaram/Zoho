package foodorderingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputLayer {
    public static void main(String[] args) {
        OrderDriver driver = new OrderDriver();
        Scanner scan = new Scanner(System.in);

        System.out.println("Number of hotels");
        int numOfHotels=scan.nextInt();
        scan.nextLine();
        List<Hotel> allHotels =new ArrayList<>();
        for (int i=0;i<numOfHotels;i++){
            System.out.println("Enter hotel Name :");
            String hotelName=scan.nextLine();
            System.out.println("Enter the food Details");
            boolean flag=true;
            List<Food>foods=new ArrayList<>();
            while (flag){
                System.out.println("Enter the food Name");
                String foodName=scan.nextLine();
                System.out.println("Enter the food Price");
                int price=scan.nextInt();

                Food food = getFood(foodName, price);
                System.out.println("1.Continue to add Food\n2.exit");
                int decision=scan.nextInt();
                scan.nextLine();
                if(decision==2){
                    flag=false;
                }
                foods.add(food);
            }
            Hotel hotel = getHotel(hotelName, foods);
            allHotels.add(hotel);
        }
        driver.initialSetUp(allHotels);

        while (true) {
            System.out.println("1.Login\n2.signUp\n3.Exit");
            System.out.println("Enter he choice :");
            int decision = scan.nextInt();
            scan.nextLine();
            if (decision == 1) {
                System.out.println("Email id:");
                String emailId = scan.nextLine();
                System.out.println("Password :");
                String password = scan.nextLine();
                int result = driver.signIn(emailId, password);
                if (result == 1) {
                    while (true){
                        System.out.println("1.search by hotels\n2.search by foods\n3.exit");
                        int decision1 = scan.nextInt();
                        if (decision1 == 1) {
                            List<String> hotels = driver.searchHotels();
                            orderFood(driver, scan, emailId, hotels);
                        } else if (decision1 == 2) {
                            List<String> foods = driver.searchFoods();
                            System.out.println(foods);
                            System.out.println("Enter food Number");
                            int index = scan.nextInt() - 1;
                            index = getIndex(scan, foods, index);
                            String foodName = getName(foods.get(index));
                            List<String> hotels = driver.searchHotelInFood(foodName);
                            orderFood(driver, scan, emailId, hotels);
                        } else if (decision1 == 3) {
                            break;
                        } else {
                            System.out.println("Invalid Input");
                        }
                    }

                } else
                    System.out.println("Email id or password is wrong");
            } else if (decision == 2) {
                System.out.println("Name :");
                String name = scan.nextLine();
                System.out.println("Email id :");
                String mailId = scan.nextLine();
                System.out.println("Password :");
                String password = scan.nextLine();
                password=checkPassword(password,scan);
                System.out.println("Age :");
                int age = scan.nextInt();
                System.out.println("Mobile Number :");
                long mobile = scan.nextLong();
                scan.nextLine();
                Customer customer = getCustomer(name, mailId, password, age, mobile);
                String result=driver.addAccount(customer);
                System.out.println(result);
            }else if(decision==3){
                break;
            }else {
                System.out.println("Invalid Input");
            }
        }

    }

    private static Hotel getHotel(String hotelName, List<Food> foods) {
        Hotel hotel=new Hotel() ;
        hotel.setHotelName(hotelName);
        hotel.setFoods(foods);
        return hotel;
    }

    private static Food getFood(String foodName, int price) {
        Food food=new Food();
        food.setFoodName(foodName);
        food.setPrice(price);
        return food;
    }

    private static Customer getCustomer(String name, String mailId, String password, int age, long mobile) {
        Customer customer = new Customer();
        customer.setAge(age);
        customer.setEmailId(mailId);
        customer.setPassword(password);
        customer.setName(name);
        customer.setMobile(mobile);
        return customer;
    }

    private static void orderFood(OrderDriver driver, Scanner scan, String emailId, List<String> hotels) {
        System.out.println(hotels);
        System.out.println("Enter hotel Number");
        int index = scan.nextInt() - 1;
        index = getIndex(scan, hotels, index);
        String hotelName = getName(hotels.get(index));
        List<String> foods = driver.searchFoodInHotel(hotelName);
        System.out.println(foods);
        System.out.println("Are you order food(yes=1/no=0)");
        int flag = scan.nextInt();
        List<String> foodNames = new ArrayList<>();
        while (flag == 1) {
            System.out.println("Enter food Number");
            index = scan.nextInt() - 1;
            index = getIndex(scan, foods, index);
            String foodName = getName(foods.get(index));
            foodNames.add(foodName);
            System.out.println("1.continue order food\n2.exit");
            flag = scan.nextInt();
        }
        if (!foodNames.isEmpty()) {
            int amount = driver.orderFood(foodNames, hotelName);
            System.out.println("Total amount is " + amount);
            System.out.println("Are you confirm Order(yes=1/no=0)");
            int status = scan.nextInt();
            if (status == 1) {
                String output = driver.confirmOrder(hotelName, emailId, amount, foods);
                System.out.println(output);
            }
        }
    }

    private static int getIndex(Scanner scan, List<String> list, int index) {
        while (index >= list.size() || index < 0) {
            System.out.println("Re enter the number");
            index = scan.nextInt() - 1;
        }
        return index;
    }

    private static String getName(String name) {
        String[] array1 = name.split("\\.");
        String[] array2 = array1[1].split("-");
        return array2[0];
    }
    private static String checkPassword(String password,Scanner scan){
        while (!Pattern.matches("(?=.*[A-z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%&]).{8,15}",password)){
            System.out.println("Enter correct Password");
            password=scan.nextLine();
        }
        return password;
    }
}