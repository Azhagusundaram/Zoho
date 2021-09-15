package taxibooking;

import java.util.ArrayList;
import java.util.List;

public class Taxi {
    private int taxiNumber;
    private char Position;
    private int time;
    private int totalAmount;
    private List<Customer>customers=new ArrayList<>();
    public int getTaxiNumber() {
        return taxiNumber;
    }

    public void setTaxiNumber(int taxiNumber) {
        this.taxiNumber = taxiNumber;
    }

    public char getPosition() {
        return Position;
    }

    public void setPosition(char position) {
        Position = position;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
