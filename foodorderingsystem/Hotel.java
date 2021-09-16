package foodorderingsystem;

import java.util.HashMap;
import java.util.Map;

public class Hotel {
    private String hotelName;
    private int totalAmount;
    Map<String,Food>foods=new HashMap<>();

    public Map<String, Food> getFoods() {
        return foods;
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
