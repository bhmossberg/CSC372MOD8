package portfolioProject;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int numberOfPages;

    public Book() {}

    // Parameterized constructor
    public Book(int id, String title, String author, String isbn, int numberOfPages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
    }

    // Getters & Setters
    public int getId() { 
    	return id; 
    }
    public void setId(int id) { 
    	this.id = id; 
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
    public int getNumberOfPages() { 
    	return numberOfPages; 
    }
    public void setNumberOfPages(int numberOfPages) { 
    	this.numberOfPages = numberOfPages; 
    }

    public void printBookInfo() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Number of Pages: " + numberOfPages);
        System.out.println("------------------------------");
    }
}