package foodorderingsystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
    private String hotelName;
    private int totalAmount;
    private Map<String,Food>foods=new HashMap<>();

    public Map<String, Food> getFoods() {
        return foods;
    }
    public void setFoods(List<Food> foods){
        for(Food food:foods){
            String foodName=food.getFoodName();
            this.foods.put(foodName,food);
        }
    }
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount+= totalAmount;
    }
}
