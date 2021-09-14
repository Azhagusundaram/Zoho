package carparking;

import java.util.ArrayList;
import java.util.List;

public class DailyManagement {
    private int totalFare=0;
    private int numberOfCarsParked;
    private List<Car>cars=new ArrayList<>();

    public int getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(int totalFare) {
        this.totalFare+= totalFare;
    }
    public void setCars(Car car){
        cars.add(car);
    }
    public int getNumberOfCars(){
        return cars.size();
    }
}
