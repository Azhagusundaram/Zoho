package eventmanagement;

public class Event {
    private String eventName;
    private int duration;
    private String startTime;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    @Override
    public String toString(){
        return "\n"+startTime+" "+eventName;
    }
}
