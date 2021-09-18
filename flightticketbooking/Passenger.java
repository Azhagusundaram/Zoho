package flightticketbooking;

public class Passenger {
    private String name;
    private String address;
    private int seatNumber;
    private String ticketType;
    private String meal;
    private double amount;

    public void setName(String name) {
        this.name = name;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }


    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    @Override
    public String toString(){
        return name+" "+address+" "+ticketType+" "+seatNumber+" "+amount;
    }
}
