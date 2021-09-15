package movieticketbooking;

import java.util.*;

public class Time {
    private String time;
    Map<String, List<String>> seats=new HashMap<>();


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Map<String, List<String>> getSeats() {
        return seats;
    }

    public void setSeats(String seatNumber,String seatType) {

        List<String>tempSeats=seats.get(seatType);
        if(tempSeats==null){
            tempSeats=new ArrayList<>();
            seats.put(seatType,tempSeats);
        }
        tempSeats.add(seatNumber);
    }
    @Override
    public String toString(){
        Map<String,Integer>availableSeats=new HashMap<>();
        Set<String>seatTypes=seats.keySet();
        for(String seatType:seatTypes){
            int number=seats.get(seatType).size();
            availableSeats.put(seatType,number);
        }
        return availableSeats.toString();
    }
}
