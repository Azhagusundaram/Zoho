package movieticketbooking;

import java.util.*;

public class InputLayer {
    static  Scanner scan=new Scanner(System.in);
    public static void main(String[] args) {

        ProgramDriver driver=new ProgramDriver();
        List<Movie>movies=initialSetUp();
        driver.initialSetUp(movies);

        while (true){
            int number;
            System.out.println("1.BookTicket\n2.Available Tickets\n3.Exit");
            System.out.println("Enter the decision :");
            int decision=scan.nextInt();
            if(decision==1){

                List<String>movieList= driver.getMovieList();
                System.out.println(movieList);
                System.out.println("Enter the movie Number :");
                number=scan.nextInt();
                String movieName=getNames(movieList.get(number-1));
                List<String>timingList=driver.getTimings(movieName);
                System.out.println(timingList);
                System.out.println("Enter the timing Number :");
                number=scan.nextInt();
                scan.nextLine();
                String timing=getNames(timingList.get(number-1));
                System.out.println("Enter the ticket Type(Deluxe/SuperDeluxe)");
                String ticketType=scan.nextLine();
                List<String>seatNumbers=new ArrayList<>();
                if(ticketType.startsWith("D")||ticketType.startsWith("d")){
                    ticketType="Deluxe";
                    seatNumbers=driver.getTicketList(movieName,timing,ticketType);
                }else if(ticketType.startsWith("s")||ticketType.startsWith("S")){
                    ticketType="Super Deluxe";
                    seatNumbers=driver.getTicketList(movieName,timing,ticketType);
                }else {
                    System.out.println("Wrong Input");
                }
                System.out.println(seatNumbers);
                boolean flag=true;
                List<String>bookingSeats=new ArrayList<>();
                while (flag){
                    System.out.println("Enter Seat Number ");
                    bookingSeats.add(scan.nextLine());
                    System.out.println("1.Continue booking\n2.exit");
                    int decision2=scan.nextInt();
                    scan.nextLine();
                    if(decision2==2){
                        flag=false;
                    }
                }
                TicketBooking ticket = getTicketBooking(movieName, timing, ticketType, bookingSeats);
                TicketBooking ticketBooking=driver.bookTickets(ticket);
                System.out.println(ticketBooking);
            }else if(decision==2){
                Collection<Movie> availableTickets=driver.getAvailableTickets().values();
                System.out.println(availableTickets);
            }else if(decision==3){
                break;
            }else {
                System.out.println("Invalid Input");
            }

        }


    }

    private static TicketBooking getTicketBooking(String movieName, String timing, String ticketType, List<String> bookingSeats) {
        TicketBooking ticket=new TicketBooking();
        ticket.setMovieName(movieName);
        ticket.setTime(timing);
        ticket.setSeats(bookingSeats, ticketType);
        return ticket;
    }

    public static String getNames(String name){
        String[]array=name.split("\\.");
        return array[1];
    }
    public static List<Movie> initialSetUp(){
        List<String>showTimings=new ArrayList<>(Arrays.asList("9:00","12:00","15:00","18:00","22:00"));
        System.out.println("Number of movies ");
        int numOfMovies = scan.nextInt();
        scan.nextLine();
        List<Movie>movies=new ArrayList<>();
        for (int i = 0; i < numOfMovies; i++) {
            System.out.println("Enter the Movie Name :");
            String movieName = scan.nextLine();
            Movie movie = new Movie();
            movie.setMovieName(movieName);
            System.out.println("Number of Shows :");
            int numOfShows = scan.nextInt();
            System.out.println("Enter Deluxe Ticket Price :");
            int deluxePrice = scan.nextInt();
            System.out.println("Enter Super Deluxe Price :");
            int superDeluxePrice = scan.nextInt();
            movie.setDeluxePrice(deluxePrice);
            movie.setSuperDeluxePrice(superDeluxePrice);
            for (int j = 0; j < numOfShows; j++) {
                Time time = new Time();
                time.setTime(showTimings.get(j));
                System.out.println("Number of Deluxe Ticket in show "+(j+1)+" :");
                int numOfDeluxe = scan.nextInt();
                System.out.println("Number of Super Deluxe Ticket "+(j+1)+":");
                int numOfSuperDeluxe = scan.nextInt();
                System.out.println("Number of Seats per Row");
                int numOfSeats = scan.nextInt();
                scan.nextLine();
                char seatIndex = 'A';
                int number=1;
                for (int k = 1; k <= numOfDeluxe; k++) {
                    String seatNumber = seatIndex + "" + number;
                    String seatType = "Deluxe";
                    if (k % numOfSeats == 0) {
                        seatIndex += 1;
                        number=1;
                    }
                    number++;
                    time.setSeats(seatNumber, seatType);
                }
                number=1;
                for (int l = 1; l <= numOfSuperDeluxe; l++) {
                    String seatNumber = seatIndex + "" + number;
                    String seatType = "Super Deluxe";
                    if (l % numOfSeats == 0) {
                        seatIndex += 1;
                        number=1;
                    }
                    number++;
                    time.setSeats(seatNumber, seatType);
                }
                movie.setTimings(time);
            }
            movies.add(movie);
        }
        return movies;
    }
}
