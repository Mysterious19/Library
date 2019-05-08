public class Init{
    public static void main(String[] args){
        Database dB = new Database();
        Admin clerk = new Admin(dB);
        Menu startUp = new Menu(clerk);

        startUp.selectTask();
    } 
}