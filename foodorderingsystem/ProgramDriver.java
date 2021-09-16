package foodorderingsystem;

import java.util.*;

public class ProgramDriver {
    OrderManagement cache=new OrderManagement();
    public String addAccount(Customer customer){
        Map<String,Customer> customerDetails=cache.getCustomerDetails();
        String emailId=customer.getEmailId();
        if(customerDetails.containsKey(emailId)){
            return "Account Already exit";

        }
        customerDetails.put(emailId,customer);
        return "Account Added Successfully";
    }
    public int signIn(String emailId,String password){
        Map<String,Customer> customerDetails=cache.getCustomerDetails();
        Customer customer=customerDetails.get(emailId);
        if(customer==null){
            return 0;
        }
        String oldPassword=customer.getPassword();
        if(oldPassword.equals(password)){
            return 1;
        }
        return 0;
    }
    public List<String> searchHotels(){
        Set<String> hotels=cache.getHotels().keySet();
        List<String>hotelsList=new ArrayList<>();
        int i=1;
        for(String hotel:hotels){
            hotelsList.add(i+"."+hotel);
            i++;
        }
        return hotelsList;
    }
    public List<String> searchFoods(){
        Set<String> foods=cache.getFoods().keySet();
        List<String>foodsList=new ArrayList<>();
        int i=1;
        for(String food : foods){
            foodsList.add(i+"."+ food);
            i++;
        }
        return foodsList;
    }
    public List<String> searchFoodInHotel(String hotelName){
        Hotel hotel=cache.getHotels().get(hotelName);
        Map<String,Food>foodMap=hotel.getFoods();
        Collection<Food> foods =foodMap.values();
        List<String>foodsList=new ArrayList<>();
        int i=1;
        for(Food food : foods){
            foodsList.add(i+"."+ food.getFoodName()+"-"+food.getPrice());
            i++;
        }
        return foodsList;
    }
    public List<String> searchHotelInFood(String foodName){
        Map<String,Hotel>hotelMap=cache.getFoods().get(foodName);
       Set<String>hotels= hotelMap.keySet();
        List<String>hotelsList=new ArrayList<>();
        int i=1;
        for(String hotel:hotels){
            hotelsList.add(i+"."+hotel);
            i++;
        }
        return hotelsList;
    }
    public int orderFood(List<String>foodNames,String hotelName){
        int totalAmount=0;
        Hotel hotel=cache.getHotels().get(hotelName);
         Map<String,Food>foodMap= hotel.getFoods();
         for (String foodName:foodNames){
             Food food=foodMap.get(foodName);
             int price=food.getPrice();
             totalAmount+=price;
         }
         return totalAmount;
    }
    public String confirmOrder(String hotelName, String customerEmailId, int totalAmount, List<String>foods){
        Hotel hotel=cache.getHotels().get(hotelName);
        hotel.setTotalAmount(totalAmount);
        Customer customer=cache.getCustomerDetails().get(customerEmailId);
        customer.setCart(foods,totalAmount);
        return "Order Confirmed";
    }

}
