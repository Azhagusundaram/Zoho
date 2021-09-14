package flightticketbooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ProgramDriver driver= new ProgramDriver();
        driver.initialSetUp();
        while (true){
            System.out.println("Choose the decision :");
            System.out.println("\n1.Book Ticket\n2.Cancel Ticket\n3.Available Tickets\n4.Flight Summary\n5.exit");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                System.out.println("Number of Passengers");
                int numOfPassengers=scan.nextInt();
                System.out.println("Flight Number");
                int flightNumber=scan.nextInt();
                scan.nextLine();
                List<Passenger>passengers=new ArrayList<>(numOfPassengers);
                for(int i=0;i<numOfPassengers;i++){
                    System.out.println("Name");
                    String name=scan.nextLine();
                    System.out.println("Address");
                    String address=scan.nextLine();
                    System.out.println("Business/Economy");
                    String ticketType=scan.nextLine();
                    System.out.println("Are you want Meal(Yes/No)");
                    String meal=scan.nextLine();
                    Passenger passenger = getPassenger(name, address, ticketType, meal);
                    passengers.add(passenger);
                }
                String bookingStatus= driver.bookTicket(passengers,flightNumber);
                System.out.println(bookingStatus);
            }
            else if(decision==2){

                System.out.println("Flight Number :");
                int flightNumber=scan.nextInt();
                System.out.println("Ticket Number :");
                int ticketNumber=scan.nextInt();
                System.out.println("1.individual\n2.All");
                int decision1=scan.nextInt();
                if(decision1==1){
                    System.out.println("Enter seat Number ");
                    int seatNumber=scan.nextInt();
                    String result=driver.cancelTicket(flightNumber,ticketNumber,seatNumber);
                    System.out.println(result);
                }else if(decision1==2) {
                    String result=driver.cancelTicket(flightNumber,ticketNumber,0);
                    System.out.println(result);
                }else {
                    System.out.println("Invalid Input");
                }
            }else if(decision==3){
                Map<Integer,Map<String,Integer>>availableSeats=driver.getAvailableTickets();
                System.out.println(availableSeats.toString());
            }else if(decision==4){
                System.out.println("Enter flight Number");
                int flightNumber=scan.nextInt();
                String result=driver.getFlightSummary(flightNumber);
                System.out.println(result);
            }else if(decision==5){
                break;
            }
            else {
                System.out.println("Invalid Input");
            }
        }
    }

    private static Passenger getPassenger(String name, String address, String ticketType, String meal) {
        Passenger passenger=new Passenger();
        passenger.setAddress(address);
        passenger.setName(name);
        passenger.setMeal(meal);
        if(ticketType.startsWith("b")||ticketType.startsWith("B")){
            ticketType="Business";
        }else if(ticketType.startsWith("e")||ticketType.startsWith("E")){
            ticketType="Economy";
        }
        passenger.setTicketType(ticketType);
        return passenger;
    }

}

