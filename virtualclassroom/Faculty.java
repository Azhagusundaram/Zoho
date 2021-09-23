package virtualclassroom;

public class Faculty extends User {
    private String subject;
    private String accessType;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getAccessType() {
        return accessType;
    }

    @Override
    public void setAccessType(String accessType) {
        this.accessType=accessType;
    }
    @Override
    public String toString() {
        return "\nName:" + getName() + " Id:" + id;
    }
}
