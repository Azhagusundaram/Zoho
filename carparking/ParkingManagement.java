package carparking;

import java.util.*;

public class ParkingManagement {
    SlotComparator comparator=new SlotComparator();
    private List<SlotSpace>availableSpaces=new ArrayList<>();
    private List<SlotSpace> reservedSpace=new ArrayList<>();
    private Map<Integer,Car> parkedSpace=new HashMap<>();
    private List<Integer>reservedCars=new ArrayList<>();

    public List<SlotSpace> getReservedSpace() {
        return reservedSpace;
    }

    public void setReservedSpace(List<SlotSpace>slots) {
        reservedSpace.addAll(slots);
        reservedSpace.sort(comparator);
    }
    public void setReservedSpace(SlotSpace slot) {
        reservedSpace.add(slot);
        reservedSpace.sort(comparator);
    }


    public void setAvailableSpace(List<SlotSpace>slots){
        availableSpaces.addAll(slots);
        availableSpaces.sort(comparator);
    }
    public void setAvailableSpace(SlotSpace slot){
        availableSpaces.add(slot);
        availableSpaces.sort(comparator);
    }

    public List <SlotSpace> getAvailableSpace() {
        return availableSpaces;
    }

    public Map<Integer, Car> getParkedSpace() {
        return parkedSpace;
    }

    public void setParkedSpace(int carNumber,Car car) {
        parkedSpace.put(carNumber,car);
    }

    public List<Integer> getReservedCars() {
        return reservedCars;
    }

}