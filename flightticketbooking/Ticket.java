package flightticketbooking;

import java.util.HashMap;
import java.util.Map;

public class Ticket {
    private int flightNumber;
    public int ticketNumber;
    public double totalAmount;
    Map<Integer,Passenger> passengers=new HashMap<>();

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount+= totalAmount;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }


    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }
    public void setPassengers(Passenger passenger){
        int seatNumber=passenger.getSeatNumber();
        passengers.put(seatNumber,passenger);
    }
    public Map<Integer, Passenger> getPassengers( ){
        return passengers;
    }
    @Override
    public String toString(){
        return ticketNumber+" "+passengers.toString();
    }

}
