package tollpaymentprocessing;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String start;
    private String dest;
    private String vehicleNumber;
    private String vehicleType;
    private double amount=0;
    private String vip;
    private List<String> tollsPassed=new ArrayList<>();

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount+= amount;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public List<String> getTollsPassed() {
        return tollsPassed;
    }

    public void setTollsPassed(String toll) {
        tollsPassed.add(toll);
    }
    @Override
    public String toString(){
        return "\nVehicle Number : "+vehicleNumber+" Starting Point : "+start+" Destination "+dest+" Tolls Passed : "+tollsPassed+" Total Amount Paid : "+amount;
    }
}
