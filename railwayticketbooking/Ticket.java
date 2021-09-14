package railwayticketbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ticket {
    private int bookingId;
    private List<Passenger> passengers=new ArrayList<>();

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public List< Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger passenger) {
        passengers.add(passenger);
    }
    @Override
    public String toString(){
        return "\nBooking Id : "+ bookingId +"\n"+passengers.toString();
    }
}
