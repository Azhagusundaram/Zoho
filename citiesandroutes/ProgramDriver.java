package citiesandroutes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProgramDriver {
    CitiesRouteManagement cache=new CitiesRouteManagement();
    public Set<String> getCities(){
        Set<String>cities=cache.getAllCities().keySet();
        return cities;
    }
    public boolean findCity(String cityName){
        return cache.getAllCities().containsKey(cityName);
    }
    public String addRoute(String city1Name, String city2Name, int distance){
        Map<String,City>allCities=cache.getAllCities();
        City city1=allCities.get(city1Name);
        City city2=allCities.get(city2Name);
        if(city1!=null||city2!=null){
            if(city1==null){
                city1=new City();
                city1.setCityName(city1Name);
                cache.setAllCities(city1);
            }
            if(city2==null){
                city2=new City();
                city2.setCityName(city2Name);
                cache.setAllCities(city2);
            }
            if(city1.getRoutes().containsKey(city2Name)){
                return "Already route there";
            }
            city1.setRoute(city2Name,distance);
            city2.setRoute(city1Name,distance);
            return "Route added";
        }else {
            return "Both cities are not there";
        }
    }
    public String deleteRoute(String city1Name,String city2Name){
        Map<String,City>allCities=cache.getAllCities();
        City city1=allCities.get(city1Name);
        City city2=allCities.get(city2Name);
        if(city1!=null&&city2!=null){
            Map<String,Integer>city1Routes=city1.getRoutes();
            Map<String,Integer>city2Routes=city2.getRoutes();
             boolean city1Route=city1Routes.containsKey(city2Name);
             if(city1Route){
                 int numOfCity1Routes=city1Routes.size();
                 int numOfCity2Routes=city2Routes.size();
                 if(numOfCity1Routes>1&&numOfCity2Routes>1){
                     city1Routes.remove(city2Name);
                     city2Routes.remove(city1Name);
                     return "Route Deleted";
                 }
                 return "One city is isolated so rote not deleted";
             }
             return "There is no route between these cities";
        }
        return "ciy name is wrong";
    }
    public List<String> busyCity(){
        Map<String,City>allCities= cache.getAllCities();
        Set<String>cityNames=allCities.keySet();
        List<String> busyCities = new ArrayList<>();
        int max=0;
        for(String cityName:cityNames){
            City city=allCities.get(cityName);
            int numOfRoutes=city.getRoutes().size();
            if(max<numOfRoutes){
                max=numOfRoutes;
                busyCities=new ArrayList<>();
            }else if(max==numOfRoutes){
                busyCities.add(cityName);
            }
        }
        return busyCities;
    }
    public void printRoutes(String city1Name,String city2Name){

    }
}
