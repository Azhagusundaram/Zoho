package citiesandroutes;

import java.util.*;

public class CityRoutesDriver {
    CityRoutesManagement cache=new CityRoutesManagement();
    Map<List<String>,Integer>allRoutes=new HashMap<>();
    public void initialSetUp(){
        City city=new City();
        city.setCityName("A");
        cache.setAllCities(city);
        addRoute("A","B",20);
        addRoute("A","C",45);
        addRoute("A","D",25);
        addRoute("B","G",35);
        addRoute("B","F",90);
        addRoute("C","E",10);
        addRoute("D","G",30);
        addRoute("E","H",35);
        addRoute("F","G",30);
        addRoute("G","H",40);
    }
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
    public List<String> getBusyCity(){
        Map<String,City>allCities= cache.getAllCities();
        Set<String>cityNames=allCities.keySet();
        List<String> busyCities = new ArrayList<>();
        int max=0;
        for(String cityName:cityNames){
            City city=allCities.get(cityName);
            int numOfRoutes=city.getRoutes().size();
            if(max<numOfRoutes){
                max=numOfRoutes;
                busyCities.clear();
            }else if(max==numOfRoutes){
                busyCities.add(cityName);
            }
        }
        return busyCities;
    }
    public Map<List<String>, Integer> findAllRoutes(String city1Name, String city2Name){
        Map<String,City>allCities=cache.getAllCities();
        City city1=allCities.get(city1Name);
        City city2=allCities.get(city2Name);
        if(city1!=null&&city2!=null){
            Set<String>cityNames=city1.getRoutes().keySet();
            Set<String>routes=new LinkedHashSet<>();
            routes.add(city1Name);
            findRoutes(cityNames,city2Name,routes);
        }else{
            List<String> list =new ArrayList<>();
            list.add("ciy name is wrong");
            allRoutes.put(list,0);

        }
        return allRoutes;
    }

    public void findRoutes(Set<String>cityNames, String destination, Set<String>routes){

       Set<String>routeNames=new HashSet<>();
       routeNames.addAll(cityNames);
       routeNames.removeAll(routes);
        for(String cityName:routeNames){
            if(!destination.equals(cityName)){
                City city=cache.getAllCities().get(cityName);
                Set<String>cityNames1=city.getRoutes().keySet();
                routes.add(cityName);
                findRoutes(cityNames1, destination,routes);
            }else {
                routes.add(destination);
                List<String>successRoutes=new ArrayList<>();
                successRoutes.addAll(routes);
                int distance=findDistance(successRoutes);
                allRoutes.put(successRoutes,distance);
                routes.remove(destination);
            }
            routes.remove(cityName);
        }
    }
    public int findDistance(List<String>route){
        int distance=0;
        for (int i=0;i<route.size()-1;i++){
            String cityName1=route.get(i);
            String cityName2=route.get(i+1);
            City city1=cache.getAllCities().get(cityName1);
            distance+=city1.getRoutes().get(cityName2);
        }
        return distance;
    }
    public Map<List<String>,Integer> findShortestRoutes(String cityName1, String cityName2){
        Map<List<String>,Integer>shortestRoutes=new HashMap();
        Map<List<String>,Integer>routes=findAllRoutes(cityName1,cityName2);
        if(routes!=null&&!routes.isEmpty()){
            Set<List<String>>distances=routes.keySet();
            int shortestDistance=0;
            for (List<String>route:distances){
                shortestDistance=routes.get(route);
                break;
            }
            for(List<String>route:distances){
                int distance=routes.get(route);
                if(shortestDistance>distance){
                    shortestDistance=distance;
                    shortestRoutes.clear();
                }
                if(shortestDistance==distance){
                    shortestRoutes.put(route,shortestDistance);
                }
            }

        }
        return shortestRoutes;
    }
}