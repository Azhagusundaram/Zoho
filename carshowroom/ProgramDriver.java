package carshowroom;

import java.util.*;

public class ProgramDriver {
//    private int cityId=1;
//    private int branchId=1;
//    private int carId=1;
    ShowroomManagement cache=new ShowroomManagement();
    public void initialSetUp(List<City> cities){
        for(City city:cities){
            cache.setCity(city);
        }
    }
    public void dispatchCars(int cityId,int branchId,int  carId,int quantity){
        City city=cache.getCities().get(cityId);
        Branch branch=city.getBranches().get(branchId);
        branch.setAvailableCars(carId,quantity);
    }

    public void setCars(Car car){
        cache.setCars(car);
    }
    public List<City> getCities(){
        Collection<City>cityCollection=cache.getCities().values();
        List<City>cities=new ArrayList<>(cityCollection);
        return cities;
    }
    public List<Branch> getBranches(int cityId){
        City city=cache.getCities().get(cityId);
        Collection<Branch>branchCollection=city.getBranches().values();
        List<Branch>branches=new ArrayList<>(branchCollection);
        return branches;
    }
    public List<Car> getCars(int cityId, int branchId){
        City city=cache.getCities().get(cityId);
        Branch branch=city.getBranches().get(branchId);
        if(branch==null){
            return null;
        }
        Set<Integer> carIds=branch.getAvailableCars().keySet();
        List<Car>cars=new ArrayList<>();
        for(int carId:carIds){
            Car car=cache.getCars().get(carId);
            cars.add(car);
        }
        return cars;
    }
    public List<Car> getCars(){
        Collection <Car> carCollection=cache.getCars().values();
        List<Car>cars=new ArrayList<>(carCollection);
        return cars;
    }
    public boolean checkCityId(int cityId){
        return cache.getCities().containsKey(cityId);
    }
    public boolean checkBranchId(int cityId,int branchId){
        City city=cache.getCities().get(cityId);
        return city.getBranches().containsKey(branchId);
    }
    public boolean checkCarId(int cityId,int branchId,int carId){
        City city=cache.getCities().get(cityId);
        Branch branch=city.getBranches().get(branchId);
        return branch.getAvailableCars().containsKey(carId);
    }
    public boolean checkCarId(int carId){
        return cache.getCars().containsKey(carId);
    }
    public String soldCar(SoldCar soldCar){
        int cityId=soldCar.getCityId();
        int branchId=soldCar.getBranchId();
        int carId=soldCar.getBranchId();
        City city=cache.getCities().get(cityId);
        Branch branch=city.getBranches().get(branchId);
        branch.removeAvailableCar(carId);
        boolean carSold=branch.setSoldCar(carId);
        cache.setSoldCar(soldCar);
        if(carSold){
            return "Car Sold";
        }
        return "Car Not Sold";
    }
    public void setCustomerDetails(Customer customer){
        cache.setCustomerDetails(customer);
    }
    public String getFirstSoldCar(){
        List<SoldCar>soldCars=cache.getSoldCars();
        if(!soldCars.isEmpty()){
            SoldCar soldCar=soldCars.get(0);
            int cityId =soldCar.getCityId();
            City city=cache.getCities().get(cityId);
            return city.getCityName();
        }
        return "No car is sold";
    }
    public String getLastSoldCar(){
        List<SoldCar>soldCars=cache.getSoldCars();
        if(!soldCars.isEmpty()){
            int lastIndex=soldCars.size()-1;
            SoldCar soldCar=soldCars.get(lastIndex);
            int cityId =soldCar.getCityId();
            City city=cache.getCities().get(cityId);
            return city.getCityName();
        }
        return "No car is sold";
    }
    public List<String> findCity(){
        Map<Integer,City> cities=cache.getCities();
        Set<Integer>cityIds=cities.keySet();
        List<String>cityNames=new ArrayList<>();
        int min=10;
        for(int cityId:cityIds){
            int carNumbers=0;
            City city=cities.get(cityId);
            Map<Integer,Branch>branches=city.getBranches();
            Set<Integer>branchIds=branches.keySet();
            for(int branchId:branchIds){
                Branch branch=branches.get(branchId);
                Collection<Integer>numberOfCars=branch.getAvailableCars().values();
                for(int numberOfCar:numberOfCars){
                    carNumbers+=numberOfCar;
                }
            }
            min=carNumbers;
            break;
        }
        for(int cityId:cityIds){
            int carNumbers=0;
            City city=cities.get(cityId);
            Map<Integer,Branch>branches=city.getBranches();
            Set<Integer>branchIds=branches.keySet();
            for(int branchId:branchIds){
                Branch branch=branches.get(branchId);
                Collection<Integer>numberOfCars=branch.getAvailableCars().values();
                for(int numberOfCar:numberOfCars){
                    carNumbers+=numberOfCar;
                }
            }
            if(carNumbers<min){
                min=carNumbers;
                cityNames.clear();
            }
            if(carNumbers==min){
                cityNames.add(city.getCityName());
            }
        }
        return cityNames;

    }
    public Map<String, Integer> showCities(){
        Map<Integer,City> cities=cache.getCities();
        Set<Integer>cityIds=cities.keySet();
        Map<String,Integer>cityNames=new HashMap<>();

        for(int cityId:cityIds){
            int carNumbers=0;
            City city=cities.get(cityId);
            Map<Integer,Branch>branches=city.getBranches();
            Set<Integer>branchIds=branches.keySet();
            for(int branchId:branchIds){
                Branch branch=branches.get(branchId);
                Collection<Integer>numberOfCars=branch.setSoldCars().values();
                for(int numberOfCar:numberOfCars){
                    carNumbers+=numberOfCar;
                }
            }
            cityNames.put(city.getCityName(),carNumbers);
        }
        Set<Map.Entry<String, Integer>> cityMap=cityNames.entrySet();
        List<Map.Entry<String, Integer>>cityList=new LinkedList<>(cityMap);
        cityList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        Map<String,Integer>sortedCityNames=new HashMap<>();
        for(Map.Entry<String,Integer>map:cityList){
            sortedCityNames.put(map.getKey(),map.getValue());
        }
        return sortedCityNames;
    }
    public void setSales(List<Integer>sales){
        cache.setSales(sales);
    }
    public List<String> findProfit(){
        List<Integer>sales=cache.getSales();
        int min=sales.get(0);
        int max=sales.get(0);
        List<String>profitList=new ArrayList<>();
        for(int sale:sales){
            if(sale>=max){
                max=sale;
            }else if(max!=min){
                profitList.add("("+min+","+max+")");
                min=sale;
                max=sale;
            }
        }
        if(max!=min){
            profitList.add("("+min+","+max+")");
        }
        if(profitList.isEmpty()){
            profitList.add("No Profit");
        }
        return profitList;
    }
    public boolean checkCustomerId(int customerId){
        return cache.getCustomerDetails().containsKey(customerId);
    }
}
