package portfolioProject;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Book> mainInventory;
    private ArrayList<Book> borrowedBooks;
    public Inventory() {
        mainInventory = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
    }

    public boolean addBook(int id, String title, String author, String isbn, int numberOfPages) {
        // FIX ME: add logic for book not in inventory
    	mainInventory.add(new Book(id, title, author, isbn, numberOfPages));
        System.out.println("Book added to the library.");
        return true;
    }

    public void borrowBook(int bookId) {
        // FIX ME: add logic for book not found
        mainInventory.remove(book);
        borrowedBooks.add(book);
        System.out.println(book.getTitle() + " successfully borrowed.");
    }

    public void returnBook(int bookId) {
        // FIX ME: add logic for book not found
        borrowedBooks.remove(book);
        mainInventory.add(book);
        System.out.println("Book successfully returned.");
     }

    public void printAll() {
    	// FIX ME: empty library case
        System.out.println("\n=== AVAILABLE BOOKS IN LIBRARY ===");
        for (Book book : mainInventory) {
            book.printBookInfo();
        }
    }

    public List<Book> searchByTitle(String searchTitle) {
        List<Book> results = new ArrayList<>();
        //FIX ME: add search logic
        return results;
    }
    
    public int getMainInventoryCount() {
        return mainInventory.size();
    }
    
    public void printBorrowedBooks() {
        System.out.println("\n=== CURRENTLY BORROWED BOOKS ===");
        for (Book book : borrowedBooks) book.printBookInfo();
    }
}