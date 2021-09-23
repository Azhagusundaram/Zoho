package liftsystem;

import java.util.HashMap;
import java.util.Map;

public class LiftManagement {
    private Map<Integer,Lift>lifts=new HashMap<>();

    public Map<Integer, Lift> getLifts() {
        return lifts;
    }

    public void setLifts(Lift lift) {
        int liftNumber=lift.getLiftNumber();
        lifts.put(liftNumber,lift);
    }
}
