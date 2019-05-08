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
            case 1 : 
                System.out.println("Enter User details : " + 
                                   "User id : ");
                int id = scanner.nextInt();
                System.out.println("Name : ");
                String name = scanner.next();
                
                clerk.addUser(id, name);
                break;
        }
    }
}