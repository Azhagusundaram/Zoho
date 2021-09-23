package railwayticketbooking;

import java.util.*;

public class BookingDriver {
    TicketBookingManagement cache=new TicketBookingManagement();
    public void initialSetUp(){
        Map<String,List<Integer>>availableSeats= cache.getAvailableSeats();
        int seatNumber=1;
        for(int j=1;j<=8;j++) {
            List<Integer> seats = new ArrayList<>();
            for (int k = 0; k <=8; k++) {
                seatNumber = k* 8 + j;
                seats.add(seatNumber);
            }
            if(j==7){
                seats.addAll(seats);
            }
            String berth=berthCalculate(seats.get(0));
            List<Integer>allSeats=availableSeats.get(berth);
            if(allSeats==null){
                allSeats=new ArrayList<>();
                availableSeats.put(berth,allSeats);
            }
            allSeats.addAll(seats);
        }
    }
    public String berthCalculate(int number){
        String berth="";
        while (number>8){
            number=number%8;
        }
        if(number==1||number==4){
            berth="Lower";
        }else if(number==2||number==5){
            berth="Middle";
        }else if(number==3||number==6){
            berth="Upper";
        }else if(number==8){

            berth="Side Upper";
        }else if(number==7){
            berth="Rac";
        }
        return berth;
    }
    public Ticket bookTickets(Ticket ticket){
        int bookingId =ticket.getBookingId();
        List<Passenger>passengers=ticket.getPassengers();
        for(Passenger passenger:passengers){
            int age=passenger.getAge();
            if(age>5){
                int seatNumber= checkAvailabilityAndBook(passenger);
                if(seatNumber!=0){
                    passenger.setSeatNumber(seatNumber);
                    passenger.setStatus("Booked");
                    continue;
                }
                seatNumber=checkBerthPreference("Rac",passenger);
                if(seatNumber!=0){
                    passenger.setSeatNumber(seatNumber);
                    cache.addRac(bookingId);
                    passenger.setStatus("Rac");
                    continue;
                }
                seatNumber=cache.addWaitingList(bookingId);
                if(seatNumber!=0){
                    passenger.setSeatNumber(seatNumber);
                    passenger.setStatus("Waiting List");
                }
            }else {
                passenger.setStatus("Children");
            }

        }
        cache.setBookedSeats(ticket);
        return ticket;
    }
    public int checkAvailabilityAndBook(Passenger passenger){
        int age=passenger.getAge();
        String berthPreference=passenger.getBerthPreference();
        String gender=passenger.getGender();
        int seatNumber=checkBerthPreference(berthPreference,passenger);
        if(seatNumber!=0){
            return seatNumber;
        }
        if(gender.startsWith("F")||gender.startsWith("f")||age>60){
            seatNumber=checkBerthPreference("Lower",passenger);
        }
        if(seatNumber!=0){
            return seatNumber;
        }
        seatNumber=checkBerthPreference("Middle",passenger);
        if(seatNumber!=0){
            return seatNumber;
        }
        seatNumber=checkBerthPreference("Upper",passenger);
        if(seatNumber!=0){
            return seatNumber;
        }
        seatNumber=checkBerthPreference("Side Upper",passenger);
        if(seatNumber!=0){
            return seatNumber;
        }
        seatNumber=checkBerthPreference("Lower",passenger);

        return seatNumber;

    }
    public int checkBerthPreference(String berthPreference,Passenger passenger){
        int seatNumber=0;
        Map<String,List<Integer>>availableSeats=cache.getAvailableSeats();
        List<Integer>seats=availableSeats.get(berthPreference);
        if(seats!=null&&!seats.isEmpty()){
            seatNumber=seats.get(0);
            seats.remove(0);
            passenger.setBerth(berthPreference);
        }
        return seatNumber;
    }
    public String cancelTicket(int bookingId, int index){
        Map<Integer,Ticket>bookedSeats=cache.getBookedSeats();
        Ticket ticket=bookedSeats.get(bookingId);
        List<Passenger>passengers=ticket.getPassengers();
        Passenger passenger=passengers.get(index);
        int seatNumber=passenger.getSeatNumber();
        String status=passenger.getStatus();
        passengers.remove(index);
        if(passengers.isEmpty()){
            bookedSeats.remove(bookingId);
        }
        if(status.equalsIgnoreCase("Booked")){
            berthBasedFilling(seatNumber);
        }else if(status.equalsIgnoreCase("RAC")){
            cache.deleteRac(bookingId);
            racFilling(seatNumber);
        }
        return "Ticket Cancelled Successfully";
    }
    public void berthBasedFilling(int seatNumber){
        int bookingId=cache.getRac();
        if(bookingId!=0){
            Ticket ticket=cache.getBookedSeats(bookingId);
            List<Passenger>passengers=ticket.getPassengers();
            for(Passenger passenger : passengers){
                String status= passenger.getStatus();
                if(status.equalsIgnoreCase("RAC")){
                    int seatNumber1= passenger.getSeatNumber();
                    passenger.setSeatNumber(seatNumber);
                    passenger.setStatus("Booked");
                    racFilling(seatNumber1);
                    break;
                }
            }
        }else{
            String berth=berthCalculate(seatNumber);
            cache.setAvailableSeats(berth,seatNumber);
        }
    }
    public void racFilling(int seatNumber){
        int bookingId=cache.getWaitingList();
        if(bookingId!=0){
            Ticket ticket=cache.getBookedSeats(bookingId);
            List<Passenger>passengers=ticket.getPassengers();
            for(Passenger passenger : passengers){
                String status= passenger.getStatus();
                if(status.equalsIgnoreCase("WaitingList")){
                    passenger.setSeatNumber(seatNumber);
                    passenger.setStatus("Rac");
                    cache.addRac(bookingId);
                    break;
                }
            }
        }
    }
    public String cancelAllTickets(int bookingId){
        Map<Integer,Ticket>bookedSeats=cache.getBookedSeats();
        Ticket ticket=bookedSeats.get(bookingId);
        List<Passenger>passengers=ticket.getPassengers();
        while (passengers!=null&&passengers.size()!=0){
            cancelTicket(bookingId,0);
        }
        return "Ticket Cancelled Successfully";
    }
    public Map<String, Integer> getAvailableTickets(){
        Map<String,Integer>availableTickets=new HashMap<>();
        Map<String,List<Integer>>availableSeats=cache.getAvailableSeats();
        Set<String>seats=availableSeats.keySet();
        for(String seat:seats){
            int number=availableSeats.get(seat).size();
            availableTickets.put(seat,number);
        }
        return availableTickets;
    }
    public Map<Integer, Ticket> getBookedTickets(){
        Map<Integer,Ticket>bookedTickets=cache.getBookedSeats();
        return bookedTickets;
    }
    public Ticket getTickets(int bookingId){
        Map<Integer,Ticket>bookedTickets=cache.getBookedSeats();
        Ticket ticket=bookedTickets.get(bookingId);
        return ticket;
    }
    public boolean checkBookingId(int bookingId){
        Map<Integer,Ticket>bookedTickets=cache.getBookedSeats();
        return bookedTickets.containsKey(bookingId);
    }


}
