package liftsystem;

import java.util.*;

public class LiftDriver {
    LiftManagement cache=new LiftManagement();
    private List<Integer>lowerLifts=new ArrayList<>(Arrays.asList(1,2));
    private List<Integer>upperLifts=new ArrayList<>(Arrays.asList(3,4));
    private List<Integer>allLifts=new ArrayList<>(Arrays.asList(5));
    private int totalLifts=5;
    public void initialSetUp(){
        for(int i=0;i<5;i++){
            Lift lift=new Lift();
            lift.setLiftNumber(i+1);
            lift.setPosition(0);
            cache.setLifts(lift);
        }
    }
    public Lift allocateLift(int start,int end){
        Lift lift=null;
        if((start>=0&&start<=5)&&(end>=0&&end<=5)){
            lift = getLift(start, end, lowerLifts,allLifts,5);
        }else if((start>=6&&start<=10)&&(end>=6&&end<=10)){
            lift = getLift(start, end, upperLifts,allLifts,5);
        }else if(((start>=6&&start<=10)||start==0)&&((end>=6&&end<=10)||end==0)){
            lift=getLift(start,end,upperLifts,null,10);
        }
        if(lift==null){
            lift=getLift(start,end,allLifts,null,10);
        }
        return lift;
    }

    private Lift getLift(int start, int end, List<Integer> lowerLifts,List<Integer> allLifts,int count) {
        Lift lift1=null;
        List<Integer> availableLifts = new ArrayList<>(lowerLifts);
        if(allLifts!=null){
            availableLifts.addAll(allLifts);
        }

        List<Integer> positions = new ArrayList<>();
        List<Integer> underMaintenance = new ArrayList<>();
        for (int liftNumber : availableLifts) {
            Lift lift = cache.getLifts().get(liftNumber);
            int position = lift.getPosition();
            if (position != -1) {
                positions.add(start - position);
            } else {
                underMaintenance.add(liftNumber);
            }
        }
        availableLifts.removeAll(underMaintenance);
        if(!availableLifts.isEmpty()){
            lift1 = findLift(end, availableLifts, positions, start < end,count);
        }

        return lift1;
    }

    private Lift findLift(int end, List<Integer> availableLifts, List<Integer> positions, boolean flag,int count) {
        int i=0;
        int loop=1;
        while(i<=count){
            int frequency=Collections.frequency(positions,i);
            if(frequency>0){
                int index= positions.indexOf(i);
                int liftNumber= availableLifts.get(index);
                Lift lift=cache.getLifts().get(liftNumber);
                lift.setPosition(end);
                return lift;
            }
            if(flag){
                i=i+loop;
            }else {
                i=i-loop;
            }
            loop++;
            flag=!flag;
        }
        return null;
    }
}
