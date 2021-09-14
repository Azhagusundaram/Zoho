package carparking;

import java.util.Comparator;

public class SlotComparator implements Comparator<SlotSpace> {

    @Override
    public int compare(SlotSpace o1, SlotSpace o2) {

        if(o1.getFloorNumber()<o2.getFloorNumber()){
            return -1;
        }else if(o1.getFloorNumber()== o2.getFloorNumber()){
            if(o1.getSlotNumber()<o2.getSlotNumber()){
                return -1;
            }
        }
        return 0;
    }
}
