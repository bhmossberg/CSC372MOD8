# CSC372MOD8 – Portfolio Project: Library Management System

A console library-management system. You can add books, borrow and return them, search by title, and list the books that are available. The program validates its input and handles exceptions throughout.

## Overview

This is **CSU Global CSC372 (Programming II), Module 8**. The code is the course portfolio project (commits "portfolio v1.0" and "Portfolio final v1.1"). It is an Eclipse Java project with all classes in the `portfolioProject` package. The module's readings cover stacks, queues and exception handling; they are listed at the end.

## Design

```
LibraryApp (console menu, input validation)
    └── Inventory (business logic)
            ├── ArrayList<Book> mainInventory   (available)
            └── ArrayList<Book> borrowedBooks   (checked out)
                    └── Book (data class)
```

### `Book.java`
A data class with `id` (unique), `title`, `author`, `isbn` (stored as a `String`) and `numberOfPages`. It has default and parameterized constructors, getters and setters, and `printBookInfo()`.

### `Inventory.java`
Keeps **two lists**, so borrowing a book moves the `Book` object from one list to the other.

| Method | Behaviour |
|---|---|
| `addBook(id, title, author, isbn, pages)` | Rejects a duplicate ID in either list. Otherwise it adds the book and prints how many copies of that title are available. Several copies of the same title can exist under different IDs. |
| `borrowBook(id)` | Moves the book from `mainInventory` to `borrowedBooks`. If the ID isn't available, the error message says whether it is already borrowed or doesn't exist. |
| `returnBook(id)` | Moves the book back to `mainInventory` and reports the copies now available. |
| `searchByTitle(text)` | Case-insensitive **partial** title match over the available books; returns a `List<Book>` |
| `getQuantity(title)` | Counts the available copies of a title (case-insensitive exact match) |
| `printAll()` / `printBorrowedBooks()` | Lists each collection |
| `idExists`, `getMainInventoryCount`, `getBorrowedCount` | Helpers that `LibraryApp` uses to check things before calling the methods above |
| `findBookById(list, id)` (private) | Linear search by ID |

### `LibraryApp.java` (`main`)
A `while (run)` loop shows a two-column menu and dispatches the choice with a `switch`:

`1 Add Book · 2 Borrow Book · 3 Return Book · 4 Search By Title · 5 Print All Books · 6 Exit`

- All input is read with `nextLine()` and parsed with `Integer.parseInt` inside `try/catch (NumberFormatException)`. This avoids the `nextInt()`/`nextLine()` newline problem and prevents bad input from crashing the program.
- The add flow re-prompts until the ID is numeric and the page count is greater than 0, and it stops if the ID already exists.
- Borrow and return check for empty collections first. Return lists the borrowed books before asking for an ID.
- A commented-out block in `main` holds sample books you can use for testing.

## Build and run

JDK 8 or newer:

```bash
mkdir -p bin
javac -d bin src/portfolioProject/*.java
java -cp bin portfolioProject.LibraryApp
```

Sample session (verified, abridged):
```
============================================
  WELCOME TO THE LIBRARY MANAGEMENT SYSTEM
============================================

=================== LIBRARY MENU ===================
1. Add Book         3. Return Book         5. Print All Books
2. Borrow Book      4. Search By Title     6. Exit
Enter your choice (1-6): 1
--- Add New Book ---
Enter book ID: 101
...
The Right Stuff added to the library. 1 Available
...
Enter your choice (1-6): 2
Enter the unique ID of the book to borrow: 101
The Right Stuff successfully borrowed. Remaining copies: 1
Enter your choice (1-6): abc
Invalid input. Please enter a number between 1 and 6.
```

## Concepts demonstrated
- Separating layers: data class, inventory/business logic, and console UI
- `ArrayList` collections and moving objects between them
- Exception handling (`NumberFormatException`) and input-validation loops
- Menu-driven program control with `switch`
- Linear search and case-insensitive string matching
- Javadoc documentation

## Module readings

Required
- [ ] Chapter 13 and Chapter 15, Sections 4, 5, and 6 in Programming in Java with ZyLabs.
- [ ] Oracle. (2024). Queue implementations.
- [ ] GeeksforGeeks. (2025, August 5). Java exception handling.
- [ ] GeeksforGeeks. (2015, July 23). Types of exception in Java with examples.

Recommended
- [ ] GeeksforGeeks. (2025, June 26). Queue interface in Java.
- [ ] GeeksforGeeks. (2025, July 23). Stack class in Java.
