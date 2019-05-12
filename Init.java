package library;

/*
Description : Init class is the main class from where the library inventory system begins
*/

public class Init {
    public static void main(String[] args) {

        System.out.println("------------------------------------------------------");
        System.out.println("              Library Inventory System                ");
        System.out.println("------------------------------------------------------");
        
        // Table creation required only once during the set-up of database
        TableCreation obj = new TableCreation();
        obj.createTables();

        // Admin object clerk to handle all the operations of the library
        Admin clerk = new Admin();

        // Menu object listing all the various operations
        Menu startUp = new Menu(clerk);
        int loop = 1;
        
        while (loop == 1) {
            loop = startUp.selectTask();
        }

    }
}