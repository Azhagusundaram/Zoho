package hotelmanagementsystem;

public class Hotel {
    private String hotelName;
    private int roomsAvailable;
    private String location;
    private double rating;
    private int pricePerRoom;
    private int totalAmount;

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount+= totalAmount;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable+= roomsAvailable;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPricePerRoom() {
        return pricePerRoom;
    }

    public void setPricePerRoom(int pricePerRoom) {
        this.pricePerRoom = pricePerRoom;
    }
    @Override
    public String toString(){
        return hotelName+" "+roomsAvailable+" "+location+" "+rating+" "+pricePerRoom;

    }
}
//5
//H1
//Chennai
//5
//3
//100
//H2
//Bangalore
//10
//4
//200
//H3
//Chennai
//10
//2
//50
//H4
//Bangalore
//2
//5
//500
//H5
//Bangalore
//4
//4
//300
