package taxibooking;

public class Customer {
    private int customerId;
    private int pickupTime;
    private char pickUpPlace;
    private char dropPlace;
    private int amount;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(int pickupTime) {
        this.pickupTime = pickupTime;
    }

    public char getPickUpPlace() {
        return pickUpPlace;
    }

    public void setPickUpPlace(char pickUpPlace) {
        this.pickUpPlace = pickUpPlace;
    }

    public char getDropPlace() {
        return dropPlace;
    }

    public void setDropPlace(char dropPlace) {
        this.dropPlace = dropPlace;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
