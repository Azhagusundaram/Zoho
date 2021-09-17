
package citiesandroutes;

import java.util.HashMap;
import java.util.Map;

public class City {
    private String cityName;
    private Map<String,Integer> routes=new HashMap<>();

    public Map<String, Integer> getRoutes() {
        return routes;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
    public void setRoute(String cityName,int distance){
        routes.put(cityName,distance);
    }
}
