package railwayticketbooking;

import java.util.*;

public class InputLayer {
    public static void main(String[] args) {
        ProgramDriver driver=new ProgramDriver();
        Scanner scan=new Scanner(System.in);
        driver.initialSetUp();
        int bookingId=1;
        while(true){
            System.out.println("choose the decision");
            System.out.println("1.Book ticket\n2.cancel Ticket\n3.available Tickets\n4.Booked Tickets\n5.exit");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                Ticket ticket=new Ticket();
                ticket.setBookingId(bookingId);
                System.out.println("number of passengers");
                int numOfPassengers=scan.nextInt();
                scan.nextLine();
                for(int i=0;i<numOfPassengers;i++){
                    System.out.println("Name :" );
                    String name=scan.nextLine();
                    System.out.println("Address ; ");
                    String address=scan.nextLine();
                    System.out.println("Gender");
                    String gender=scan.nextLine();
                    System.out.println("Berth Preference");
                    String berthPreference=scan.nextLine();
                    System.out.println("Age :");
                    int age=scan.nextInt();
                    scan.nextLine();
                    Passenger passenger = getPassenger(name, address, gender, berthPreference, age);
                    ticket.setPassengers(passenger);
                }
                bookingId++;
                Ticket result= driver.bookTickets(ticket);
                System.out.println(result.toString());
            }else if(decision==2){
                System.out.println("Booking Id : ");
                int id =scan.nextInt();
                System.out.println("1.individual\n2.All");
                int decision1=scan.nextInt();
                if(driver.checkBookingId(id)){
                    if(decision1==1){
                        Ticket ticket=driver.getTickets(id);
                        List<Passenger>passengers=ticket.getPassengers();
                        printPassengers(passengers);
                        System.out.println("Enter the serial Number");
                        int index=scan.nextInt();
                        String result=driver.cancelTicket(id,index-1);
                        System.out.println(result);
                    }else if(decision1==2){
                        String result=driver.cancelAllTickets(id);
                        System.out.println(result);
                    }else {
                        System.out.println("Invalid Input");
                    }
                }else {
                    System.out.println("Invalid Booking Id");
                }

            }else if(decision==3){
                Map<String,Integer>availableTickets=driver.getAvailableTickets();
                System.out.println(availableTickets.toString());
            }else if(decision==4){
                Collection<Ticket> bookedTickets= driver.getBookedTickets().values();
                System.out.println(bookedTickets);
            }
            else if(decision==5){
                break;
            }
            else {
                System.out.println("Invalid Input");
            }
        }

    }

    private static void printPassengers(List<Passenger> passengers) {
        int i=1;
        for(Passenger passenger: passengers){
            System.out.println(i+"."+passenger);
        }
    }

    private static Passenger getPassenger(String name, String address, String gender, String berthPreference, int age) {
        Passenger passenger=new Passenger();
        passenger.setBerthPreference(berthPreference);
        passenger.setAddress(address);
        passenger.setName(name);
        passenger.setAge(age);
        passenger.setGender(gender);
        return passenger;
    }
}
