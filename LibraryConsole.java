package com.mycompany.libraryprogram;

import java.util.*;
import java.util.regex.Pattern;

// -------------------- MAIN CLASS --------------------
public class LibraryConsole {

    private static Map<Integer, Member> members = new HashMap<>();
    private static Set<Integer> usedMemberIds = new HashSet<>();
    private static List<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static void main(String[] args) {

        // Preload some books
        books.add(new Book("B001", "Java Basics", "J. Smith", "111"));
        books.add(new Book("B002", "OOP with Java", "A. Brown", "222"));

        boolean running = true;

        while (running) {
            System.out.println("\n=== Smart Library Management System ===");
            System.out.println("1. Add Member");
            System.out.println("2. View/Edit/Delete Member");
            System.out.println("3. Add Book");
            System.out.println("4. View/Edit/Delete Book");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addMember();
                case "2" -> manageMember();
                case "3" -> addBook();
                case "4" -> manageBook();
                case "5" -> borrowBook();
                case "6" -> returnBook();
                case "7" -> {
                    running = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    // -------------------- MEMBER METHODS --------------------
    private static void addMember() {
        try {
            System.out.print("Enter Member ID (numeric): ");
            int id = Integer.parseInt(scanner.nextLine());
            if (usedMemberIds.contains(id)) {
                System.out.println("Member ID already exists.");
                return;
            }

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                System.out.println("Invalid email format.");
                return;
            }

            System.out.print("Member Type (Student/Staff): ");
            String type = scanner.nextLine();

            Member member = type.equalsIgnoreCase("Student") ?
                    new StudentMember(id, name, email) :
                    new StaffMember(id, name, email);

            members.put(id, member);
            usedMemberIds.add(id);
            System.out.println("Member added: " + member.getName());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Must be numeric.");
        }
    }

    private static void manageMember() {
        if (members.isEmpty()) {
            System.out.println("No members available.");
            return;
        }
        Member member = selectMember();
        if (member == null) return;

        System.out.println("1. View");
        System.out.println("2. Edit");
        System.out.println("3. Delete");
        System.out.print("Choose action: ");
        String action = scanner.nextLine();

        switch (action) {
            case "1" -> System.out.println(member);
            case "2" -> editMember(member);
            case "3" -> {
                members.remove(member.getMemberId());
                usedMemberIds.remove(member.getMemberId());
                System.out.println("Member deleted: " + member.getName());
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private static void editMember(Member m) {
        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();
        if (name.isEmpty() || email.isEmpty()) {
            System.out.println("Fields cannot be empty.");
            return;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("Invalid email format.");
            return;
        }
        m.setName(name);
        m.setEmail(email);
        System.out.println("Member updated: " + m.getName());
    }

    // -------------------- BOOK METHODS --------------------
    private static void addBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        if (id.isEmpty() || title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            System.out.println("All fields required.");
            return;
        }
        books.add(new Book(id, title, author, isbn));
        System.out.println("Book added: " + title);
    }

    private static void manageBook() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        Book book = selectBook();
        if (book == null) return;

        System.out.println("1. View");
        System.out.println("2. Edit");
        System.out.println("3. Delete");
        System.out.print("Choose action: ");
        String action = scanner.nextLine();

        switch (action) {
            case "1" -> System.out.println(book);
            case "2" -> editBook(book);
            case "3" -> {
                books.remove(book);
                System.out.println("Book deleted: " + book.getTitle());
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private static void editBook(Book b) {
        System.out.print("Enter new Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter new ISBN: ");
        String isbn = scanner.nextLine();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            System.out.println("All fields required.");
            return;
        }
        b.setTitle(title);
        b.setAuthor(author);
        b.setIsbn(isbn);
        System.out.println("Book updated: " + b.getTitle());
    }

    // -------------------- BORROW / RETURN --------------------
    private static void borrowBook() {
        Member member = selectMember();
        Book book = selectBook();
        if (member == null || book == null) return;

        try {
            Loan loan = new Loan(member, book);
            System.out.println("Loan successful: " + loan.getLoanDetails());
        } catch (IllegalStateException ex) {
            System.out.println("Cannot borrow: " + ex.getMessage());
        }
    }

    private static void returnBook() {
        Member member = selectMember();
        Book book = selectBook();
        if (member == null || book == null) return;

        if (!book.isAvailable() && member.getBorrowedBooks() > 0) {
            book.setAvailable(true);
            member.decrementBorrowedBooks();
            System.out.println("Book returned: " + book.getTitle() + " by " + member.getName());
        } else {
            System.out.println("Invalid return.");
        }
    }

    // -------------------- UTILS --------------------
    private static Member selectMember() {
        System.out.println("Select Member:");
        for (Member m : members.values()) {
            System.out.println(m.getMemberId() + ": " + m.getName());
        }
        System.out.print("Enter Member ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            return members.get(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return null;
        }
    }

    private static Book selectBook() {
        System.out.println("Select Book:");
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            System.out.println(i + 1 + ": " + b.getTitle() + (b.isAvailable() ? " (Available)" : " (On Loan)"));
        }
        System.out.print("Enter Book number: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > books.size()) return null;
            return books.get(choice - 1);
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return null;
        }
    }
}
