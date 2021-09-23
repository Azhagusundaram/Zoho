package movieticketbooking;

import java.util.*;

public class BookingDriver {
    BookingManagement cache=new BookingManagement();

    int bookingId=1;

    public TicketBooking bookTickets(TicketBooking ticket){
        Map<String, Movie> movies=cache.getAvailableTickets();
        String movieName=ticket.getMovieName();
        Movie movie =movies.get(movieName);
        if(movie ==null){
            return null;
        }
        String time=ticket.getTime();
        Time timing= movie.getTimings().get(time);
        if(timing==null){
            return null;
        }
        Map<String,List<String>>ticketSeats=ticket.getSeats();
        Map<String, List<String>> availableSeatTypes=timing.getSeats();
        Set<String> seatTypes=ticketSeats.keySet();
        for(String seatType:seatTypes){
            List<String>availableSeats=availableSeatTypes.get(seatType);
            List<String>seats=ticketSeats.get(seatType);
            List<String> tempSeats =new ArrayList<>();
            tempSeats.addAll(seats);
            if(availableSeats!=null){
                for(String seatNumber: tempSeats){
                    if(availableSeats.contains(seatNumber)){
                       availableSeats.remove(seatNumber);
                       int amount=getAmount(movieName,seatType);
                       ticket.setTotalAmount(amount);
                    }else {
                        seats.remove(seatNumber);
                    }
                }
            }
        }
        ticket.setBookingId(bookingId);
        cache.setBookedTickets(ticket);
        bookingId++;
        return ticket;
    }
    public int getAmount(String movieName,String ticketType){
        Movie movie=cache.getAvailableTickets().get(movieName);
        if(ticketType.startsWith("D")){
            return movie.getDeluxePrice();
        }
        else if(ticketType.startsWith("S"))
            return movie.getSuperDeluxePrice();
        return 0;

    }
    public Map<String, Movie> getAvailableTickets(){
        return cache.getAvailableTickets();
    }
    public void initialSetUp(List<Movie>movies) {
        cache.setAvailableTickets(movies);
    }
    public List<String> getMovieList(){
        Map<String,Movie>movieNameLists=cache.getAvailableTickets();
        Set<String>movieNames= movieNameLists.keySet();
        List<String>movieNameList=new ArrayList<>();
        int i=1;
        for(String movieName:movieNames){
            movieNameList.add(i+"."+movieName);
            i++;
        }
        return movieNameList;
    }
    public List<String> getTimings(String movieName){
        Movie movie=cache.getAvailableTickets().get(movieName);
        Set<String>timings=movie.getTimings().keySet();
        List<String>timingList=new ArrayList<>();
        int i=1;
        for(String timing:timings){
            timingList.add(i+"."+timing);
            i++;
        }
        return timingList;
    }
    public List<String> getTicketList(String movieName, String timing, String ticketType){
        Movie movie=cache.getAvailableTickets().get(movieName);
        Time time=movie.getTimings().get(timing);
        List<String>tickets=time.getSeats().get(ticketType);
        return tickets;
    }
}
