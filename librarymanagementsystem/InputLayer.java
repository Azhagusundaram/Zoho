package librarymanagementsystem;

import java.util.*;

public class InputLayer {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ProgramDriver driver=new ProgramDriver();
        System.out.println("Number of books");
        int numOfBooks=scan.nextInt();
        scan.nextLine();
        List<Book>books=new ArrayList<>(numOfBooks);
        for (int i=0;i<numOfBooks;i++){
            System.out.println("Title");
            String title=scan.nextLine();
            System.out.println("Author");
            String author=scan.nextLine();
            System.out.println("Subject");
            String subject=scan.nextLine();
            System.out.println("Publication");
            String publication=scan.nextLine();
            Book book = getBook(title, author, subject, publication);
            books.add(book);
        }
        driver.initialSetUp(books);
        while (true){
            System.out.println("1.Add member Account\n2.Existing member");
            int decision=scan.nextInt();
            scan.nextLine();
            if(decision==1){
                System.out.println("Enter name :");
                String name=scan.nextLine();
                Member member=new Member();
                member.setName(name);
                int result=driver.addMember(member);
                System.out.println("Your member id is "+result);
            }else if(decision==2){
                System.out.println("Enter member id");
                int memberId=scan.nextInt();
                if(driver.checkMemberId(memberId)){
                    while (true) {
                        System.out.println("1.Search the book\n2.ReturnBook\n3.view Notification\n4.Exit");
                        int decision1 = scan.nextInt();
                        scan.nextLine();
                        if (decision1 == 1) {
                            searchBook(scan, driver, memberId);
                        } else if (decision1 == 2) {
                            System.out.println("enter the book id");
                            int bookId = scan.nextInt();
                            if (driver.checkBookId(bookId)) {
                                String result = driver.returnBook(bookId, memberId);
                                System.out.println(result);
                            } else {
                                System.out.println("Wrong bookId");
                            }
                        } else if (decision1 == 3) {
                            List<String> result = driver.getNotifications(memberId);
                            System.out.println(result);
                        } else if (decision1 == 4) {
                            break;
                        }
                    }
                }
                else {
                    System.out.println("Invalid member Id");
                }
            }else if(decision==3){
                break;
            }else {
                System.out.println("Invalid input");
            }
        }
    }

    private static Book getBook(String title, String author, String subject, String publication) {
        Book book=new Book();
        book.setAuthor(author);
        book.setPublication(publication);
        book.setSubject(subject);
        book.setTitle(title);
        book.setStatus("Available");
        return book;
    }

    private static void searchBook(Scanner scan, ProgramDriver driver, int memberId) {
        while (true){
            System.out.println("1.Search by title\n2.search by author\n3.search by publications\n4.search by subject\n5.Exit");
            int decision2 = scan.nextInt();
            scan.nextLine();
            if(decision2 ==1){
                System.out.println("Enter the title :");
                String title= scan.nextLine();
                Map<Integer, Book>books= driver.searchBooks(title,"Title");
                checkOutBook(scan, driver, memberId, books);
            }else if(decision2 ==2){
                System.out.println("Enter the Author :");
                String title= scan.nextLine();
                Map<Integer, Book>books= driver.searchBooks(title,"Author");
                checkOutBook(scan, driver, memberId, books);
            }else if(decision2 ==3){
                System.out.println("Enter the Publication :");
                String title= scan.nextLine();
                Map<Integer, Book>books= driver.searchBooks(title,"Publication");
                checkOutBook(scan, driver, memberId, books);
            }else if(decision2 ==4){
                System.out.println("Enter the Subject :");
                String title= scan.nextLine();
                Map<Integer, Book>books= driver.searchBooks(title,"Subject");
                checkOutBook(scan, driver, memberId, books);
            }else if(decision2==5){
                break;
            }
        }
    }

    private static void checkOutBook(Scanner scan, ProgramDriver driver, int memberId, Map<Integer, Book> books) {
        Book book=printBooks(scan, books);
        if(book==null){
            System.out.println("No Books for your search");
            return;
        }
        System.out.println("Are you checkOut the book(yes=1/no=0)");
        int decision2= scan.nextInt();
        if(decision2==1){
            String result= driver.checkOutBook(book, memberId);
            System.out.println(result);
            if(result.startsWith("O")){
                System.out.println("Are you Reserved the book(yes=1/no=0)");
                int decision3= scan.nextInt();
                if(decision3==1){
                    result= driver.setReservedBook(book, memberId);
                    System.out.println(result);
                }
            }
        }
    }

    private static Book printBooks(Scanner scan, Map<Integer,Book>books){
        Set<Integer>serialNumbers=books.keySet();
        if(books.isEmpty()){
            return null;
        }
        for(int serialNumber:serialNumbers ){
            System.out.println(serialNumber+"."+books.get(serialNumber));
        }
        System.out.println("Enter the book Number");
        int decision=scan.nextInt();
        while (decision> serialNumbers.size()||decision<=0){
            System.out.println("Enter correct number:");
            decision=scan.nextInt();
        }
        scan.nextLine();
        Book book=books.get(decision);
        return book;
    }
}
