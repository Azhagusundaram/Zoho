package carparking;

import java.util.*;

public class ProgramDriver {
    ParkingManagement cache=new ParkingManagement();
    DailyManagement day=new DailyManagement();
    public void addSlots(int numOfFloors,int numOfSlots){
        List<SlotSpace> temporarySlots=new ArrayList<>();
        List<SlotSpace>reservedSlots=new ArrayList<>();
        for(int i=1;i<=numOfFloors;i++){
            for(int j=1;j<=numOfSlots;j++){
                SlotSpace slotSpace = getSlotSpace(i, j);
                if(j<=2){
                    slotSpace.setReserved(true);
                    reservedSlots.add(slotSpace);
                    continue;
                }
                temporarySlots.add(slotSpace);
            }
        }
        cache.setAvailableSpace(temporarySlots);
        cache.setReservedSpace(reservedSlots);
    }

    private SlotSpace getSlotSpace(int i, int j) {
        SlotSpace slotSpace=new SlotSpace();
        slotSpace.setSlotNumber(j);
        slotSpace.setFloorNumber(i);
        slotSpace.setBooked(false);
        return slotSpace;
    }

    public void setReservedCars(){
        List<Integer>reservedCars=new ArrayList<>();
        reservedCars.addAll(Arrays.asList(120,140,130,150,160,170));
    }
    public String findParkingSpace(Car car){
        boolean reserved=car.getReserved();
        int time=car.getEntryTime();
        List<SlotSpace>availableSpaces= cache.getAvailableSpace();
        List<SlotSpace>reservedSpaces=cache.getReservedSpace();
        if(!availableSpaces.isEmpty()||!reservedSpaces.isEmpty()){
            String result="";
            if(time<540){
                if(reserved==true){
                    SlotSpace slotSpace=reservedSpaces.get(0);
                    result=bookSlot(car, slotSpace);
                    reservedSpaces.remove(0);
                }else if(!availableSpaces.isEmpty()){
                    SlotSpace slotSpace=availableSpaces.get(0);
                    result=bookSlot(car, slotSpace);
                    availableSpaces.remove(0);
                }else {
                    result="Space Not Available";
                }
            }else {
                if(!availableSpaces.isEmpty()){
                    SlotSpace slotSpace=availableSpaces.get(0);
                    result=bookSlot(car, slotSpace);
                    availableSpaces.remove(0);
                }else {
                    SlotSpace slotSpace=reservedSpaces.get(0);
                    result=bookSlot(car, slotSpace);
                    reservedSpaces.remove(0);
                }
            }
            return result;
        }

        return "Slot Not Available";
    }
    public int getAvailableSpaces(int time){
        System.out.println(time);
        int number=0;
        if(time<540){
            number=cache.getAvailableSpace().size();
        }else {
            number=cache.getAvailableSpace().size();
            number+=cache.getReservedSpace().size();
        }
        return number;
    }
    public String getFare(int carNumber,int exitTime){
        String result="";
        Map<Integer,Car> cars=cache.getParkedSpace();
        if(checkCarNumber(carNumber)){
           Car car=cache.getParkedSpace().get(carNumber);
           int entryTime=car.getEntryTime();
           int totalTime = getTotalTime(exitTime, entryTime);
           int amount=totalTime*50;
           car.setAmount(amount);
           car.setExitTime(exitTime);
           day.setTotalFare(amount);
           day.setCars(car);
           cars.remove(carNumber);
           int slotNumber=car.getSlotNumber();
           int floorNumber=car.getFloorNumber();
           SlotSpace slotSpace=getSlotSpace(floorNumber,slotNumber);
           if(slotNumber<=2){
               cache.setReservedSpace(slotSpace);
           }
            cache.setAvailableSpace(slotSpace);
           return "Total Fare : "+amount;
        }
        return "Invalid Car Number";
    }

    private int getTotalTime(int exitTime, int entryTime) {
        int totalTime=Math.abs(exitTime - entryTime);
        totalTime=totalTime/60;
        int reminder=totalTime%60;
        if(reminder!=0){
            totalTime+=1;
        }
        return totalTime;
    }

    private String bookSlot(Car car, SlotSpace slotSpace) {
        slotSpace.setBooked(true);
        int carNumber=car.getCarNumber();
        int slotNumber = slotSpace.getSlotNumber();
        int floorNumber = slotSpace.getFloorNumber();
        car.setSlotNumber(slotNumber);
        car.setFloorNumber(floorNumber);
        cache.setParkedSpace(carNumber,car);
        return "Slot Number : "+slotNumber+" Floor Number : "+floorNumber;
    }

    public boolean checkReserved(int carNumber){
        List<Integer>reservedCars=cache.getReservedCars();
        return reservedCars.contains(carNumber);
    }
    public boolean checkCarNumber(int carNumber){
        return cache.getParkedSpace().containsKey(carNumber);
    }
    public String getDailyReport(){
        int totalFare=day.getTotalFare();
        int numberOfCars=day.getNumberOfCars();
        return "Total Fare : "+totalFare+" Number of Cars parked : "+numberOfCars;
    }
}
