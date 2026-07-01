package portfolioProject;
/**
 * Manages the library's collection of books.
 * Maintains two separate lists:
 *    Main inventory (available books)
 *    Borrowed books (currently checked out)
 * @author Benjamin Mossberg
 */
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Book> mainInventory;
    private ArrayList<Book> borrowedBooks;
    
    /**
     * Constructs a new Inventory with empty ArrayLists.
     */
    public Inventory() {
        mainInventory = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
    }
    
    /**
     * Adds a new book to the main inventory.
     * @param id            the unique ID of the book
     * @param title         the title of the book
     * @param author        the author of the book
     * @param isbn          the ISBN of the book
     * @param numberOfPages the number of pages
     * @return true if the book was added successfully, false if the ID already exists
     */
    
    public boolean addBook(int id, String title, String author, String isbn, int numberOfPages) {
        if (findBookById(mainInventory, id) != null || findBookById(borrowedBooks, id) != null) {
            System.out.println("Error: A book with ID " + id + " already exists.");
            return false;
        }
        mainInventory.add(new Book(id, title, author, isbn, numberOfPages));
        System.out.println(title + " added to the library.");
        return true;
    }

    /**
     * Borrows a book by moving it from the main inventory to the borrowed list.
     * @param bookId the ID of the book to borrow
     */
    public void borrowBook(int bookId) {
        Book book = findBookById(mainInventory, bookId);
        if (book == null) {
            if (findBookById(borrowedBooks, bookId) != null) {
                System.out.println("Error: Book with ID " + bookId + " is already borrowed.");
            } else {
                System.out.println("Error: Book with ID " + bookId + " not found.");
            }
            return;
        }
        mainInventory.remove(book);
        borrowedBooks.add(book);
        System.out.println(book.getTitle() + " successfully borrowed.");
    }

    /**
     * Returns a book from the borrowed list back to the main inventory.
     * @param bookId the ID of the book to return
     */
    public void returnBook(int bookId) {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Error: No books are currently borrowed.");
            return;
        }
        Book book = findBookById(borrowedBooks, bookId);
        if (book != null) {
            borrowedBooks.remove(book);
            mainInventory.add(book);
            System.out.println("Book successfully returned.");
        } else {
            System.out.println("Error: Book with ID " + bookId + " is not in the borrowed list.");
        }
    }

    /**
     * Prints all books currently available in the main inventory.
     */
    public void printAll() {
        if (mainInventory.isEmpty()) {
            System.out.println("The library inventory is currently empty.");
            return;
        }
        System.out.println("\n=== AVAILABLE BOOKS IN LIBRARY ===");
        for (Book book : mainInventory) {
            book.printBookInfo();
        }
    }

    /**
     * Searches for books in the main inventory by title (case-insensitive partial match).
     * @param searchTitle the title or partial title to search for
     * @return a list of matching books
     */
    public List<Book> searchByTitle(String searchTitle) {
        List<Book> results = new ArrayList<>();
        String lower = searchTitle.toLowerCase().trim();
        for (Book book : mainInventory) {
            if (book.getTitle().toLowerCase().contains(lower)) {
                results.add(book);
            }
        }
        return results;
    }
    
    /**
     * Returns the number of books currently available in the main inventory.
     * @return the count of available books
     */
    public int getMainInventoryCount() {
        return mainInventory.size();
    }
    
    /**
     * Helper method to find a book by its ID in a given list.
     * @param list the list to search in
     * @param id   the book ID to find
     * @return the Book object if found, otherwise null
     */
    private Book findBookById(ArrayList<Book> list, int id) {
        for (Book b : list) if (b.getId() == id) return b;
        return null;
    }

    /**
     * Prints all books currently in the borrowed list.
     */
    public void printBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books are currently borrowed.");
            return;
        }
        System.out.println("\n=== CURRENTLY BORROWED BOOKS ===");
        for (Book book : borrowedBooks) book.printBookInfo();
    }
}