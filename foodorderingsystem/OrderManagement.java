package foodorderingsystem;

import java.util.HashMap;
import java.util.Map;

public class OrderManagement {
    private Map<String,Hotel>hotels=new HashMap<>();
    private Map<String,Customer>customerDetails=new HashMap<>();
    private Map<String,Map<String,Hotel>>foods=new HashMap<>();

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
