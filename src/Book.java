public class Book {

    private String title;
    private String author;
    private String isbn;
    private String genre;
    private boolean available;
    private double cost;

    public Book(String title, String author, String isbn,String genre, boolean available, double cost) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre= genre;
        this.available = available;
        this.cost = cost;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString(){
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn +
                ", Genre: " + genre + ", Available: " + (available ? "Yes" : "No") +
                ", Cost: " + cost;
    }

}
