package librarymanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManagement {
    private Map<Integer,Book> allBooks=new HashMap<>();
    private Map<Integer,SimilarBooks>similarBooks=new HashMap<>();
    private Map<String, List<Integer>>titleBasedBooks=new HashMap<>();
    private Map<String, List<Integer>>authorBasedBooks=new HashMap<>();
    private Map<String, List<Integer>>subjectBasedBooks=new HashMap<>();
    private Map<String, List<Integer>>publicationBasedBooks=new HashMap<>();
    private Map<Integer,Member>members=new HashMap<>();
    private Map<Integer,List<Integer>>reservedBooks=new HashMap<>();

    public Map<Integer, SimilarBooks> getSimilarBooks() {
        return similarBooks;
    }

    public void setSimilarBooks(SimilarBooks similarBook) {
        int similarBookId=similarBook.getSimilarBookId();
        similarBooks.put(similarBookId,similarBook);
    }
    public void setMember(Member member){
        int memberId=member.getMemberId();
        members.put(memberId,member);
    }
    public void setReservedBooks(int similarBookId, int memberId){
        List<Integer>memberIds=reservedBooks.get(similarBookId);
        if(memberIds==null){
            memberIds=new ArrayList<>();
            reservedBooks.put(similarBookId,memberIds);
        }
        memberIds.add(memberId);
    }
    public Map<Integer, List<Integer>> getReservedBooks() {
        return reservedBooks;
    }

    public Map<Integer, Member> getMembers() {
        return members;
    }

    public void setAllBooks(Book book) {
        int bookId=book.getBookId();
        allBooks.put(bookId,book);
    }

    public void setTitleBasedBooks(Book book) {
        String title=book.getTitle();
        addBook(book, title, titleBasedBooks);
    }

    public void setAuthorBasedBooks(Book book) {
        String author =book.getAuthor();
        addBook(book, author, authorBasedBooks);
    }

    public void setSubjectBasedBooks(Book book) {
        String subject =book.getSubject();
        addBook(book, subject, subjectBasedBooks);
    }

    public Map<Integer, Book> getAllBooks() {
        return allBooks;
    }

    public Map<String, List<Integer>> getTitleBasedBooks() {
        return titleBasedBooks;
    }

    public Map<String, List<Integer>> getAuthorBasedBooks() {
        return authorBasedBooks;
    }

    public Map<String, List<Integer>> getSubjectBasedBooks() {
        return subjectBasedBooks;
    }

    public Map<String, List<Integer>> getPublicationBasedBooks() {
        return publicationBasedBooks;
    }

    public void setPublicationBasedBooks(Book book) {
        String publication =book.getPublication();
        addBook(book, publication, publicationBasedBooks);
    }

    private void addBook(Book book, String publication, Map<String, List<Integer>> booksList) {
        int bookId = book.getBookId();
        List<Integer> books = booksList.get(publication);
        if (books == null) {
            books = new ArrayList<>();
            booksList.put(publication, books);
        }
        books.add(bookId);
    }
}
