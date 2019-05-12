package library;

import java.util.Scanner;

/*
Description : Menu class lists all the operations of the library and takes input for the required
operation 
*/

public class Menu {
    Admin clerk;

    Menu(Admin clerk) {
        this.clerk = clerk;
    }

    // -----Select task or operation to be performed--------
    public int selectTask() {
        System.out.println("\n             MENU             ");
        System.out.println("1. Add New User \n" + "2. Find User By ID \n" + "3. Find User by Name \n"
                + "4. Delete User by ID \n\n" + "5. Add New Book \n" + "6. Search Book by Name(suggestions) \n"
                + "7. Delete Book by ID \n\n" + "8. Issue Book \n" + "9. Return Book \n" + "10. List of books issued the user\n"
                + "11. Remove Unused Books\n" + "12. Exit");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
        // Add new user
        case 1: {
            System.out.println("\nEnter User details : ");
            System.out.print("User Name : ");
            String name = scanner.next();
            System.out.print("Group id (1-Student, 2-Staff, 3- Faculty) : ");
            Integer group = scanner.nextInt();

            clerk.addUser(name, group);

            break;
        }

        // Find user by id
        case 2: {
            System.out.println("\nEnter User ID : ");
            Integer id = scanner.nextInt();

            clerk.findUserById(id);

            break;
        }

        // Find user by name
        case 3: {
            System.out.println("\nEnter User Name : ");
            String name = scanner.next();

            clerk.findUserByName(name);

            break;
        }

        // Delete user by id
        case 4: {
            System.out.println("\nEnter User ID : ");
            Integer id = scanner.nextInt();

            clerk.removeUser(id);

            break;
        }

        // Add new book
        case 5: {
            System.out.println("\nEnter Book details : ");
            System.out.println("Book Name : ");
            String name = scanner.next();
            System.out.println("Quantity : ");
            Integer quantity = scanner.nextInt();

            clerk.addBook(name, quantity);

            break;
        }

        // Search book by name
        case 6: {
            System.out.println("\nEnter name of the book (or prefix of a book name)");
            String name = scanner.next();

            clerk.search(name);

            break;
        }

        // Delete book by id
        case 7: {
            System.out.println("\nEnter Book ID of the book to be deleted.");
            Integer id = scanner.nextInt();

            clerk.removeBook(id);

            break;
        }

        // Issue book from library
        case 8: {
            System.out.println("\nEnter User ID : ");
            int userId = scanner.nextInt();
            System.out.println("Enter Book ID : ");
            int bookId = scanner.nextInt();

            clerk.issueBook(userId, bookId);

            break;
        }

        // return book to library
        case 9: {
            System.out.println("\nEnter User ID : ");
            int userId = scanner.nextInt();
            System.out.println("Enter Book ID : ");
            int bookId = scanner.nextInt();

            clerk.returnBook(bookId, userId);
            
            break;
        }

        //list of books issued by the user
        case 10: {
            System.out.println("Enter User ID :");
            Integer id = scanner.nextInt();
            
            clerk.listBooksIssue(id);
            
            break;
        }

        // remove unused books
        case 11: {
            System.out.println("Enter number of days :");
            Integer days = scanner.nextInt();
            
            clerk.unusedBooks(days);
            
            break;
        }

        //exit
        case 12: {
            return 0;
        }
        default:
            System.out.println("Did not match any option. Please specify again.");
            return 1;
        }

        System.out.println("Press any key to continue . . . ");
        try {
            System.in.read();
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }
}