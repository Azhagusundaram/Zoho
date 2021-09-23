package carshowroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        CarShowroomDriver driver=new CarShowroomDriver();
        Scanner scan=new Scanner(System.in);
        System.out.println("Number of Cities");
        int branchId=1;
        int cityId=1;
        int carId=1;
        int customerId=1;
        int numOfCities=scan.nextInt();
        scan.nextLine();
        List<City>allCities=new ArrayList<>();
        for(int i=0;i<numOfCities;i++){
            System.out.println((i+1)+"City Name");
            String cityName=scan.nextLine();
            System.out.println("Number of branches");
            int numOfBranches=scan.nextInt();
            scan.nextLine();
            City city = getCity(cityId, cityName);
            for(int j=0;j<numOfBranches;j++){
                System.out.println((j+1)+" Branch Name");
                String branchName=scan.nextLine();
                Branch branch = getBranch(branchId, branchName);
                city.setBranch(branch);
                branchId++;
            }
            allCities.add(city);
            cityId++;

        }
        driver.initialSetUp(allCities);
        while (true){
            System.out.println("1.Admin Operation\n2.Customer Operation\n3.Sales\n4.Exit");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                while (true){
                    System.out.println("1.Dispatch Car\n2.Show Cities\n3.Find city to add Stock\n4.car first sold city\n5.car last sold city\n6.Go back");
                    int decision1=scan.nextInt();
                    scan.nextLine();
                    if(decision1==1){
                        carId = dispatchCar(driver, scan, carId);
                    }else if(decision1==2){
                        Map<String,Integer>cities= driver.showCities();
                        System.out.println(cities);
                    }else if(decision1==3){
                        List<String>cityNames=driver.findCity();
                        System.out.println(cityNames);
                    }else if(decision1==4){
                        String cityName=driver.getFirstSoldCar();
                        System.out.println(cityName);
                    }else if(decision1==5){
                        String cityName=driver.getLastSoldCar();
                        System.out.println(cityName);
                    }else if(decision1==6){
                        break;
                    }else {
                        System.out.println("Invalid Input");
                    }
                }

            }else if(decision==2){
                System.out.println("Enter the Name :");
                String customerName=scan.nextLine();
                System.out.println("Mobile Number");
                long mobile=scan.nextLong();

                System.out.println(driver.getCities());
                System.out.println("Enter the city Id");
                int cityId1=scan.nextInt();
                if(driver.checkCityId(cityId1)){
                    System.out.println(driver.getBranches(cityId1));
                    System.out.println("Enter Branch Id");
                    int branchId1=scan.nextInt();
                    if(driver.checkBranchId(cityId1,branchId1)){
                        List<Car>cars=driver.getCars(cityId1,branchId1);
                        if(cars.isEmpty()){
                            System.out.println("No car Available");
                            continue;
                        }
                        System.out.println(cars);
                        System.out.println("Enter the car Id");
                        int carId1=scan.nextInt();
                        if(driver.checkCarId(cityId1,branchId1,carId1)){
                            setCustomer(customerId, customerName, mobile);
                            SoldCar soldCar = getSoldCar(customerId, cityId1, branchId1, carId1);
                            String result=driver.soldCar(soldCar);
                            System.out.println(result);
                            customerId++;
                        }else {
                            System.out.println("Invalid car Id");
                        }
                    }else {
                        System.out.println("Invalid branch Id");
                    }
                }else {
                    System.out.println("Invalid cityId");
                }

            }else if(decision==3){
                List<Integer>sales=new ArrayList<>();
                for (int i=0;i<7;i++){
                    System.out.println("Day "+(i+1));
                    sales.add(scan.nextInt());
                }
                driver.setSales(sales);
                List<String>profit=driver.findProfit();
                System.out.println(profit);
            }
            else if(decision==4){
                break;
            }else {
                System.out.println("Invalid input");
            }
        }

    }

    private static SoldCar getSoldCar(int customerId, int cityId1, int branchId1, int carId1) {
        SoldCar soldCar=new SoldCar();
        soldCar.setCarId(carId1);
        soldCar.setBranchId(branchId1);
        soldCar.setCityId(cityId1);
        soldCar.setCustomerId(customerId);
        return soldCar;
    }

    private static void setCustomer(int customerId, String customerName, long mobile) {
        Customer customer=new Customer();
        customer.setCustomerId(customerId);
        customer.setMobileNumber(mobile);
        customer.setName(customerName);

    }

    private static int dispatchCar(CarShowroomDriver driver, Scanner scan, int carId) {
        System.out.println(driver.getCities());
        System.out.println("enter city Id");
        int cityId1= scan.nextInt();
        if(driver.checkCityId(cityId1)){
            System.out.println(driver.getBranches(cityId1));
            System.out.println("Enter branch Id");
            int branchId1= scan.nextInt();
            if(driver.checkBranchId(cityId1,branchId1)){
                List<Car> cars= driver.getCars();
                int decision2;
                if(cars.isEmpty()){
                    System.out.println("No Car Updated");
                    decision2=1;
                }else {
                    System.out.println(cars);
                    System.out.println("1.New Model\n2.old Model");
                    decision2= scan.nextInt();
                }
                if(decision2==1){
                    scan.nextLine();
                    System.out.println("Car Name :");
                    String carName= scan.nextLine();
                    System.out.println("Quantity");
                    int quantity= scan.nextInt();
                    setCar(driver, carId, carName);
                    driver.dispatchCars(cityId1,branchId1, carId,quantity);
                    carId++;
                }else if(decision2==2){
                    System.out.println("Enter Car Id");
                    int carId1= scan.nextInt();
                    if(driver.checkCarId(carId1)){
                        System.out.println("Enter the quantity");
                        int quantity= scan.nextInt();
                        driver.dispatchCars(cityId1,branchId1,carId1,quantity);
                    }else {
                        System.out.println("Invalid car id");
                    }
                }

            }else {
                System.out.println("Invalid branchId");
            }
        }else {
            System.out.println("invalid cityId");
        }
        return carId;
    }

    private static void setCar(CarShowroomDriver driver, int carId, String carName) {
        Car car=new Car();
        car.setCarId(carId);
        car.setCarName(carName);
        driver.setCars(car);
    }

    private static Branch getBranch(int branchId, String branchName) {
        Branch branch=new Branch();
        branch.setBranchName(branchName);
        branch.setBranchId(branchId);
        return branch;
    }

    private static City getCity(int cityId, String cityName) {
        City city=new City();
        city.setCityId(cityId);
        city.setCityName(cityName);
        return city;
    }
}
