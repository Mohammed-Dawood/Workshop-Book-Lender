package se.lexicon.model;

import java.util.Arrays;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private static int sequencer = 0;
    private static Person[] persons = new Person[0];
    private Book[] borrowedBooks = new Book[0];

    // Constructor
    public Person(String firstName, String lastName) {
        getNextId();
        validateAndAssignStringInput(firstName, "firstName");
        validateAndAssignStringInput(lastName, "lastName");
        addPerson(this);
    }

    // Method to generate the next ID for a person
    private void getNextId() {
        this.id = ++sequencer;
    }

    // Method to validate and assign input strings to the appropriate fields
    private void validateAndAssignStringInput(String stringInput, String paramName) {
        if (stringInput == null || stringInput.trim().isEmpty()) {
            throw new IllegalArgumentException(paramName + " must not be null or empty");
        }

        switch (paramName) {
            case "firstName":
                this.firstName = stringInput;
                break;
            case "lastName":
                this.lastName = stringInput;
                break;
        }
    }

    // Method to add a person to the persons array
    private static void addPerson(Person person) {
        persons = Arrays.copyOf(persons, persons.length + 1);
        persons[persons.length - 1] = person;
    }

    // Method to get all persons
    public static Person[] getPersons() {
        return persons;
    }

    // Method to find a person by their full name
    public static Person findPersonByName(String name) {
        for (Person person : persons) {
            if ((person.getFirstName() + " " + person.getLastName()).equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    // Method to borrow a book
    public void borrowBook(Book book) {
        borrowedBooks = Arrays.copyOf(borrowedBooks, borrowedBooks.length + 1);
        borrowedBooks[borrowedBooks.length - 1] = book;
    }

    // Method to return a borrowed book
    public void returnBook(Book book) {
        Book[] newBorrowedBooks = new Book[borrowedBooks.length - 1];
        int index = 0;
        for (Book b : borrowedBooks) {
            if (!b.equals(book)) {
                newBorrowedBooks[index++] = b;
            }
        }
        borrowedBooks = newBorrowedBooks;
    }

    // Method to display all borrowed books for this person
    public String displayBorrowedBooks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Borrowed Books by ").append(firstName).append(" ").append(lastName).append(":\n");
        for (Book book : borrowedBooks) {
            sb.append(book.toString()).append("\n");
        }
        return sb.toString();
    }

    // Method to display information of all persons in the system
    public static String displayAllPersons() {
        StringBuilder sb = new StringBuilder();
        for (Person person : persons) {
            sb.append(person.toString()).append("\n");
        }
        return sb.toString();
    }

    // Getter method for firstName
    public String getFirstName() {
        return firstName;
    }

    // Getter method for lastName
    public String getLastName() {
        return lastName;
    }

    // Override toString method to display person information
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
