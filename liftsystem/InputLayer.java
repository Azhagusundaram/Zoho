package liftsystem;

import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        LiftDriver driver=new LiftDriver();
        driver.initialSetUp();
        while (true){
            System.out.println("1.Travel in lift\n2.exit");
            int decision=scan.nextInt();
            if(decision==1){
                System.out.println("Enter Start");
                int start=scan.nextInt();
                System.out.println("End ");
                int end=scan.nextInt();
                Lift lift=driver.allocateLift(start,end);
                if(lift==null){
                    System.out.println("No lift is available");
                }else {
                    System.out.println(lift+" is allocated");
                }
            }else if(decision==2){
                break;
            }else {
                System.out.println("Invalid input");
            }

        }


    }
}
