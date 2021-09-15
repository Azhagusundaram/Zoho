package taxibooking;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ProgramDriver driver=new ProgramDriver();
        System.out.println("Number of Taxis : ");
        int numOfTaxis=scan.nextInt();
        driver.initialSetUp(numOfTaxis);
        while(true){
            System.out.println("Choose the Decision : ");
            System.out.println("1.TaxiBooking\n2.All Details\n3.Exit");
            int decision=scan.nextInt();
            if(decision==1){
                System.out.println("Customer Id:");
                int customerId=scan.nextInt();
                scan.nextLine();
                System.out.println("Starting place");
                char pickUpPlace=scan.nextLine().charAt(0);
                System.out.println("Destination Place");
                char dropPlace=scan.nextLine().charAt(0);
                System.out.println("Pick Up Time ");
                int pickUpTime=scan.nextInt();
                Customer customer = getCustomer(customerId,pickUpPlace, dropPlace, pickUpTime);
                String result=driver.bookTaxi(customer);
                System.out.println(result);
            }else if(decision==2){
                Map<Taxi, List<Customer>>result= driver.getAllDetails();
                System.out.println(result);
            }else if(decision==3){
                break;
            }
            else {
                System.out.println("Invalid Input");
            }
        }
    }

    private static Customer getCustomer(int customerId,char pickUpPlace, char dropPlace, int pickUpTime) {
        Customer customer=new Customer();
        customer.setCustomerId(customerId);
        customer.setPickUpPlace(pickUpPlace);
        customer.setDropPlace(dropPlace);
        customer.setPickupTime(pickUpTime);
        return customer;
    }
}
