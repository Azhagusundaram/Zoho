package railwayticketbooking;

import java.util.*;

public class TicketBookingManagement {
    private Map<String, List<Integer>> availableSeats=new HashMap<>();
    private Map<Integer,Ticket>bookedSeats=new HashMap<>();
    private Deque<Integer>rac=new LinkedList<>();
    private Deque<Integer>waitingList=new LinkedList<>();

    public void setAvailableSeats(String berth,int seatNumber){
        List<Integer>seats=availableSeats.get(berth);
        if(seats==null){
            seats=new ArrayList<>();
            availableSeats.put(berth,seats);
        }
        seats.add(seatNumber);
    }
    public Map<String, List<Integer>> getAvailableSeats(){
        return availableSeats;
    }
    public int getWaitingList(){
        if(!waitingList.isEmpty())
            return waitingList.poll();
        return 0;
    }
    public int getRac(){
        if(!rac.isEmpty())
            return rac.poll();
        return 0;
    }
    public void deleteRac(int bookingId){
        rac.remove( bookingId);
    }
    public void setBookedSeats(Ticket ticket){
        int ticketNumber=ticket.getBookingId();
        bookedSeats.put(ticketNumber,ticket);
    }
    public Map<Integer, Ticket> getBookedSeats(){
        return bookedSeats;
    }
    public Ticket getBookedSeats(int bookingId){
        return bookedSeats.get(bookingId);
    }
    public void addRac(int bookingId){
        rac.add(bookingId);
    }
    public int addWaitingList(int bookingId){
        if(waitingList.size()<10){
            waitingList.add(bookingId);
            return 100;
        }
        return 0;
    }
}
