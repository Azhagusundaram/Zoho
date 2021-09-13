package carparking;

public class SlotSpace {
    private int floorNumber;
    private boolean Booked;
    private boolean reserved;
    private int slotNumber;

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public boolean isBooked() {
        return Booked;
    }

    public void setBooked(boolean booked) {
        Booked = booked;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
}
