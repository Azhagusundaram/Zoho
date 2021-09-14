package flightticketbooking;

import java.util.ArrayList;
import java.util.List;

public class Flight {
    private int flightNumber;
    private List<Integer> businessTickets=new ArrayList<>();
    private List<Integer>EconomyTickets=new ArrayList<>();
    private double surgePrice;
    private int mealPrice;
    private double totalAmount;
    private double economyTicketPrice;
    private double businessTicketPrice;

    public double getEconomyTicketPrice() {
        economyTicketPrice+=economyTicketPrice*surgePrice;
        return economyTicketPrice;
    }

    public void setEconomyTicketPrice(double economyTicketPrice) {
        this.economyTicketPrice = economyTicketPrice;
    }

    public double getBusinessTicketPrice() {
        businessTicketPrice+=businessTicketPrice*surgePrice;
        return businessTicketPrice;
    }

    public void setBusinessTicketPrice(double businessTicketPrice) {
        this.businessTicketPrice = businessTicketPrice;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public List<Integer> getBusinessTickets() {
        return businessTickets;
    }

    public void setBusinessTickets(List<Integer> businessTickets) {
        this.businessTickets = businessTickets;
    }

    public List<Integer> getEconomyTickets() {
        return EconomyTickets;
    }

    public void setEconomyTickets(List<Integer> economyTickets) {
        EconomyTickets = economyTickets;
    }

    public double getSurgePrice() {
        return surgePrice;
    }

    public void setSurgePrice(double surgePrice) {
        this.surgePrice = surgePrice;
    }

    public int getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(int mealPrice) {
        this.mealPrice = mealPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount+= totalAmount;
    }
}
