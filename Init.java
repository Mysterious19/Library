//Begin
public class Init {
    public static void main(String[] args) {

        TableCreation obj = new TableCreation();
        obj.createTables();
        
        Admin clerk = new Admin();
        Menu startUp = new Menu(clerk);
        int loop = 1;
        while (loop == 1) {
            loop = startUp.selectTask();
        }

    }
}