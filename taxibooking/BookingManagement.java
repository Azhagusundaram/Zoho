package taxibooking;

import java.util.*;

public class BookingManagement {
    private Map<Character, Set<Taxi>>allTaxis=new HashMap<>();
    private Map<Integer,List<Customer>>allBookingDetails=new HashMap<>();
    TaxiComparator comparator=new TaxiComparator();
    public void setAllTaxis(Taxi taxi){
        char position=taxi.getPosition();
        Set<Taxi>taxis=allTaxis.get(position);
        if(taxis==null){
            taxis=new TreeSet<>(comparator);
            allTaxis.put(position,taxis);
        }
        taxis.add(taxi);
    }
    public Map<Character, Set<Taxi>> getAllTaxis(){
        return allTaxis;
    }
}
