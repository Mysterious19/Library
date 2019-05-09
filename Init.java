//Begin
public class Init{
    public static void main(String[] args){
        Database dB = new Database();
        Admin clerk = new Admin(dB);
        Menu startUp = new Menu(clerk);
        int loop = 1;
        while(loop==1){
            loop = startUp.selectTask();
        }
        
    } 
}