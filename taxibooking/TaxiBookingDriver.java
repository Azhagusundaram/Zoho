package taxibooking;

import java.util.*;

public class TaxiBookingDriver {
    BookingManagement cache=new BookingManagement();
    int bookingId =1;
    List<Character>positions=new ArrayList<>(Arrays.asList('A','B','C','D','E','F'));
    public String bookTaxi(Customer customer){
        Taxi taxi=checkTaxiAvailability(customer);
        if(taxi!=null){
            char position=customer.getDropPlace();
            taxi.setPosition(position);
            int totalTime=getTime(customer);
            taxi.addTime(totalTime);
            customer.setDropTime(totalTime);
            int amount=getAmount(totalTime);
            taxi.setTotalAmount(amount);
            customer.setAmount(amount);
            customer.setBookingId(bookingId);
            bookingId++;
            cache.setAllBookingDetails(taxi,customer);
            cache.setAllTaxis(taxi);

            return "Booking Id is "+bookingId+"\nTaxi-"+taxi.getTaxiNumber()+" is allotted";
        }
        return "No Taxi Available";
    }

    private Taxi checkTaxiAvailability(Customer customer) {
        char pickUpPlace=customer.getPickUpPlace();
        int pickUpTime=customer.getPickupTime();
        Map<Character, List<Taxi>> allTaxis= cache.getAllTaxis();
        int loopCount=1;
        int index=positions.indexOf(pickUpPlace);
        boolean flag=true;
        while(loopCount< positions.size()*2){
            if(index>=0&&index<positions.size()){
                pickUpPlace =positions.get(index);
                List<Taxi>taxis= allTaxis.get(pickUpPlace);
                if(taxis!=null&&!taxis.isEmpty()){
                    Taxi taxi=taxis.get(0);
                    if(taxi.getTime()<=pickUpTime){
                        taxi.setTime(pickUpTime);
                        taxis.remove(taxi);
                        return taxi;
                    }
                }
            }
            loopCount++;
            flag=!flag;
            if(flag){
                index=index+loopCount;
            }else {
                index=index-loopCount;
            }
        }
        return null;
    }
    private int getTime(Customer customer){
        char pickUpPlace=customer.getPickUpPlace();
        char dropPlace=customer.getDropPlace();
        int index1=positions.indexOf(pickUpPlace);
        int index2=positions.indexOf(dropPlace);
        int totalTime=Math.abs(index1-index2);
        return totalTime;
    }
    private int getAmount(int time){
        int distance=time*15;
        int amount=(distance*10)+50;
        return amount;
    }

    public void initialSetUp(int numOfTaxis){
        for(int i=1;i<=numOfTaxis;i++){
            Taxi taxi=new Taxi();
            taxi.setPosition('A');
            taxi.setTime(0);
            taxi.setTaxiNumber(i);
            cache.setAllTaxis(taxi);
        }
    }
    public Map<Taxi, List<Customer>> getAllDetails(){
        Map<Taxi, List<Customer>> allBookingDetails=cache.getAllBookingDetails();
        return allBookingDetails;
    }
}