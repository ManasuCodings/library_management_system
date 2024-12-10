import java.time.LocalDate;
import java.util.*;

public class Library {
    private List<Book> books=new LinkedList<Book>();
    private Map<String, User> users= new HashMap<String, User>();
    private Map<Book, LocalDate> borrowedDates =new HashMap<Book, LocalDate>();

    public void addBook(Book book){
        books.add(book);
    }
    public void addUser(User user){
        users.put(user.getUserId(),user);

    }

    public void displayAllBooks() throws NoBooksAvailableException{
        if(books.isEmpty()){
            throw new NoBooksAvailableException("no books available in the library");
        }
        books.forEach(System.out::println);
    }

    public void displayAvailableBooks() throws NoBooksAvailableException{

        long count = books.stream().filter(Book::isAvailable).count();
        if (count ==0){
            throw new NoBooksAvailableException("No available books in the library");
        }
        System.out.println("Available books in library: ");

        books.stream().filter(Book::isAvailable).forEach(System.out::println);


    }
    public void searchByTitle(String title) throws BookNotFoundException{
        long count = books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).count();
        if (count ==0){
            throw new BookNotFoundException("no book found with title: "+title);
        }
        System.out.print("search results");
        books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).forEach(System.out::println);
    }
    public void searchByAuthor(String author)throws BookNotFoundException{
        long count = books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).count();
        if(count ==0){
            throw  new BookNotFoundException("no book found with author: "+author);
        }
        System.out.println("search result: ");
        books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).forEach(System.out::println);
    }

    public void searchByGenre(String genre) throws BookNotFoundException {
        long count = books.stream().filter(book -> book.getGenre().equalsIgnoreCase(genre)).count();
        if (count == 0) {
            throw new BookNotFoundException("No book found with the genre: " + genre);
        }
        System.out.println("search result: ");
        books.stream().filter(book -> book.getGenre().equalsIgnoreCase(genre)).forEach(System.out::println);

    }

    public void issueBook(String isbn, String userId) throws BookUnavailableException {
        Optional<Book> bookOptional = books.stream().filter(book -> book.getIsbn().equals(isbn) && book.isAvailable()).findFirst();

        if(bookOptional.isPresent() ){
            Book book = bookOptional.get();
            User user = users.get(userId);

            if (user!= null){
                book.setAvailable(false);
                user.addBorrowedBook(book);
                borrowedDates.put(book, LocalDate.now());
            }
            else{
                throw  new BookUnavailableException("User with ID: " + userId + " not found.");
            }
        }
        else{
            throw new BookUnavailableException("Book with ISBN: " + isbn + " is unavailable.");
        }

    }

    public  void returnBook(String isbn, String userId) throws BookNotFoundException{
        User user = users.get(userId);
        if(user != null){
            Optional<Book> bookOptional = user.getBorrowedBooks().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
            
            if(bookOptional.isPresent()){
                Book book = bookOptional.get();
                book.setAvailable(true);
                user.removeBorrowedBook(book);

                LocalDate borrowedDate = borrowedDates.get(book);
                long overdueDays = LocalDate.now().toEpochDay() - borrowedDate.toEpochDay() - 90;

                if(overdueDays>0){
                    double fine =overdueDays *0.02* book.getCost();
                    user.addFine(fine);
                    System.out.println("overdue fine: " + fine);
                }
                borrowedDates.remove(book);

            }
            else{
                throw new BookNotFoundException("Book with ISBN: " + isbn + " not found in borrowed books.");
            }
        }else{
            throw new BookNotFoundException("User with ID: " + userId + " not found.");
        }
    }

    public void issuedToUser() throws NoUsersAvailableException {
        if (users.isEmpty()) {
            throw new NoUsersAvailableException("No users available in the library.");
        }
        System.out.println("Library Cards:");
        users.values().forEach(System.out::println);
    }

}
