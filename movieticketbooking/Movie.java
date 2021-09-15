package movieticketbooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Movie {
    private Map<String, Time> timings =new HashMap<>();
    private String movieName;
    private int deluxePrice;
    private int superDeluxePrice;

    public int getDeluxePrice() {
        return deluxePrice;
    }

    public void setDeluxePrice(int deluxePrice) {
        this.deluxePrice = deluxePrice;
    }

    public int getSuperDeluxePrice() {
        return superDeluxePrice;
    }

    public void setSuperDeluxePrice(int superDeluxePrice) {
        this.superDeluxePrice = superDeluxePrice;
    }

    public Map<String, Time> getTimings() {
        return timings;
    }

    public void setTimings (Time time) {
        String showTime=time.getTime();
        timings.put(showTime,time);

    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    @Override
    public String toString(){
        return "\nMovie : "+movieName+" "+timings.toString();
    }
}
