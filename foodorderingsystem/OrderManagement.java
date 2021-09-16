package foodorderingsystem;

import java.util.HashMap;
import java.util.Map;

public class OrderManagement {
    private Map<String,Hotel>hotels=new HashMap<>();
    private Map<String,Customer>customerDetails=new HashMap<>();
    private Map<String,Map<String,Hotel>>foods=new HashMap<>();

    public void setHotels(Hotel hotel){
        String hotelName=hotel.getHotelName();
        hotels.put(hotelName,hotel);
    }
    public void setFoods(String foodName,Hotel hotel){
        String hotelName=hotel.getHotelName();
        Map<String,Hotel>hotels=foods.get(foodName);
        if(hotels==null){
            hotels=new HashMap<>();
            foods.put(foodName,hotels);
        }
        hotels.put(hotelName,hotel);

    }
    public Map<String, Hotel> getHotels() {
        return hotels;
    }

    public Map<String, Customer> getCustomerDetails() {
        return customerDetails;
    }

    public Map<String, Map<String, Hotel>> getFoods() {
        return foods;
    }
}
