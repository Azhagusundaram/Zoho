package carshowroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowroomManagement {
    private Map<Integer,City> cities =new HashMap<>();
//    private Map<Integer,City>soldCarsInCities=new HashMap<>();
    private List<SoldCar>soldCars=new ArrayList<>();
    private Map<Integer,Customer>customerDetails=new HashMap<>();
    private Map<Integer,Car>cars=new HashMap<>();
    private List<Integer>sales=new ArrayList<>();

    public void setSales(List<Integer>sales){
        this.sales=sales;
    }

    public List<Integer> getSales() {
        return sales;
    }

    public void setCity(City city){
        int cityId=city.getCityId();
        cities.put(cityId,city);
    }
    public void setSoldCar(SoldCar soldCar){
        soldCars.add(soldCar);
    }
    public void setCustomerDetails(Customer customer){
        int customerId=customer.getCustomerId();
        customerDetails.put(customerId,customer);
    }

    public Map<Integer, City> getCities() {
        return cities;
    }
    public void setCars(Car car){
        int carId=car.getCarId();
        cars.put(carId,car);
    }

//    public Map<Integer, City> getSoldCarsInCities() {
//        return soldCarsInCities;
//    }

    public List<SoldCar> getSoldCars() {
        return soldCars;
    }

    public Map<Integer, Customer> getCustomerDetails() {
        return customerDetails;
    }

    public Map<Integer, Car> getCars() {
        return cars;
    }
}
