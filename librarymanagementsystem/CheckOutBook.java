package librarymanagementsystem;

public class CheckOutBook {

    private long checkOutDate;
    private Book book;

    public void setCheckOutDate(long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public long getCheckOutDate() {
        return checkOutDate;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
