package EventManagement;

import java.util.ArrayList;
import java.util.List;

public class Track {
    List<Event>session1=new ArrayList<>();
    List<Event>session2=new ArrayList<>();
    int session1time=540;
    int session2time=780;

    public List<Event> getSession1() {
        return session1;
    }

    public void setSession1(Event event) {
        this.session1.add(event);
    }

    public List<Event> getSession2() {
        return session2;
    }

    public void setSession2(Event event) {
        this.session2.add(event);
    }

    public int getSession1time() {
        return session1time;
    }

    public void setSession1time(int session1time) {
        this.session1time = session1time;
    }

    public int getSession2time() {
        return session2time;
    }

    public void setSession2time(int session2time) {
        this.session2time = session2time;
    }
    @Override
    public String toString(){
        return "\nSession 1 \n"+session1.toString()+" \nSession 2 "+session2.toString();
    }
}
