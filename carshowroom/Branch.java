package carshowroom;

import java.util.HashMap;
import java.util.Map;

public class Branch {
    private int branchId;
    private String branchName;
    private Map<Integer,Integer> availableCars =new HashMap<>();
    private Map<Integer,Integer>soldCars=new HashMap<>();

    public void setAvailableCars(int carId, int quantity){
        Integer numOfCars= availableCars.get(carId);
        if(numOfCars==null){
            numOfCars=0;
        }
        numOfCars=numOfCars+quantity;
        availableCars.put(carId,numOfCars);
    }
    public boolean setSoldCar(int carId){
        Integer numOfCars= availableCars.get(carId);
        numOfCars=numOfCars-1;
        availableCars.put(carId,numOfCars);
        return true;
    }
    public Map<Integer, Integer> setSoldCars(){
        return soldCars;
    }

    public Map<Integer, Integer> getAvailableCars() {
        return availableCars;
    }
    public void removeAvailableCar(int carId){
        Integer numOfCars= availableCars.get(carId);
        numOfCars=numOfCars-1;
        if(numOfCars==0){
            availableCars.remove(carId);
            return;
        }
        availableCars.put(carId,numOfCars);
    }
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String toString(){
        return "\nBranch Id : "+branchId+"\tBranch Name : "+branchName;
    }
}
