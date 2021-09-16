package librarymanagementsystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramDriver {
    private int bookId=0;
    private int memberId=0;
    private long date;
    LibraryManagement cache=new LibraryManagement();
    public void initialSetUp(List<Book> books){
        for(Book book:books){
            bookId++;
            book.setBookId(bookId);
            cache.setAllBooks(book);
            cache.setAuthorBasedBooks(book);
            cache.setPublicationBasedBooks(book);
            cache.setSubjectBasedBooks(book);
            cache.setTitleBasedBooks(book);
        }
    }
    public int addMember(Member member){
        memberId++;
        member.setMemberId(memberId);
        cache.setMember(member);
        return memberId;
    }

    private List<Book> searchByTitle(String title){
        Map<String,List<Book>>titleBasedBook= cache.getTitleBasedBooks();
        List<Book>books=titleBasedBook.get(title);
        return books;
//        Set<String> set = titleBasedBook.keySet().stream().filter((String s) -> s.startsWith(title)).collect(Collectors.toSet());
    }
    private List<Book> searchByAuthor(String author){
        System.out.println(cache.getAuthorBasedBooks());
        List<Book>books=cache.getAuthorBasedBooks().get(author);
        return books;
    }
    private List<Book> searchBySubject(String subject){
        List<Book>books=cache.getSubjectBasedBooks().get(subject);
        return books;
    }
    private List<Book> searchByPublication(String publication){
        List<Book>books=cache.getPublicationBasedBooks().get(publication);
        return books;
    }
    public Map<Integer, Book> searchBooks(String name, String search){
        if(search.equalsIgnoreCase("Title")){
            List<Book>books=searchByTitle(name);
            System.out.println(search);
            return bookWithOrder(books);
        }else if(search.equalsIgnoreCase("Author")){
            System.out.println(search);
            List<Book>books=searchByAuthor(name);
            return bookWithOrder(books);
        }else if(search.equalsIgnoreCase("Subject")){
            System.out.println(search);
            List<Book>books=searchBySubject(name);
            return bookWithOrder(books);
        }else if(search.equalsIgnoreCase("Publication")){
            System.out.println(search);
            List<Book>books=searchByPublication(name);
            return bookWithOrder(books);
        }
        return null;
    }
    public Map<Integer, Book> bookWithOrder(List<Book>books){
        Map<Integer,Book>orderedBook=new HashMap<>();
        if(books!=null){
            int i=1;
            for(Book book:books){
                orderedBook.put(i,book);
                i++;
            }
        }
        return orderedBook;
    }
    public String checkOutBook(Book book, int memberId){
        int bookId=book.getBookId();
        String status=book.getStatus();
        if(status.equalsIgnoreCase("Checkout")){
            return "Out of Stock";
        }
        Member member=cache.getMembers().get(memberId);
        Map<Integer,CheckOutBook> checkOutBooks=member.getCheckOutBook();
        int numberOfBooks=checkOutBooks.size();
        if(numberOfBooks==5){
            return "You already have 5 books";
        }
        book.setStatus("Checkout");
        CheckOutBook checkOutBook = getCheckOutBook(book);
        checkOutBooks.put(bookId,checkOutBook);
        return "Book Successfully Checkout";
    }
    public String setReservedBook(Book book,int memberId){
        int bookId=book.getBookId();
        Member member=cache.getMembers().get(memberId);
        cache.setReservedBooks(bookId,memberId);
        member.setReservedBooks(book);
        return "Book reserved Successfully";
    }
    public String returnBook(int bookId,int memberId){
        if(checkCheckOutBooks(bookId,memberId)){
            Book book=cache.getAllBooks().get(bookId);
            book.setStatus("Available");
            Member member=cache.getMembers().get(memberId);
            CheckOutBook checkOutBook= member.getCheckOutBook().get(bookId);
            long checkOutDate=checkOutBook.getCheckOutDate();
            long returnDate=System.currentTimeMillis();
            int fineAmount=calculateFine(checkOutDate,returnDate);
            member.setTotalFineAmount(fineAmount);
            member.getCheckOutBook().remove(bookId);
            getReservedBook(bookId);
            return "Your fine Amount is "+fineAmount;
        }else {
            return "Invalid book id";
        }


    }
    public boolean checkCheckOutBooks(int bookId, int memberId){
        Member member=cache.getMembers().get(memberId);
        return member.getCheckOutBook().containsKey(bookId);

    }
    public boolean checkBookId(int bookId){
        return cache.getAllBooks().containsKey(bookId);
    }
    private void getReservedBook(int bookId) {
        Map<Integer, List<Integer>> reservedBooks=cache.getReservedBooks();
        Book book=cache.getAllBooks().get(bookId);
        List<Integer> memberIds =reservedBooks.get(bookId);
        if (memberIds != null&&!memberIds.isEmpty()) {
            int memberId=memberIds.get(0);
            Member member=cache.getMembers().get(memberId);
            member.setNotifications(book+" is available");
            member.removeReservedBook(book);
            memberIds.remove((Integer) memberId);
        }

    }
    public List<String> getNotifications(int memberId){
        Member member=cache.getMembers().get(memberId);
        return member.getNotifications();
    }
    public int calculateFine(long checkOutDate, long returnDate){
        long diff=returnDate-checkOutDate;
        if(864000000<diff){
            long extraMilliSeconds =diff-864000000;
            long date= extraMilliSeconds /86400000;
            int fine=(int)(date+1)*10;
            return fine;
        }
        return 0;

    }
    private CheckOutBook getCheckOutBook(Book book) {
        CheckOutBook checkOutBook=new CheckOutBook();
        checkOutBook.setBook(book);
        long checkOutDate=System.currentTimeMillis();
        checkOutBook.setCheckOutDate(checkOutDate);
        return checkOutBook;
    }

    public boolean checkMemberId(int memberId){
        return cache.getMembers().containsKey(memberId);
    }
}
