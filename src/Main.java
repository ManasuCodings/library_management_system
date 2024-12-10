import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("\n Library management system");
            System.out.println("1. Add Book");
            System.out.println("2. Add user");
            System.out.println("3. Display All Books");
            System.out.println("4. Display Available Books");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Search Book by Author");
            System.out.println("7. Search Book by Genre");
            System.out.println("8. Issue Book");
            System.out.println("9. Return Book");
            System.out.println("10. Display Library Cards");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 ->{
                    System.out.print("Enter Book Details\n");

                    System.out.println("title: ");
                    String title = scanner.nextLine();
                    System.out.println("Author: ");
                    String author = scanner.nextLine();
                    System.out.println("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.println("Genre: ");
                    String genre = scanner.nextLine();
                    System.out.println("Cost: ");
                    double cost = scanner.nextDouble();
                    scanner.nextLine();
                    library.addBook(new Book(title, author, isbn, genre, true, cost));

                    System.out.println("Book added successfully!");
                }
                case 2->{
                    System.out.println("Enter user Details: ");
                    System.out.println("user name");
                    String userName = scanner.nextLine();
                    System.out.println("User Id: ");
                    String userId = scanner.nextLine();
                    library.addUser(new User(userName,userId));
                    System.out.println("user added successfully");
                }
                case 3->{
                    try {
                        library.displayAllBooks();
                    } catch (NoBooksAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4->{

                    try {
                        library.displayAvailableBooks();
                    }
                    catch (NoBooksAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5->{
                    System.out.println("Enter title to search\n");
                    String title = scanner.next();

                    try {
                        library.searchByTitle(title);
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6->{
                    System.out.println("Enter author to search");
                    String author = scanner.nextLine();
                    try {
                        library.searchByAuthor(author);
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 7->{
                    System.out.println("Enter genre to search");
                    String genre = scanner.nextLine();

                    try {
                        library.searchByGenre(genre);
                    } catch (BookNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 8 ->{
                    System.out.println("Enter ISBN of the book to issue: ");
                    String isbn = scanner.next();
                    System.out.println("enter user id: ");
                    String userId = scanner.next();

                    try {
                        library.issueBook(isbn,userId);
                        System.out.println("book issued successfully");
                    }catch (BookUnavailableException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 9->{
                    System.out.println("Enter ISBN of the book to return");
                    String isbn = scanner.nextLine();
                    System.out.println("enter user ID");
                    String userID = scanner.nextLine();

                    try {
                        library.returnBook(isbn,userID);
                        System.out.println("Book returned successfully!");
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 10->{

                    try {
                        library.issuedToUser();
                    } catch (NoUsersAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 11-> {
                    System.out.println("Exiting the Library Management System...");
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }

        }while (choice !=11);

        scanner.close();
    }
}