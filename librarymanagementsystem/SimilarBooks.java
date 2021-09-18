package librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class SimilarBooks {
    private int similarBookId;
    private List<Integer> bookIds=new ArrayList<>();

    public int getSimilarBookId() {
        return similarBookId;
    }

    public void setSimilarBookId(int similarBookId) {
        this.similarBookId = similarBookId;
    }

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds( int bookId) {
       bookIds.add(bookId);
    }
}
