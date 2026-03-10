// Code and logic to add to Loan Class constructor

      if (!book.isAvailable()) { 
            throw new IllegalStateException("Book is not available."); 
        } 
  

        if (!member.canBorrow()) { 
            throw new IllegalStateException("Borrow limit reached."); 
        } 

        //Initialise member  

       // Initialise book 

        //Set book availability to false   

       //Increment member borrowed books  
