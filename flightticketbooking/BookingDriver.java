package flightticketbooking;

import java.util.*;

public class BookingDriver {

    int ticketNumber=0;

    TicketBookingManagement cache=new TicketBookingManagement();
    public int bookTicket(Passenger passenger,int flightNumber){

        Map<Integer, Map<String, List<Integer>>> availableTickets=cache.getAvailableTickets();
        String ticketType= passenger.getTicketType();
        Map<String,List<Integer>>flightMap=availableTickets.get(flightNumber);
        if(flightMap==null){
            return 0;
        }
        List<Integer>tickets=flightMap.get(ticketType);
        if(tickets==null){
            return 0;
        }
        if(tickets.isEmpty()){
            System.out.println("empty");
            return 0;
        }
        int seatNumber=tickets.get(0);
        passenger.setSeatNumber(seatNumber);
        tickets.remove(0);
        return 1;
    }
    public String bookTicket(List<Passenger>passengers, int flightNumber){
        Map<String,List<Passenger>>bookingStatus=new HashMap<>();
        List<Passenger>successBooking=new ArrayList<>();
        List<Passenger>failureBooking=new ArrayList<>();
        bookingStatus.put("Success",successBooking);
        bookingStatus.put("Failure",failureBooking);
        Ticket ticket=new Ticket();
        boolean flag=false;
        for(Passenger passenger:passengers){
            int result=bookTicket(passenger,flightNumber);
            if(result==1){
                ticket.setFlightNumber(flightNumber);
                ticket.setPassengers(passenger);
                double amount=getAmount(passenger,flightNumber);
                passenger.setAmount(amount);
                ticket.setTotalAmount(amount);
                successBooking.add(passenger);
                flag=true;
                continue;
            }
            failureBooking.add(passenger);
        }
        if(flag==true){
            ticketNumber++;
            ticket.setTicketNumber(ticketNumber);
            cache.setBookedTickets(ticket);
        }
        return "Ticket Number : "+ticketNumber+"\n"+bookingStatus.toString()+"\nTotal Amount :"+ticket.getTotalAmount();

    }
    public double getAmount(Passenger passenger,int flightNumber){
        double totalAmount=0;
        String meal= passenger.getMeal();
        String ticketType= passenger.getTicketType();
        Flight flight=cache.getFlights().get(flightNumber);
        if(ticketType.startsWith("e")||ticketType.startsWith("E")){
            totalAmount=flight.getEconomyTicketPrice();
        }else if(ticketType.startsWith("b")||ticketType.startsWith("B")){
            totalAmount=flight.getBusinessTicketPrice();
        }
        if(meal.startsWith("Y")||meal.startsWith("y")){
            totalAmount+=flight.getMealPrice();
        }
        flight.setTotalAmount(totalAmount);
        return totalAmount;
    }
    public void initialSetUp(){
        int numOfFlights=2;
        int economyTickets=20;
        int businessTickets=10;

        for(int i=1;i<=numOfFlights;i++){
            int flightNumber=100+i;
            List<Integer>businessTicketSeats=new ArrayList<>(businessTickets);
            List<Integer>economyTicketSeats=new ArrayList<>(economyTickets);
            for(int j=1;j<=economyTickets;j++){
                economyTicketSeats.add(j);
            }
            for (int k=1;k<=businessTickets;k++){
                businessTicketSeats.add(economyTickets+k);
            }
            Flight flight = getFlight(flightNumber, businessTicketSeats, economyTicketSeats);
            cache.setFlights(flight);
            cache.setAvailableTickets(flight);
        }
    }

    private Flight getFlight(int flightNumber, List<Integer> businessTicketSeats, List<Integer> economyTicketSeats) {
        double surgePrice=0.2;
        double economyTicketPrice=100;
        double businessTicketPrice=200;
        int mealPrice=50;
        Flight flight=new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setFlightNumber(flightNumber);
        flight.setBusinessTicketPrice(businessTicketPrice);
        flight.setEconomyTicketPrice(economyTicketPrice);
        flight.setBusinessTickets(businessTicketSeats);
        flight.setEconomyTickets(economyTicketSeats);
        flight.setSurgePrice(surgePrice);
        flight.setMealPrice(mealPrice);
        return flight;
    }

    public Map<Integer, Map<String, Integer>> getAvailableTickets(){
        Map<Integer,Map<String,Integer>>availableSeats=new HashMap<>();
        Map<Integer, Map<String, List<Integer>>>availableTickets=cache.getAvailableTickets();
        Set<Integer>flights=availableTickets.keySet();
        for(int flightNumber:flights){
            Map<String,Integer>flightMap=new HashMap<>();
            availableSeats.put(flightNumber,flightMap);
            Map<String,List<Integer>>flightTickets=availableTickets.get(flightNumber);
            Set<String>ticketTypes=flightTickets.keySet();
            for(String ticketType:ticketTypes){
                int number=flightTickets.get(ticketType).size();
                flightMap.put(ticketType,number);
            }
        }

        return availableSeats;
    }
    public String cancelTicket(int flightNumber,int ticketNumber,int seatNumber){
        double refundAmount=0;
        Map<Integer,Map<Integer,Ticket>>bookedTickets= cache.getBookedTickets();
        Map<Integer, Ticket>tickets=bookedTickets.get(flightNumber);
        if(tickets==null){
            return "Invalid Flight Number";
        }
        Ticket ticket=tickets.get(ticketNumber);
        if(ticket==null){
            return "Invalid Ticket Number";
        }
        Map<Integer,Passenger>passengers=ticket.getPassengers();
        if(seatNumber==0){
            Set<Integer> seatNumbers= passengers.keySet();
            for(int seat:seatNumbers){
                Passenger passenger=passengers.get(seat);
                refundAmount+=cancelTicket(flightNumber,passenger);
                passengers.remove(seat);
            }
            return "Refund amount is "+refundAmount;
        }
        Passenger passenger=passengers.get(seatNumber);
        refundAmount=cancelTicket(flightNumber,passenger);
        passengers.remove(seatNumber);
        return "Refund amount is "+refundAmount;
    }
    private double cancelTicket(int flightNumber,Passenger passenger){
        double amount=passenger.getAmount();
        int seatNumber=passenger.getSeatNumber();
        cache.setAvailableTickets(flightNumber,seatNumber);
        double refundAmount=refund(amount);
        Map<Integer,Flight>flights=cache.getFlights();
        Flight flight=flights.get(flightNumber);
        flight.setTotalAmount(-refundAmount);
        return refundAmount;
    }
    public double refund(double amount){
        double refundRate=0.5;
        amount=amount*refundRate;
        return amount;
    }
    public String getFlightSummary(int flightNumber){
        Map<Integer, Ticket> bookedTickets=cache.getBookedTickets().get(flightNumber);
        Flight flight=cache.getFlights().get(flightNumber);
        if(bookedTickets==null&&flight==null){
            return "Invalid Flight Number";
        }else if(flight!=null&&bookedTickets==null){
            return "No Tickets Booked";
        }

        return bookedTickets+"\nTotal Amount : "+flight.getTotalAmount();
    }

}
