package portfolioProject;

import java.util.List;
import java.util.Scanner;

/**
 * The main application class for library management system.
 * This class provides a console-based menu interface that allows users to:
 *    Add new books to the library
 *    Borrow books from the main inventory
 *    Return borrowed books
 *    Search for books by title
 *    View all available books
 * The class uses Inventory.java to manage book data and includes
 * exception handling for invalid user input.
 *
 * @author Benjamin Mossberg
 */
public class LibraryApp {

    public static void main(String[] args) {
    	Inventory inventory = new Inventory();
    	/* Test inventory
    	inventory.addBook(101, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 180);
    	inventory.addBook(102, "To Kill a Mockingbird", "Harper Lee", "9780061120084", 376);
    	inventory.addBook(103, "Moby-Dick", "Herman Melville", "9780142437247", 720);
    	inventory.addBook(104, "The Old Man and the Sea", "Ernest Hemingway", "9780684801223", 128);
    	inventory.addBook(105, "Topgun Days", "Dave Baranek", "9781628736601", 320);
    	inventory.addBook(106, "Viper Pilot", "Dan Hampton", "9780062130358", 368);
    	inventory.addBook(107, "The Right Stuff", "Tom Wolfe", "9780312427566", 352);
    	inventory.addBook(108, "The Right Stuff", "Tom Wolfe", "9780312427566", 352);
    	*/
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        System.out.println("============================================");
        System.out.println("  WELCOME TO THE LIBRARY MANAGEMENT SYSTEM");
        System.out.println("============================================");
        while (run) {
            printMenu();
            int choice = 0;
            try {
                System.out.print("Enter your choice (1-6): ");
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }
            switch (choice) {
                case 1: // Add Book
                    addBookFlow(inventory, scanner);
                    break;
                case 2: // Borrow Book
                    borrowBookFlow(inventory, scanner);
                    break;
                case 3: // Return Book
                    returnBookFlow(inventory, scanner);
                    break;
                case 4: // Search By Title
                    searchByTitleFlow(inventory, scanner);
                    break;
                case 5: // Print All Books
                    inventory.printAll();
                    break;
                case 6: // Exit
                    System.out.println("Exiting the program. Goodbye!");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a number between 1 and 6.");
            }
            System.out.println();
        }
        scanner.close();
    }

    // === MENU ===
    private static void printMenu() {
        System.out.println("\n=================== LIBRARY MENU ===================");
        System.out.println("1. Add Book         3. Return Book         5. Print All Books");
        System.out.println("2. Borrow Book      4. Search By Title     6. Exit");
    }

    // === ADD BOOK ===
    private static void addBookFlow(Inventory inventory, Scanner scanner) {
        System.out.println("\n--- Add New Book ---");
        int id = 0;
        boolean validId = false;
        while (!validId) {
            try {
                System.out.print("Enter book ID: ");
                id = Integer.parseInt(scanner.nextLine().trim());

                if (inventory.idExists(id)) {
                    System.out.println("Error: A book with ID " + id + " already exists.");
                    return;
                } else {
                    validId = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Please enter a numeric value.");
            }
        }
        System.out.print("Enter title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        int pages = 0;
        boolean validPages = false;
        while (!validPages) {
            try {
                System.out.print("Enter number of pages: ");
                pages = Integer.parseInt(scanner.nextLine().trim());
                if (pages > 0) {
                    validPages = true;
                } else {
                    System.out.println("Number of pages must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for pages.");
            }
        }
        inventory.addBook(id, title, author, isbn, pages);
    }

    // === BORROW BOOK ===
    private static void borrowBookFlow(Inventory inventory, Scanner scanner) {
        System.out.println("\n--- Borrow Book ---");

        if (inventory.getMainInventoryCount() == 0) {
            System.out.println("No books available to borrow.");
            return;
        }
        try {
            System.out.print("Enter the unique ID of the book to borrow: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            inventory.borrowBook(bookId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a numeric book ID.");
        }
    }

    // === RETURN BOOK ===
    private static void returnBookFlow(Inventory inventory, Scanner scanner) {
        System.out.println("\n--- Return Book ---");
        if (inventory.getBorrowedCount() == 0) {
            System.out.println("No books are currently borrowed.");
            return;
        }
        inventory.printBorrowedBooks();
        try {
            System.out.print("Enter the ID of the book to return: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            inventory.returnBook(bookId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a numeric book ID.");
        }
    }

    // === SEARCH BY TITLE ===
    private static void searchByTitleFlow(Inventory inventory, Scanner scanner) {
        System.out.println("\n--- Search by Title ---");
        System.out.print("Enter full or partial book title: ");
        String searchTerm = scanner.nextLine().trim();
        if (searchTerm.isEmpty()) {
            System.out.println("Search term cannot be empty.");
            return;
        }
        List<Book> results = inventory.searchByTitle(searchTerm);
        if (results.isEmpty()) {
            System.out.println("No matching book found.");
        } else {
            System.out.println("\nMatching books found:");
            for (Book book : results) {
                book.printBookInfo();
            }
        }
    }
}