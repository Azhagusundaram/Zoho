package tollpaymentprocessing;

import java.util.HashMap;
import java.util.Map;

public class TollManagement {
    private Map<String,Toll> tolls=new HashMap<>();
    private Map<String,Vehicle>vehicles=new HashMap<>();

    public Map<String,Toll> getTolls(){
        return tolls;
    }
    public Map<String,Vehicle> getVehicles(){
        return vehicles;
    }
    public void setVehicles(Vehicle vehicle){
        String vehicleNumber=vehicle.getVehicleNumber();
        vehicles.put(vehicleNumber,vehicle);
    }
}
