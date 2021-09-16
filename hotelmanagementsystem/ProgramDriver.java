package hotelmanagementsystem;

import java.util.*;

public class ProgramDriver {
    private int userId=0;
    private HotelManagement cache=new HotelManagement();
    public void initialSetUp(List<Hotel>hotels){
        for(Hotel hotel:hotels){
            String location=hotel.getLocation();
            cache.setLocations(location);
            cache.setHotels(hotel);
        }
    }
    public List<Hotel> getHotelsSortByName(){
        Collection<Hotel> hotels=cache.getHotels().values();
        List<Hotel> hotelList = new ArrayList<>(hotels);
        hotelList.sort(new Comparator<Hotel>() {
            @Override
            public int compare(Hotel o1, Hotel o2) {
                return o1.getHotelName().compareTo(o2.getHotelName());
            }
        });
        return hotelList;
    }
    public List<Hotel> getHotelsSortByRating(){
        Collection<Hotel> hotels=cache.getHotels().values();
        List<Hotel> hotelList = new ArrayList<>(hotels);
        hotelList.sort(new Comparator<Hotel>() {
            @Override
            public int compare(Hotel o1, Hotel o2) {
                return (int) (o2.getRating()-o1.getRating());
            }
        });
        return hotelList;
    }
    public List<Hotel> getHotelsSortByRoomsAvailable(){
        Collection<Hotel> hotels=cache.getHotels().values();
        List<Hotel> hotelList = new ArrayList<>(hotels);
        hotelList.sort(new Comparator<Hotel>() {
            @Override
            public int compare(Hotel o1, Hotel o2) {
                return o2.getRoomsAvailable()-o1.getRoomsAvailable();
            }
        });
        return hotelList;
    }
    public List<Hotel> getHotels(String location){
        Collection<Hotel> hotels=cache.getHotels().values();
        List<Hotel> allHotels = new ArrayList<>(hotels);
        List<Hotel>hotelsList=new ArrayList<>();
        for(Hotel hotel:allHotels){
            String hotelLocation=hotel.getLocation();
            if(hotelLocation.equals(location)){
                hotelsList.add(hotel);
            }
        }
        return hotelsList;
    }
    public List<Hotel> getAllHotels(){
        Collection<Hotel> hotels=cache.getHotels().values();
        List<Hotel> allHotels = new ArrayList<>(hotels);
        return allHotels;
    }
    public List<String>getLocations(){
        Set<String>allLocations=cache.getLocations();
        List<String>locations=new ArrayList<>();
        int i=1;
        for(String location:allLocations){
            locations.add(i+"."+location);
            i++;
        }
        return locations;
    }
    public List<String> getHotelNames(){
        Set<String> hotelNames=cache.getHotels().keySet();
        List<String>hotels=new ArrayList<>();
        int i=1;
        for(String hotel:hotelNames){
            hotels.add(i+"."+hotel);
            i++;
        }
        return hotels;
    }
    public List<User> getUsers(){
        Collection<User> hotels=cache.getUsers().values();
        List<User> allHotels = new ArrayList<>(hotels);
        return allHotels;
    }
    public String bookHotel(User user){
        String hotelName=user.getHotelName();
        int numOfRoom=user.getNumberOfRoomsBooked();
        Hotel hotel=cache.getHotels().get(hotelName);
        int pricePerRoom=hotel.getPricePerRoom();
        int roomsAvailable=hotel.getRoomsAvailable();
        if(roomsAvailable>=numOfRoom){
            userId++;
            hotel.setRoomsAvailable(-numOfRoom);
            int totalAmount=numOfRoom*pricePerRoom;
            hotel.setTotalAmount(totalAmount);
            user.setBookingCost(totalAmount);
            user.setUserId(userId);
            cache.setUsers(user);
            return "Rooms Booked\nYour User Id is "+userId+"\nTotal Amount is "+totalAmount;
        }
        return numOfRoom+" Rooms not available";
    }

}
