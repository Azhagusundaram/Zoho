package tollpaymentprocessing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Toll {
    private String tollName;
    private double totalAmount=0;
    private List<Integer> chargingScheme;
    private Map<String,Double>vehiclesPassed=new HashMap<>();

    public String getTollName() {
        return tollName;
    }

    public void setTollName(String tollName) {
        this.tollName = tollName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount+= totalAmount;
    }

    public List<Integer> getChargingScheme() {
        return chargingScheme;
    }

    public void setChargingScheme(List<Integer> chargingScheme) {
        this.chargingScheme = chargingScheme;
    }

    public Map<String,Double> getVehiclesPassed() {
        return vehiclesPassed;
    }

    public void setVehiclesPassed(String vehicleNumber,double amount) {
        vehiclesPassed.put(vehicleNumber,amount);
    }
}
