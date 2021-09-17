package citiesandroutes;

import java.util.HashMap;
import java.util.Map;

public class CityRoutesManagement {
    private Map<String,City> allCities=new HashMap<>();

    public void setAllCities(City city) {
        String cityName= city.getCityName();
        allCities.put(cityName,city);
    }

    public Map<String, City> getAllCities() {
        return allCities;
    }
}