package railwayticketbooking;

public class Passenger {
    private String name;
    private String address;
    private String gender;
    private String berthPreference;
    private String berth;
    private int age;
    private int seatNumber;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBerthPreference() {
        return berthPreference;
    }

    public void setBerthPreference(String berthPreference) {
        this.berthPreference = berthPreference;
    }

    public void setBerth(String berth) {
        this.berth = berth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    @Override
    public String toString(){
        return name+" "+address+" "+berth+" "+age+" "+gender+" "+seatNumber;
    }
}
