
package taxibooking;

import java.util.*;

public class BookingManagement {
    private Map<Character, List<Taxi>>allTaxis=new HashMap<>();
    private Map<Taxi,List<Customer>>allBookingDetails=new HashMap<>();
    TaxiComparator comparator=new TaxiComparator();
    public void setAllTaxis(Taxi taxi){
        char position=taxi.getPosition();
        List<Taxi>taxis=allTaxis.get(position);
        if(taxis==null){
            taxis=new ArrayList<>();
            taxis.sort(comparator);
            allTaxis.put(position,taxis);
        }
        taxis.add(taxi);
    }
    public Map<Character, List<Taxi>> getAllTaxis(){
        return allTaxis;
    }
    public void setAllBookingDetails (Taxi taxi,Customer customer){
        List<Customer>customers=allBookingDetails.get(taxi);
        if(customers==null){
            customers=new ArrayList<>();
            allBookingDetails.put(taxi,customers);
        }
        customers.add(customer);
    }
    public Map<Taxi, List<Customer>> getAllBookingDetails(){
        return allBookingDetails;
    }
}