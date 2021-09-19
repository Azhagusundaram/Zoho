package carshowroom;

public class Car {
    private int carId;
    private String carName;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
    public String toString(){
        return "\nCar Id :"+carId+"\tCar Name : "+carName;
    }
}
