package citiesandroutes;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ProgramDriver driver=new ProgramDriver();
        driver.initialSetUp();
        while(true){
            System.out.println("1.ListCities\n2.Find city\n3.Shortest Route\n4.Print routes\n5.Add route\n6.Delete route\n7.Busy city\n8.exit");
            System.out.println("Enter your choice");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                Set<String> result=driver.getCities();
                System.out.println(result.toString());
            }else if(decision==2){
                System.out.println("Entre city Name :");
                String cityName=scan.nextLine();
                boolean result=driver.findCity(cityName);
                System.out.println(result);
            }else if(decision==3){
                System.out.println("Enter city 1 Name :");
                String city1Name=scan.nextLine();
                System.out.println("Enter city 2 Name :");
                String city2Name=scan.nextLine();
                Map<List<String>,Integer> routes=driver.findShortestRoutes(city1Name,city2Name);
                System.out.println(routes);
            }else if(decision==4){
                System.out.println("Enter city 1 Name :");
                String city1Name=scan.nextLine();
                System.out.println("Enter city 2 Name :");
                String city2Name=scan.nextLine();
                Map<List<String>,Integer> routes=driver.findAllRoutes(city1Name,city2Name);
                System.out.println(routes);
            }else if(decision==5){
                System.out.println("Enter city 1 Name :");
                String city1Name=scan.nextLine();
                System.out.println("Enter city 2 Name :");
                String city2Name=scan.nextLine();
                System.out.println("distance :");
                int distance=scan.nextInt();
                scan.nextLine();
                String result=driver.addRoute(city1Name,city2Name,distance);
                System.out.println(result);
            }else if(decision==6){
                System.out.println("Enter city 1 Name :");
                String city1Name=scan.nextLine();
                System.out.println("Enter city 2 Name :");
                String city2Name=scan.nextLine();
                String result=driver.deleteRoute(city1Name,city2Name);
                System.out.println(result);
            }else if(decision==7){
                List<String> result=driver.getBusyCity();
                System.out.println(result);
            }else if(decision==8){
                break;
            }else {
                System.out.println("Invalid Input");
            }
        }
    }
}
