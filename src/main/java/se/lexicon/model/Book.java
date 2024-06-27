package se.lexicon.model;

import java.util.UUID;
import java.util.Scanner;

public class Book {
    private final String id;
    private String title;
    private String author;
    private boolean available;
    private Person borrower;

    // Constructor
    public Book(String title, String author) {
        this.id = generateId();
        validateAndAssignStringInput(title, "title");
        validateAndAssignStringInput(author, "author");
        this.available = true;
    }

    // Method to generate a unique ID for each book
    private String generateId() {
        return UUID.randomUUID().toString().substring(0, 12);
    }

    // Method to validate and assign input strings to the appropriate fields
    private void validateAndAssignStringInput(String stringInput, String paramName) {
        if (stringInput == null || stringInput.trim().isEmpty()) {
            throw new IllegalArgumentException(paramName + " must not be null or empty");
        }

        switch (paramName) {
            case "title":
                this.title = stringInput;
                break;
            case "author":
                this.author = stringInput;
                break;
        }
    }

    // Method to get the title of the book
    public String getTitle() {
        return title;
    }

    // Method to check if the book is available for loan
    public boolean isAvailable() {
        return available;
    }

    // Method to loan the book to a person
    public void loanTo(Person person) {
        if (available) {
            this.available = false;
            this.borrower = person;
            person.borrowBook(this);
        } else {
            System.out.println("Book is not available for loan.");
        }
    }

    // Method to return the book
    public void returnBook() {
        if (!available) {
            this.available = true;
            borrower.returnBook(this);
            this.borrower = null;
        }
    }

    // Method to display all books
    public static String displayAllBooks(Book[] books) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.toString()).append("\n");
        }
        return sb.toString();
    }

    // Method to create an array of books
    public static Book[] createBooks() {
        Book[] books = new Book[2];
        books[0] = new Book("The Lord of the Rings", "J.R.R. Tolkien");
        books[1] = new Book("Harry Potter", "J.K. Rowling");
        return books;
    }

    // Method to loan a book, interacts with the user
    public static void loanBook(Book[] books, Scanner scanner) {
        System.out.println("\nEnter the name of the book you want to loan:");
        String bookName = scanner.nextLine();

        Book selectedBook = null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook != null) {
            if (selectedBook.isAvailable()) {
                System.out.println("Enter the name of the person who wants to loan the book:");
                String personName = scanner.nextLine();
                Person selectedPerson = Person.findPersonByName(personName);

                if (selectedPerson != null) {
                    selectedBook.loanTo(selectedPerson);
                    System.out.println("Book loaned successfully!");
                } else {
                    System.out.println("Person not found.");
                }
            } else {
                System.out.println("The book is not available for loan.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Method to return a book, interacts with the user
    public static void returnBook(Book[] books, Scanner scanner) {
        System.out.println("\nEnter the name of the book you want to return:");
        String bookName = scanner.nextLine();

        Book selectedBook = null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook != null) {
            if (!selectedBook.isAvailable()) {
                selectedBook.returnBook();
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("The book is not currently loaned out.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Override toString method to display book information
    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }
}
