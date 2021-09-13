package carparking;

import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        ProgramDriver driver=new ProgramDriver();
        System.out.println("Enter number of Floors : ");
        int numOfFloor=scan.nextInt();
        System.out.println("Number of slots in each floor : ");
        int numOfSlots=scan.nextInt();
        driver.addSlots(numOfFloor,numOfSlots);
        driver.setReservedCars();
        while (true){
            System.out.println("Choose the number");
            System.out.println("\n1.FindSlot\n2.Return Car\n3.Available Slots\n4.Daily Report\n5.exit");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                System.out.println("Car Number : ");
                int carNumber=scan.nextInt();
                System.out.println("Is car reserved?");
                boolean reserved=scan.nextBoolean();
                scan.nextLine();
                System.out.println("Entry Time(09:20 AM) : ");
                String time=scan.nextLine();
                int entryTime=timeCalculation(time);
                Car car = getCar(carNumber, reserved, entryTime);
                String result=driver.findParkingSpace(car);
                System.out.println(result);
            }else if(decision==2){
                System.out.println("Car Number : ");
                int carNumber=scan.nextInt();
                scan.nextLine();
                System.out.println("Exit Time:");
                String time=scan.nextLine();
                int exitTime=timeCalculation(time);
                String result=driver.getFare(carNumber,exitTime);
                System.out.println(result);
            }else if(decision==3){
                System.out.println("Enter the current Time :");
                String time=scan.nextLine();
                int currentTime=timeCalculation(time);
                int result=driver.getAvailableSpaces(currentTime);
                System.out.println(result);
            }else if(decision==4){
                String result=driver.getDailyReport();
                System.out.println(result);
            }else if(decision==5){
                break;
            }else {
                System.out.println("Invalid input");
            }
        }

    }

    private static Car getCar(int carNumber, boolean reserved, int entryTime) {
        Car car=new Car();
        car.setCarNumber(carNumber);
        car.setEntryTime(entryTime);
        car.setReserved(reserved);
        return car;
    }

    private static int timeCalculation(String time) {
        String[]session= time.split(" ");
        String[]hourSplit=session[0].split(":");
        int minutes=Integer.parseInt(hourSplit[0])*60;
        minutes+=Integer.parseInt(hourSplit[1]);
        if(session[1].startsWith("P")||session[1].startsWith("p")){
            minutes+=720;
        }
        return minutes;
    }
}
