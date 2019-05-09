import java.util.Scanner;


class Menu{
    Admin clerk;
    
    Menu(Admin clerk){
        this.clerk = clerk;
    }

    //menu option selecting functionality
    public int selectTask(){
        System.out.println("1. Add New User \n"+
                           "2. Delete a User \n"+
                           "3. Add New Book \n"+
                           "4. Remove Book \n"+
                           "5. Issue Book \n"+
                           "6. Return Book \n"+
                           "7. Search a book \n"+
                           "8. Unused books \n"+
                           "9. Exit ");
    
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch(option){
            case 1 : {
                System.out.println("Enter User details : \n" + 
                                   "User id : ");
                int id = scanner.nextInt();
                System.out.println("Name : ");
                String name = scanner.next();
                clerk.addUser(id, name);
                break;
            }
            case 2 : {
                System.out.println("Enter User details : \n" + 
                "User id : ");
                int id = scanner.nextInt();
                clerk.removeUser(id);
                break;
            }
            case 3 : {
                System.out.println("Enter Book details : \n" + 
                "Book id (Integer values): ");
                int id = scanner.nextInt();
                System.out.println("Name : ");
                String name = scanner.next();
                System.out.println("Quantity : ");
                int quantity = scanner.nextInt();
                clerk.addBook(id,name,quantity);
                break;
            }
            case 4 : {
                System.out.println("Enter User details : " + 
                "Book id : ");
                int id = scanner.nextInt();
                clerk.removeBook(id);
                break;
            }
            case 5 : {
                System.out.println("Enter User id : ");
                int bookId = scanner.nextInt();
                System.out.println("Enter Book id : ");
                int userId = scanner.nextInt();
            
                clerk.issue(bookId,userId);
                break;
            }

            case 6 : {
                System.out.println("Enter User id : ");
                int userId = scanner.nextInt();
                System.out.println("Enter Book id : ");
                int bookId = scanner.nextInt();
            
                clerk.returnBook(bookId,userId);
                break;
            }
            case 7 : {
                System.out.println("Type the name of the book (with suggestions)");
                String name = scanner.next();

                clerk.search(name);
                break;
            }
            case 8:{
                System.out.println("Specify days of unuse :");
                int days = scanner.nextInt();
                clerk.checkUnusedBooks(days);
                break;
            }
            case 9 : {
                return 0;
            }
            default : 
                return 1;                

        }
        return 1;
    }
}