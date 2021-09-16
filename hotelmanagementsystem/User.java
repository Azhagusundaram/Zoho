package hotelmanagementsystem;

public class User {
    private String userName;
    private int userId;
    private String hotelName;
    private int bookingCost;
    private int numberOfRoomsBooked;

    public int getNumberOfRoomsBooked() {
        return numberOfRoomsBooked;
    }

    public void setNumberOfRoomsBooked(int numberOfRoomsBooked) {
        this.numberOfRoomsBooked = numberOfRoomsBooked;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getBookingCost() {
        return bookingCost;
    }

    public void setBookingCost(int bookingCost) {
        this.bookingCost = bookingCost;
    }
    @Override
    public String toString(){
        return userName+" "+userId+" "+hotelName+" "+numberOfRoomsBooked+" "+bookingCost;
    }
}
