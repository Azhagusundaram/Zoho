package eventmanagement;

import java.util.ArrayList;
import java.util.List;

public class TrackManagement {
    List<Event>events=new ArrayList<>();
    List<Track>filledTrack=new ArrayList<>();
    List<Track>unFilledTrack=new ArrayList<>();
    public List<Track> calculateTime(List<String> lists){
        for(String str:lists){
            int duration=calculate(str);
            Event event=new Event();
            event.setEventName(str);
            event.setDuration(duration);
            events.add(event);
        }
        setEvents();
        filledTrack.addAll(unFilledTrack);
        return filledTrack;
    }
    public void printEvents(List<Track>filledTrack){
        int trackNumber=1;
        for (Track track:filledTrack){
            System.out.println("Track "+trackNumber);
            List<Event>session1=track.getSession1();
            System.out.println("Session 1");
            for(Event event:session1){
                System.out.println(event.getStartTime()+" "+event.getEventName());
            }
            System.out.println("Lunch");
            List<Event>session2=track.getSession2();
            System.out.println("Session 2");
            for(Event event:session2){
                System.out.println(event.getStartTime()+" "+event.getEventName());
            }
            trackNumber++;
        }
    }
    private int calculate(String str){
        String[] array=str.split(" ");
        String time=array[array.length-1];
        char[] chars=time.toCharArray();
        int n=0;
        for(char c:chars){
            if(c=='l'){
                n=5;
                break;
            }
            if(c=='m'){
                break;
            }
            n=n*10+(c-48);
        }
        return n;
    }
    private void setEvents(){
        unFilledTrack.add(new Track());
        for(Event event:events){
            addEvent(event);
        }
    }
    private void addEvent(Event event){
        for(int i=0;i<=unFilledTrack.size();i++){
            Track track=unFilledTrack.get(i);
            int duration=event.getDuration();
            int session1Time=track.getSession1time();
            int session2Time=track.getSession2time();
            if(duration+session1Time<=720){
                String startTime=startTime(session1Time);
                event.setStartTime(startTime);
                track.setSession1time(duration+session1Time);
                track.setSession1(event);
                break;
            }else if(duration+session2Time<=1020){
                String startTime=startTime(session2Time);
                event.setStartTime(startTime);
                track.setSession2time(duration+session2Time);
                track.setSession2(event);
                break;
            }else {
                if(session1Time==720&&session2Time==1020){
                    filledTrack.add(track);
                    unFilledTrack.remove(track);
                }
                Track track1=unFilledTrack.get(unFilledTrack.size()-1);
                if(track1.equals(track)){
                    unFilledTrack.add(new Track());
                }
            }
        }
    }

    private String startTime(int startTime){
        int hour=startTime/60;
        int minute=startTime%60;
        String minutes=minute+"";
        if(minute<10){
            minutes="0"+minutes;
        }
        if(hour>12){
            hour=hour%12;
            return hour+":"+minutes+"PM";
        }
        if(hour==12){
            return hour+":"+minutes+"PM";
        }
        return hour+":"+minutes+"AM";
    }
}
