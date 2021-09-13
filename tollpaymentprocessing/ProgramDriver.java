package tollpaymentprocessing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProgramDriver {
    TollManagement cache=new TollManagement();
    List<String> stops=new ArrayList<>();
    public void setStops(List<String> stops){
        this.stops.addAll(stops);
    }
    public void setToll(Map<String,List<Integer>> chargingScheme){
        Map<String,Toll>tolls=cache.getTolls();
        Set<String>tollGates=chargingScheme.keySet();
        for(String tollName:tollGates){
            Toll toll=new Toll();
            toll.setTollName(tollName);
            List<Integer>charging=chargingScheme.get(tollName);
            toll.setChargingScheme(charging);
            tolls.put(toll.getTollName(),toll);
        }
    }
    public int setJourney(Vehicle vehicle){
        int route=findRoute(vehicle);
        if(route==1){
            setClockWiseRoute(vehicle);
            return 1;
        }else if(route==-1){
            setAntiClockWiseRoute(vehicle);
            return 1;
        }else {
            return 0;
        }

    }
    private void setAntiClockWiseRoute(Vehicle vehicle){
        String start=vehicle.getStart();
        String dest=vehicle.getDest();
        int startIndex=stops.indexOf(start);
        int destIndex=stops.indexOf(dest);
        if(startIndex<destIndex){
            for(int i=startIndex;i>=0;i--){
                String cityName=stops.get(i);
                checkToll(vehicle,cityName);
            }
            for(int i=destIndex;i<stops.size();i++){
                String cityName=stops.get(i);
                checkToll(vehicle,cityName);
            }
        }else {
            for(int i=startIndex;i<stops.size();i++){
                String cityName=stops.get(i);
                checkToll(vehicle,cityName);
            }
            for(int i=destIndex;i>=0;i--){
                String cityName=stops.get(i);
                checkToll(vehicle,cityName);
            }
        }
        cache.setVehicles(vehicle);
    }
    private void setClockWiseRoute(Vehicle vehicle){
        String start=vehicle.getStart();
        String dest=vehicle.getDest();
        int startIndex=stops.indexOf(start);
        int destIndex=stops.indexOf(dest);

        if(startIndex>destIndex){
            for(int i=startIndex;i>=destIndex;i--){
                String cityName=stops.get(i);
                checkToll(vehicle,cityName);
            }

        }else{
            for(int i=startIndex;i<=destIndex;i++){
                String cityName=stops.get(i);
                checkToll(vehicle,cityName);
            }
        }
        cache.setVehicles(vehicle);
    }
    private void checkToll(Vehicle vehicle,String cityName){
        Map<String,Toll>tolls=cache.getTolls();
        String vehicleNumber=vehicle.getVehicleNumber();
        if(tolls.containsKey(cityName)){
            Toll toll=tolls.get(cityName);
            double amount=getAmount(vehicle,toll);
            vehicle.setAmount(amount);
            vehicle.setTollsPassed(cityName);
            toll.setTotalAmount(amount);
            toll.setVehiclesPassed(vehicleNumber,amount);
        }
    }
    private int findRoute(Vehicle vehicle){
        String start=vehicle.getStart();
        String dest=vehicle.getDest();
        int startIndex=stops.indexOf(start);
        int destIndex=stops.indexOf(dest);
        int clockWiseRoute;
        int antiClockWiseRoute;
        if(startIndex==destIndex){
            return 0;
        }
        else if(startIndex<destIndex){
            clockWiseRoute=destIndex-startIndex;
            antiClockWiseRoute=startIndex+(stops.size()-destIndex);

        }else{
            clockWiseRoute=startIndex-destIndex;
            antiClockWiseRoute=destIndex+(stops.size()-startIndex);
        }
        if(clockWiseRoute-antiClockWiseRoute>0){
            return -1;
        }else {
            return 1;
        }
    }
    private double getAmount(Vehicle vehicle,Toll toll){
        double amount=0;
        String vehicleType=vehicle.getVehicleType();
        String vip=vehicle.getVip();
        List<Integer>chargingScheme=toll.getChargingScheme();
        if(vehicleType.startsWith("C")||vehicleType.startsWith("c")){
            amount=chargingScheme.get(0);
        }else if (vehicleType.startsWith("B")||vehicleType.startsWith("b")){
            amount=chargingScheme.get(1);
        } else if (vehicleType.startsWith("T")||vehicleType.startsWith("t")){
            amount=chargingScheme.get(2);
        } else if (vehicleType.startsWith("L")||vehicleType.startsWith("l")){
            amount=chargingScheme.get(3);
        }
        if(vip.startsWith("y")||vip.startsWith("Y")){
            amount=amount*0.8;
        }
        return amount;
    }
    public void getVehicleAmount(String vehicleNumber){
        Map<String,Vehicle>vehicleMap= cache.getVehicles();
        Vehicle vehicle=vehicleMap.get(vehicleNumber);
        double amount=vehicle.getAmount();
        System.out.println(amount);
    }
    public StringBuilder getAllTolls(){
        Map<String,Toll>allTolls= cache.getTolls();
        if(allTolls!=null&&!allTolls.isEmpty()){
            Set<String>tolls=allTolls.keySet();
            StringBuilder stringBuilder=new StringBuilder();
            for(String tollName:tolls){
                Toll toll=allTolls.get(tollName);
                Map<String, Double> vehiclePassed=toll.getVehiclesPassed();
                double totalAmount=toll.getTotalAmount();
                stringBuilder.append("\n"+tollName);
                stringBuilder.append(vehiclePassed.toString());
                stringBuilder.append("Total Amount : "+totalAmount);
            }
            return stringBuilder;
        }
        return new StringBuilder("Tolls are Empty");

    }
    public StringBuilder getAllVehicles(){
        Map<String,Vehicle>allVehicles=cache.getVehicles();
        if(allVehicles==null&&allVehicles.isEmpty()){
            return new StringBuilder("Vehicles list is empty");
        }
        Set<String>vehicles= allVehicles.keySet();
        StringBuilder stringBuilder=new StringBuilder();
        for(String vehicleNumber:vehicles){
            Vehicle vehicle=allVehicles.get(vehicleNumber);
            stringBuilder.append(vehicle);
        }
        return stringBuilder;
    }
}
