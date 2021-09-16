package librarymanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManagement {
    private Map<Integer,Book> allBooks=new HashMap<>();
    private Map<String, List<Book>>titleBasedBooks=new HashMap<>();
    private Map<String, List<Book>>authorBasedBooks=new HashMap<>();
    private Map<String, List<Book>>subjectBasedBooks=new HashMap<>();
    private Map<String, List<Book>>publicationBasedBooks=new HashMap<>();
    private Map<Integer,Member>members=new HashMap<>();
    private Map<Integer,List<Integer>>reservedBooks=new HashMap<>();

    public void setMember(Member member){
        int memberId=member.getMemberId();
        members.put(memberId,member);
    }
    public void setReservedBooks(int bookId,int memberId){
        List<Integer>memberIds=reservedBooks.get(bookId);
        if(memberIds==null){
            memberIds=new ArrayList<>();
            reservedBooks.put(bookId,memberIds);
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
        List<Book>books=titleBasedBooks.get(title);
        if(books==null){
            books=new ArrayList<>();
            titleBasedBooks.put(title,books);
        }
       books.add(book);
    }

    public void setAuthorBasedBooks(Book book) {
        String author =book.getAuthor();
        List<Book>books=authorBasedBooks.get(author);
        if(books==null){
            books=new ArrayList<>();
            authorBasedBooks.put(author,books);
        }
        books.add(book);
    }

    public void setSubjectBasedBooks(Book book) {
        String subject =book.getSubject();
        List<Book>books=subjectBasedBooks.get(subject);
        if(books==null){
            books=new ArrayList<>();
            subjectBasedBooks.put(subject,books);
        }
        books.add(book);
    }

    public Map<Integer, Book> getAllBooks() {
        return allBooks;
    }

    public Map<String, List<Book>> getTitleBasedBooks() {
        return titleBasedBooks;
    }

    public Map<String, List<Book>> getAuthorBasedBooks() {
        return authorBasedBooks;
    }

    public Map<String, List<Book>> getSubjectBasedBooks() {
        return subjectBasedBooks;
    }

    public Map<String, List<Book>> getPublicationBasedBooks() {
        return publicationBasedBooks;
    }

    public void setPublicationBasedBooks(Book book) {
        String publication =book.getPublication();
        List<Book>books=publicationBasedBooks.get(publication);
        if(books==null){
            books=new ArrayList<>();
            publicationBasedBooks.put(publication,books);
        }
        books.add(book);
    }
}
