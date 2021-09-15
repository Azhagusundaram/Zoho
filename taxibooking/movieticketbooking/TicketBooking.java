package movieticketbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketBooking {
    private int bookingId;
    private int totalAmount;

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount+= totalAmount;
    }

    private String movieName;
    private String time;

    private Map<String,List<String>> seats=new HashMap<>();
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Map<String, List<String>> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seat,String seatType) {
        List<String>seatNumbers=seats.get(seatType);
        if(seatNumbers==null){
            seatNumbers=new ArrayList<>();
            seats.put(seatType,seatNumbers);
        }
        seatNumbers.addAll(seat);
    }
    @Override
    public String toString(){
        return "Booking ID: " +bookingId+" Movie Name : "+movieName+" Time : "+time+" Seats : "+seats.toString()+"Total Amount : "+totalAmount;
    }
}
