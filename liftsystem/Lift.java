package liftsystem;

public class Lift {
    private int liftNumber;
    private int position;

    public int getLiftNumber() {
        return liftNumber;
    }

    public void setLiftNumber(int liftNumber) {
        this.liftNumber = liftNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public String toString(){
        return "Lift "+liftNumber;
    }
}
