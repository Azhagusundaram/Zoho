package hotelmanagementsystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HotelManagement {
    private Map<String,Hotel>hotels=new HashMap<>();
    private Map<Integer,User>users=new HashMap<>();
    private Set<String>locations=new HashSet<>();

    public void setLocations(String location){
        locations.add(location);
    }
    public Map<String, Hotel> getHotels() {
        return hotels;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Set<String> getLocations() {
        return locations;
    }
    public void setHotels(Hotel hotel){
        String hotelName=hotel.getHotelName();
        hotels.put(hotelName,hotel);
    }
    public void setUsers(User user){
        int userId=user.getUserId();
        users.put(userId,user);
    }
}
