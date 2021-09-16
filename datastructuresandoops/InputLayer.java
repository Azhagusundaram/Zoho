package datastructuresandoops;

import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        Functionalities function=new Functionalities();
        while (true){
            System.out.println("1.Set\n2.Get\n3.Unset\n4.Update\n5.Count number of variables\n6.Begin\n7.Rollback\n8.Commit\n9.Exit");
            int decision=scan.nextInt();
            scan.nextLine();
            String result;
            if(decision==1){
                System.out.println("Variable :");
                char variable=scan.nextLine().charAt(0);
                System.out.println("Value :");
                int value=scan.nextInt();
                result=function.set(variable,value);
                System.out.println(result);
            }else if(decision==2){
                System.out.println("Variable :");
                char variable=scan.nextLine().charAt(0);
                 result=function.get(variable);
                System.out.println(result);
            }else if(decision==3){
                System.out.println("Variable :");
                char variable=scan.nextLine().charAt(0);
                result=function.unSet(variable);
                System.out.println(result);
            }else if(decision==4){
                System.out.println("Variable :");
                char variable=scan.nextLine().charAt(0);
                System.out.println("Value :");
                int value=scan.nextInt();
                result=function.update(variable,value);
                System.out.println(result);
            }else if(decision==5){
                System.out.println("Value :");
                int value=scan.nextInt();
                int output=function.count(value);
                System.out.println(output);
            }else if(decision==6){
                function.begin();
            }else if(decision==7){
                function.rollBack();
            }else if(decision==8){
                function.commit();
            }
        }

    }
}
