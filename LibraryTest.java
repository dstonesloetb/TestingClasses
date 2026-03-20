package com.mycompany.libraryprogram;

/**
 *
 * @author Student
 */
import java.util.*;

public class LibraryTest {
    public static void main(String[] args) {

        System.out.println("=== Smart Library Test Simulation ===");

        // -------------------- CREATE MEMBERS --------------------
        Member student = new StudentMember(1, "Alice", "alice@example.com");
        Member staff = new StaffMember(2, "Bob", "bob@example.com");

        System.out.println("\nMembers Created:");
        System.out.println(student);
        System.out.println(staff);

        // -------------------- CREATE BOOKS --------------------
        Book book1 = new Book("B001", "Java Basics", "J. Smith", "111");
        Book book2 = new Book("B002", "OOP with Java", "A. Brown", "222");
        Book book3 = new Book("B003", "Data Structures", "C. Lee", "333");

        

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);

        

        System.out.println("\nBooks Created:");
        for (Book b : books) System.out.println(b);
        
        /*Generate the loan tests  */
        System.out.println("");
        

        // -------------------- BORROW LOGIC --------------------
        System.out.println("\n--- Borrowing Books ---");
        borrowBook(student, book1); // valid
        borrowBook(student, book2); // valid
        borrowBook(student, book3); // valid
        borrowBook(student, book3); // invalid (already borrowed by Alice)
        borrowBook(staff, book1);   // invalid (already borrowed)

        // Borrow limit test
        Book book4 = new Book("B004", "Algorithms", "D. Kim", "444");
        books.add(book4);
        borrowBook(student, book4); // still within limit
        Book book5 = new Book("B005", "Networks", "E. Tan", "555");
        books.add(book5);
        borrowBook(student, book5); // still within limit

        // write code to exceed student limit (5 books)   ******
        
        
        
        

        // -------------------- RETURN LOGIC --------------------
        System.out.println("\n--- Returning Books ---");
        returnBook(student, book1); // valid return
        returnBook(staff, book1);   // invalid return (staff didn't borrow)
        borrowBook(staff, book1);   // now staff can borrow

        // -------------------- FINAL STATE --------------------
        System.out.println("\n--- Final Member Status ---");
        System.out.println(student);
        System.out.println(staff);

        System.out.println("\n--- Final Book Status ---");
        for (Book b : books) System.out.println(b);
    }

    // -------------------- HELPER METHODS --------------------
    private static void borrowBook(Member member, Book book) {
        System.out.println("\nAttempting to borrow '" + book.getTitle() + "' by " + member.getName());
        try {
            Loan loan = new Loan(member, book);
            System.out.println("Loan successful: " + loan.getLoanDetails());
        } catch (IllegalStateException ex) {
            System.out.println("Cannot borrow: " + ex.getMessage());
        }
    }

    private static void returnBook(Member member, Book book) {
        System.out.println("\nAttempting to return '" + book.getTitle() + "' by " + member.getName());
        if (!book.isAvailable() && member.getBorrowedBooks() > 0) {
            book.setAvailable(true);
            member.decrementBorrowedBooks();
            System.out.println("Return successful.");
        } else {
            System.out.println("Return failed. Book may not be borrowed by this member.");
        }
    }
}
