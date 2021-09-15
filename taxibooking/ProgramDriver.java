package taxibooking;

import java.util.*;

public class ProgramDriver {
    BookingManagement cache=new BookingManagement();
    List<Character>positions=new ArrayList<>(Arrays.asList('A','B','C','D','E','F'));
    public void bookTaxi(Customer customer){
        char pickUpPlace=customer.getPickUpPlace();
        int pickUpTime=customer.getPickupTime();
        Map<Character, Set<Taxi>> allTaxis= cache.getAllTaxis();
        int loopCount=1;
        while(pickUpPlace>='A'||pickUpPlace<='F'){
            if(pickUpPlace>='A'){
                Set<Taxi>taxis=allTaxis.get(pickUpPlace);
                    for(Taxi taxi:taxis){
                        if(taxi.getTime()<pickUpTime){
                            Taxi bookedTaxi=taxi;
                        }
                        break;
                    }
                    
                pickUpPlace= (char) ((int)pickUpPlace+loopCount);
            }
            loopCount++;
            if(pickUpPlace<='F'){
                Set<Taxi>taxis=allTaxis.get(pickUpPlace);
                for(Taxi taxi:taxis){
                    if(taxi.getTime()<pickUpTime){
                        Taxi bookedTaxi=taxi;
                    }
                    break;
                }
                pickUpPlace= (char) ((int)pickUpPlace-loopCount);
            }
           loopCount++;
        }

    }
    public void initialSetUp(int numOfTaxis){
        for(int i=1;i<=numOfTaxis;i++){
            Taxi taxi=new Taxi();
            taxi.setPosition('A');
            taxi.setTime(9);
            taxi.setTaxiNumber(i);
            cache.setAllTaxis(taxi);
        }
    }
}
