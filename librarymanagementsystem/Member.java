package librarymanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Member {
    private int memberId;
    private String name;
    private Map<Integer,CheckOutBook> checkOutBook=new HashMap<>();
    private List<Integer>reservedBook=new ArrayList<>();
    private int totalFineAmount;
    private List<String>notifications=new ArrayList<>();

    public void setReservedBooks(int similarBookId){
        reservedBook.add(similarBookId)  ;
    }
    public void removeReservedBook(int similarBookId){
        reservedBook.remove(similarBookId);
    }
    public void setNotifications(String notification) {
        notifications.add(notification);
    }
    public List<String> getNotifications(){
        return notifications;
    }


    public Map<Integer, CheckOutBook> getCheckOutBook() {
        return checkOutBook;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalFineAmount() {
        return totalFineAmount;
    }

    public void setTotalFineAmount(int totalFineAmount) {
        this.totalFineAmount+= totalFineAmount;
    }
}
