package se.lexicon;

import java.util.Scanner;

import se.lexicon.model.Book;
import se.lexicon.model.Person;

public class App {
    public static void main(String[] args) {
        // Create books.
        Book[] books = Book.createBooks();

        // Create person instances
        new Person("Sami", "Alabed");
        new Person("Mohammed", "Dawood");

        // Display all person information
        System.out.println("Display all person information in the system: ");
        System.out.println(Person.displayAllPersons());

        // Display all book information
        System.out.println("\nDisplay all book information in the system: ");
        System.out.println(Book.displayAllBooks(books));

        Scanner scanner = new Scanner(System.in);
        boolean continueAction = true;

        while (continueAction) {
            // Ask if the user wants to loan or return a book
            System.out.println("\nDo you want to loan or return a book? (loan/return):");
            String action = scanner.nextLine();

            if (action.equalsIgnoreCase("loan")) {
                // Loan a book
                Book.loanBook(books, scanner);
            } else if (action.equalsIgnoreCase("return")) {
                // Return a book
                Book.returnBook(books, scanner);
            } else {
                System.out.println("Invalid action. Please enter 'loan' or 'return'.");
            }

            // Ask if the user wants to perform another action
            System.out.println("\nDo you want to perform another action? (yes/no):");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("no")) {
                continueAction = false;
            }
        }

        // Display all book information after loaning/returning
        System.out.println("\nDisplay all book information after loaning/returning: ");
        System.out.println(Book.displayAllBooks(books));

        // Display the books that each person has borrowed
        System.out.println("\nDisplay all borrowed books for each person:");
        for (Person person : Person.getPersons()) {
            System.out.println(person.displayBorrowedBooks());
        }

        System.out.println("Goodbye!");
    }
}
