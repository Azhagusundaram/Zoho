package virtualclassroom;

public class Student extends Login {
    private int standard;
    private String accessType;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    @Override
    public String getAccessType() {
        return null;
    }

@Override
    public String toString(){
        return "\nName:"+getName()+" Id:"+id;
}
}
