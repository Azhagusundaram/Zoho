package librarymanagementsystem;

public class Book {
    private String title;
    private String author;
    private String subject;
    private String publication;
    private int bookId;
    private String status;

    public int getSimilarBookId() {
        return similarBookId;
    }

    public void setSimilarBookId(int similarBookId) {
        this.similarBookId = similarBookId;
    }

    private int similarBookId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    @Override
    public String toString(){
        return "Book Id :"+bookId+" Title :"+title+" Author :"+author+" Publication :"+publication+" Subject :"+subject+" Similar Book Id :"+similarBookId;
    }
}
//3
//z
//x
//c
//v
//a
//x
//d
//f
//z
//w
//d
//v