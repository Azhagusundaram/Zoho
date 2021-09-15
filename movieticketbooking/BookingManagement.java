package movieticketbooking;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookingManagement {
    private Map<String,Movie>availableTickets=new LinkedHashMap<>();
    private Map<Integer,TicketBooking>bookedTickets=new HashMap<>();

    public void setAvailableTickets(List<Movie> movies){
        for(Movie movie:movies){
            String movieName=movie.getMovieName();
            availableTickets.put(movieName,movie);
        }
    }
    public Map<String, Movie> getAvailableTickets(){
        return availableTickets;
    }
    public void setBookedTickets(TicketBooking ticketBooking){
        int bookingId=ticketBooking.getBookingId();
        bookedTickets.put(bookingId,ticketBooking);
    }
}
