package hotelmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        HotelDriver driver=new HotelDriver();
        System.out.println("Number of hotels");
        int numOfHotels=scan.nextInt();
        scan.nextLine();
        List<Hotel> allhotels =new ArrayList<>(numOfHotels);
        for(int i=0;i<numOfHotels;i++){
            System.out.println("Hotel Name");
            String name=scan.nextLine();
            System.out.println("Location :");
            String location=scan.nextLine();
            System.out.println("Rooms Available");
            int roomsAvailable=scan.nextInt();
            System.out.println("Rating");
            int rating=scan.nextInt();
            System.out.println("Price per Room");
            int pricePerRoom=scan.nextInt();
            scan.nextLine();
            Hotel hotel = getHotel(name, location, roomsAvailable, rating, pricePerRoom);
            allhotels.add(hotel);
        }
        driver.initialSetUp(allhotels);
        while (true){
            System.out.println("\n1.Print Hotel Data\n2.Sort by hotel Name\n3.sort by Hotel ratings\n4.Print hotel data for location\n5.sort by rooms available in hotel\n6.print user booking data\n7.book hotel\n8.exit");
            System.out.println("enter the choice :");
            int decision=scan.nextInt();
            if(decision==1){
                List<Hotel>hotels= driver.getAllHotels();
                printHotels(hotels);
            }else if(decision==2){
                List<Hotel>hotels= driver.getHotelsSortByName();
                printHotels(hotels);
            }else if(decision==3){
                List<Hotel>hotels= driver.getHotelsSortByRating();
                printHotels(hotels);
            }else if(decision==4){
                List<String>locations=driver.getLocations();
                System.out.println(locations);
                System.out.println("Enter the location number :");
                int index=scan.nextInt()-1;
                String location=getName(locations.get(index));
                List<Hotel>hotels= driver.getHotels(location);
                printHotels(hotels);
            } else if(decision==5){
                List<Hotel>hotels= driver.getHotelsSortByRoomsAvailable();
                printHotels(hotels);
            }else if(decision==6){
                List<User>users=driver.getUsers();
                printHotels(users);
            }else if(decision==7){
                System.out.println("User Name");
                String name=scan.nextLine();
                List<String>hotels= driver.getHotelNames();
                printHotels(hotels);
                System.out.println("enter the hotel number");
                int index=scan.nextInt();
                String hotelName=getName(hotels.get(index));
                System.out.println("Enter the Number of Rooms");
                int numOfRooms=scan.nextInt();
                User user = getUser(name, hotelName, numOfRooms);
                String result=driver.bookHotel(user);
                System.out.println(result);
            }else if(decision==8){
                break;
            }else {
                System.out.println("Invalid Input");
            }

        }
    }

    private static User getUser(String name, String hotelName, int numOfRooms) {
        User user=new User();
        user.setUserName(name);
        user.setHotelName(hotelName);
        user.setNumberOfRoomsBooked(numOfRooms);
        return user;
    }

    private static Hotel getHotel(String name, String location, int roomsAvailable, int rating, int pricePerRoom) {
        Hotel hotel=new Hotel();
        hotel.setHotelName(name);
        hotel.setRoomsAvailable(roomsAvailable);
        hotel.setLocation(location);
        hotel.setRating(rating);
        hotel.setPricePerRoom(pricePerRoom);
        return hotel;
    }

    private static void printHotels(List hotels){
        if(hotels.isEmpty()){
            System.out.println("Empty");
        }
        for(Object hotel:hotels){
            System.out.println(hotel);
        }
    }
    private static String getName(String name){
        String[]array=name.split("\\.");
        return array[1];
    }
}
