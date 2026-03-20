//Code to add after class declaration before gui constructor 

 // Data storage
    private Map<Integer, Member> members = new HashMap<>();
    private Set<Integer> usedMemberIds = new HashSet<>();
    private List<Book> books = new ArrayList<>();

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");


//code to add to gui constructor
        // Sample books
        books.add(new Book("B001", "Java Basics", "J. Smith", "111"));
        books.add(new Book("B002", "OOP with Java", "A. Brown", "222"));


//Code to add after constructor

    // ---------------- MEMBER METHODS ----------------
    private void addMember() {

                
        //Check add member fields are not empty 

        //Get member detals from form

        //Creat student member or staff member onject

        //add member and memberId to list
        
        members.put(id, member);
        usedMemberIds.add(id);

        //show member in gui



    }

    private void viewEditDeleteMember() {
        if (members.isEmpty()) {
            showError("No members available.");
            return;
        }
        String[] memberArray = members.values().stream()
                .map(m -> m.getMemberId() + " - " + m.getName())
                .toArray(String[]::new);

        JComboBox<String> memberBox = new JComboBox<>(memberArray);
        Object[] options = {"View", "Edit", "Delete", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this, memberBox, "Select Member",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (choice == 0) { // View
            Member m = new ArrayList<>(members.values()).get(memberBox.getSelectedIndex());
            outputArea.setText(
                    "Member Details:\nID: " + m.getMemberId() + "\nName: " + m.getName() +
                            "\nEmail: " + m.getEmail() + "\nBorrowed Books: " + m.getBorrowedBooks() +
                            "\nBorrow Limit: " + m.calculateBorrowLimit()
            );
        } else if (choice == 1) { // Edit
            Member m = new ArrayList<>(members.values()).get(memberBox.getSelectedIndex());
            editMember(m);
        } else if (choice == 2) { // Delete
            Member m = new ArrayList<>(members.values()).get(memberBox.getSelectedIndex());
            members.remove(m.getMemberId());
            usedMemberIds.remove(m.getMemberId());
            outputArea.setText("Member deleted: " + m.getName());
        }
    }

    private void editMember(Member m) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField nameField = new JTextField(m.getName());
        JTextField emailField = new JTextField(m.getEmail());

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Edit Member", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String email = emailField.getText();
            if (name.isEmpty() || email.isEmpty()) {
                showError("All fields required.");
                return;
            }
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                showError("Invalid email format.");
                return;
            }
            m.name = name;
            m.email = email;
            outputArea.setText("Member updated: " + m.getName());
        }
    }

    // ---------------- BOOK METHODS ----------------
    private void addBook() {

        //Check add book fields are not empty 

        //Get book details from form

        //Creat a book object

        //add book to books list

        //show book in gui

        }
    

    private void viewEditDeleteBook() {
        if (books.isEmpty()) {
            showError("No books available.");
            return;
        }
        String[] bookArray = books.stream()
                .map(b -> b.getItemId() + " - " + b.getTitle() + (b.isAvailable() ? " (Available)" : " (On Loan)"))
                .toArray(String[]::new);

        JComboBox<String> bookBox = new JComboBox<>(bookArray);
        Object[] options = {"View", "Edit", "Delete", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this, bookBox, "Select Book",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        Book b = books.get(bookBox.getSelectedIndex());
        if (choice == 0) { // View
            outputArea.setText("Book Details:\nID: " + b.getItemId() + "\nTitle: " + b.getTitle() +
                    "\nAuthor: " + b.getAuthor() + "\nISBN: " + b.getIsbn() +
                    "\nAvailable: " + b.isAvailable());
        } else if (choice == 1) { // Edit
            editBook(b);
        } else if (choice == 2) { // Delete
            books.remove(b);
            outputArea.setText("Book deleted: " + b.getTitle());
        }
    }

    private void editBook(Book b) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField titleField = new JTextField(b.getTitle());
        JTextField authorField = new JTextField(b.getAuthor());
        JTextField isbnField = new JTextField(b.getIsbn());

        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Edit Book", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (titleField.getText().isEmpty() || authorField.getText().isEmpty() || isbnField.getText().isEmpty()) {
                showError("All fields required.");
                return;
            }
            b.setTitle(titleField.getText());
            b.setAuthor(authorField.getText());
            b.setIsbn(isbnField.getText());
            outputArea.setText("Book updated: " + b.getTitle());
        }
    }



//Code to add to end of Library GUI before main method 

// ---------------- UTILS ----------------
    private Member selectMember() {
        String[] memberArray = members.values().stream()
                .map(m -> m.getMemberId() + " - " + m.getName())
                .toArray(String[]::new);
        JComboBox<String> memberBox = new JComboBox<>(memberArray);
        int result = JOptionPane.showConfirmDialog(this, memberBox, "Select Member", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return new ArrayList<>(members.values()).get(memberBox.getSelectedIndex());
        }
        return null;
    }

    private Book selectBook() {
        String[] bookArray = books.stream()
                .map(b -> b.getItemId() + " - " + b.getTitle() + (b.isAvailable() ? " (Available)" : " (On Loan)"))
                .toArray(String[]::new);
        JComboBox<String> bookBox = new JComboBox<>(bookArray);
        int result = JOptionPane.showConfirmDialog(this, bookBox, "Select Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return books.get(bookBox.getSelectedIndex());
        }
        return null;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
