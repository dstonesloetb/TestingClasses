//Insert package name

/**
 *
 * @author Student Name
 */
public class BookTest {
    public static void main(String[] args) {
        System.out.println("=== Book Test ===");

        // Create a Book instance  BOO1, Java Programming, J. Smith, 12345


        // Print initial state
        System.out.println("Initial Book: " + book);

        // Borrow the book
        System.out.println("\nBorrowing the book...");
        if (book.isAvailable()) {
            //Set book availabilty to false 

            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is not available to borrow.");
        }

        // Attempt to borrow again (should fail)
        System.out.println("\nAttempting to borrow again...");
 
        //Insert code to check if book is not available
        //Output "Book is already on loan." 


        // Return the book
        System.out.println("\nReturning the book...");
        //Set book availabilty to true 

        System.out.println("Book returned successfully.");

        // Final state
        System.out.println("\nFinal Book: " + book);
    }
}
