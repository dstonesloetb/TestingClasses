//Code to add to Member.java class


 public void incrementBorrowedBooks() {
        borrowedBooks++;
    }
    
    public void decrementBorrowedBooks() {
        if (borrowedBooks > 0) borrowedBooks--;
    }
    
    public abstract int calculateBorrowLimit();

    
    public boolean canBorrow() {
        return borrowedBooks < calculateBorrowLimit();
    }
