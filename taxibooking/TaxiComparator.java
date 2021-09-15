package taxibooking;

import java.util.Comparator;

public class TaxiComparator implements Comparator<Taxi> {
    @Override
    public int compare(Taxi o1, Taxi o2) {
        if (o1.getTime()==(o2.getTime())) {
            if (o1.getTotalAmount() == o2.getTotalAmount()) {
                return o1.getTaxiNumber() - o2.getTaxiNumber();
            }
            return o1.getTotalAmount() - o2.getTotalAmount();
        }
        return o1.getTime()-o2.getTime();
    }
}
