import java.util.LinkedList;

public class User {
    private String userName;
    private String  userId;
    private LinkedList<Book> borrowedBooks =new LinkedList<Book>();
    private double fine = 0.0;
    public User(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LinkedList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }
    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void setBorrowedBooks(LinkedList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public double getFine() {
        return fine;
    }

    public void addFine(double amount) {
        fine += amount;
    }

    @Override
    public String toString(){
        return  "User: " + userName + ", UserID: " + userId + ", Fine: " + fine +
                ", Borrowed Books: " + borrowedBooks;
    }

}
