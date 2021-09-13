package tollpaymentprocessing;

import java.util.*;

public class InputLayer {
    static List<String>stops;
    static Scanner scan=new Scanner(System.in);
    public static void main(String[] args) {
        ProgramDriver driver=new ProgramDriver();
        stops=new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H"));
        driver.setStops(stops);
        Map<String,List<Integer>>chargingScheme=new HashMap<>();
        chargingScheme.put("B",new ArrayList<>(Arrays.asList(30,40,50,60)));
        chargingScheme.put("D",new ArrayList<>(Arrays.asList(35,45,55,65)));
        chargingScheme.put("E",new ArrayList<>(Arrays.asList(40,50,60,70)));
        chargingScheme.put("G",new ArrayList<>(Arrays.asList(45,55,65,75)));
        driver.setToll(chargingScheme);
        System.out.println("Total Stops \n1.A 2.B 3.C 4.D 5.E 6.F 7.G 8.H");
        while (true){

            System.out.println("1.Vehicle Journey \n2.All Tolls Details \n3.All Vehicles Details \n4.Exit");
            System.out.println("Enter the decision :");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                System.out.println("Vehicle Number :");
                String vehicleNumber=scan.nextLine();
                System.out.println("Starting point");
                String start=scan.nextLine();
                start=checkCity(start);
                System.out.println("Destination");
                String dest=scan.nextLine();
                dest=checkCity(dest);
                System.out.println("Vip (Yes/No)");
                String vip=scan.nextLine();
                System.out.println("Vehicle Type(Car/Bus/Truck/Lorry)");
                String vehicleType=scan.nextLine();
                Vehicle vehicle=getVehicle(vehicleNumber, start, dest, vip, vehicleType);
                int output=driver.setJourney(vehicle);
                if(output==1){
                    driver.getVehicleAmount(vehicleNumber);
                }else {
                    System.out.println("Starting point and Destination point are same");
                }
            }
            else if (decision==2){
                StringBuilder output =driver.getAllTolls();
                System.out.println(output);
            }
            else if(decision==3){
                StringBuilder output= driver.getAllVehicles();
                System.out.println(output);
            }else if(decision==4){
                break;
            }else {
                System.out.println("Invalid input");
            }
        }
    }

    private static Vehicle getVehicle(String vehicleNumber, String start, String dest, String vip, String vehicleType) {
        Vehicle vehicle=new Vehicle();
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setVehicleType(vehicleType);
        vehicle.setDest(dest);
        vehicle.setStart(start);
        vehicle.setVip(vip);
        return vehicle;
    }
    private static String checkCity(String cityName){
        while (!stops.contains(cityName)){
            System.out.println("Enter correct cityName");
            cityName=scan.nextLine();
        }
        return cityName;
    }
}
