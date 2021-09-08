package EventManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        TrackManagement track=new TrackManagement();
        List<String> lists=new ArrayList<>();
        int n=13;
        while(n>0){
            System.out.println("Enter the input :");
            String str=scan.nextLine();
            lists.add(str);
            n--;
        }
        List<Track>filledTrack=track.calculateTime(lists);
        track.printEvents(filledTrack);
    }
}
//Writing Fast tests against enterprise rails 60min
//overdoing in python 45min
//lua for the masses 30min
//ruby errors from mismatched gem versions 45min
//common ruby errors 45min
//Rails for python developers lightning
//communicating over distance 60min
//accounting driven development 45min
//woah 30min
//sitdown and write 30min
//pair programming vs noise 45min
//rails magic 60min
//ruby on rails 60min