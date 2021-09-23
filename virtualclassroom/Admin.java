package virtualclassroom;

public class Admin extends User {
    String accessType;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getAccessType() {
        return accessType;
    }

    @Override
    public void setAccessType(String accessType) {
        this.accessType=accessType;
    }
}
