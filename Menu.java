import java.util.Scanner;

class Menu{
    Admin clerk;
    
    Menu(Admin clerk){
        this.clerk = clerk;
    }

    public void selectTask(){
        System.out.println("1. Add New User"+
                           "2. Delete a User"+
                           "3. Add New Book"+
                           "4. Remove Book"+
                           "5. Issue Book"+
                           "6. Return Book"+
                           "7. Dues Information"+
                           "8. Search a book"+
                           "9. Unused books");
    
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch(option){
            case 1 : {
                System.out.println("Enter User details : " + 
                                   "User id : ");
                int id = scanner.nextInt();
                System.out.println("Name : ");
                String name = scanner.next();
                
                clerk.addUser(id, name);
                break;
            }
            case 2 : {
                System.out.println("Enter User details : " + 
                "User id : ");
                int id = scanner.nextInt();
                clerk.removeUser(id);
                break;
            }
            case 3 : {
                System.out.println("Enter Book details : " + 
                "Book id (Integer values): ");
                int id = scanner.nextInt();
                System.out.println("Name : ");
                String name = scanner.next();
                clerk.addBook(id,name);
                break;
            }
            case 4 : {
                System.out.println("Enter User details : " + 
                "Book id : ");
                int id = scanner.nextInt();
                clerk.removeBook(id);
                break;
            }
        }
    }
}