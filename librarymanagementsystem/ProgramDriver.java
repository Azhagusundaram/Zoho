package librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProgramDriver {
    private int memberId=0;
    LibraryManagement cache=new LibraryManagement();
    public void initialSetUp(List<Book> books){
        for(Book book:books){
            cache.setAllBooks(book);
        }
    }
    public void initialSetUp1(List<SimilarBooks>similarBooks){
        for(SimilarBooks similarBook:similarBooks){
            List<Integer>bookIds=similarBook.getBookIds();
            int bookId=bookIds.get(0);
            Book book=cache.getAllBooks().get(bookId);
            cache.setSimilarBooks(similarBook);
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
        Map<String,List<Integer>>titleBasedBook= cache.getTitleBasedBooks();
        List<Integer>bookIds=titleBasedBook.get(title);
        if(bookIds==null){
            return null;
        }
        List<Book> books = getBooks(bookIds);
        return books;
//        Set<String> set = titleBasedBook.keySet().stream().filter((String s) -> s.startsWith(title)).collect(Collectors.toSet());
    }

    private List<Book> getBooks(List<Integer> bookIds) {
        List<Book>books=new ArrayList<>();
        for (int bookId: bookIds){
            Book book=cache.getAllBooks().get(bookId);
            books.add(book);
        }
        return books;
    }

    private List<Book> searchByAuthor(String author){
        System.out.println(cache.getAuthorBasedBooks());
        List<Integer>bookIds=cache.getAuthorBasedBooks().get(author);
        if(bookIds==null){
            return null;
        }
        List<Book> books = getBooks(bookIds);
        return books;
    }
    private List<Book> searchBySubject(String subject){
        List<Integer>bookIds=cache.getSubjectBasedBooks().get(subject);
        if(bookIds==null){
            return null;
        }
        List<Book> books = getBooks(bookIds);
        return books;
    }
    private List<Book> searchByPublication(String publication){
        List<Integer>bookIds=cache.getPublicationBasedBooks().get(publication);
        if(bookIds==null){
            return null;
        }
        List<Book> books = getBooks(bookIds);
        return books;
    }
    public List<Book> searchBooks(String name, String search){
        if(search.equalsIgnoreCase("Title")){
            List<Book>books=searchByTitle(name);
            return books;
        }else if(search.equalsIgnoreCase("Author")){
            List<Book>books=searchByAuthor(name);
            return books;
        }else if(search.equalsIgnoreCase("Subject")){
            List<Book>books=searchBySubject(name);
            return books;
        }else if(search.equalsIgnoreCase("Publication")){
            List<Book>books=searchByPublication(name);
            return books;
        }
        return null;
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
        int similarBookId =book.getSimilarBookId();
        Member member=cache.getMembers().get(memberId);
        cache.setReservedBooks(similarBookId,memberId);
        member.setReservedBooks(similarBookId);
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
            int similarBookId=book.getSimilarBookId();
            getReservedBook(similarBookId);
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
    public boolean checkSimilarBookId(int similarBookId){
        return cache.getSimilarBooks().containsKey(similarBookId);
    }
    public Book getAvailableBook(int similarBookId){
        SimilarBooks similarBooks=cache.getSimilarBooks().get(similarBookId);
        List<Integer>bookIds=similarBooks.getBookIds();
        for(int bookId:bookIds){
            Book book=cache.getAllBooks().get(bookId);
            String status=book.getStatus();
            if(status.startsWith("A")){
                return book;
            }
        }
        int bookId=bookIds.size()-1;
        Book book=cache.getAllBooks().get(bookId);
        return book;
    }
    private void getReservedBook(int similarBookId) {
        Map<Integer, List<Integer>> reservedBooks=cache.getReservedBooks();
        SimilarBooks similarBook=cache.getSimilarBooks().get(similarBookId);
        int bookId=similarBook.getBookIds().get(0);
        Book book=cache.getAllBooks().get(bookId);
        List<Integer> memberIds =reservedBooks.get(similarBookId);
        if (memberIds != null&&!memberIds.isEmpty()) {
            for(int memberId:memberIds){
                Member member=cache.getMembers().get(memberId);
                member.setNotifications(book+" is available");
                member.removeReservedBook(similarBookId);
                memberIds.remove((Integer) memberId);
            }
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
