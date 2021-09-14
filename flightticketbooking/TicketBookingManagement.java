package flightticketbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketBookingManagement {
    private Map<Integer, Map<String, List<Integer>>>availableTickets=new HashMap<>();
    private Map<Integer,Map<Integer,Ticket>>bookedTickets=new HashMap<>();
    private Map<Integer,Flight>flights=new HashMap<>();

    public Map<Integer, Map<String, List<Integer>>> getAvailableTickets() {
        return availableTickets;
    }

    public Map<Integer, Map<Integer,Ticket>> getBookedTickets() {
        return bookedTickets;
    }

    public Map<Integer, Flight> getFlights() {
        return flights;
    }
    public void setBookedTickets(Ticket ticket){
       int flightNumber=ticket.getFlightNumber();
       int ticketNumber=ticket.getTicketNumber();
       Map<Integer, Ticket> tickets=bookedTickets.get(flightNumber);
       if(tickets==null){
           tickets=new HashMap<>();
           bookedTickets.put(flightNumber,tickets);
       }
       tickets.put(ticketNumber,ticket);
    }
    public void setFlights(Flight flight){
        int flightNumber=flight.getFlightNumber();
        flights.put(flightNumber,flight);
    }
    public void setAvailableTickets(Flight flight){
        int flightNumber=flight.getFlightNumber();
        List<Integer>businessTickets=new ArrayList<>();
        businessTickets.addAll(flight.getBusinessTickets());
        List<Integer>economyTickets=new ArrayList<>();
        economyTickets.addAll(flight.getEconomyTickets());
        Map<String,List<Integer>>TicketMap=new HashMap<>();
        availableTickets.put(flightNumber,TicketMap);
       TicketMap.put("Business",businessTickets);
        TicketMap.put("Economy",economyTickets);
    }
    public void setAvailableTickets(int flightNumber,int seatNumber){
        Map<String,List<Integer>>ticketsMap=availableTickets.get(flightNumber);
        if(ticketsMap==null){
            ticketsMap=new HashMap<>();
            availableTickets.put(flightNumber,ticketsMap);

        }
        Flight flight= flights.get(flightNumber);
        if(flight.getBusinessTickets().contains(seatNumber)){
            List<Integer>tickets=ticketsMap.get("Business");
            if(tickets==null){
                tickets=new ArrayList<>();
                ticketsMap.put("Business",tickets);
            }
            tickets.add(seatNumber);
        }else if(flight.getEconomyTickets().contains(seatNumber)){
            List<Integer> tickets = ticketsMap.computeIfAbsent("Economy", k -> new ArrayList<>());
            tickets.add(seatNumber);
        }
    }


}
