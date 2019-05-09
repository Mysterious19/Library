import java.sql.*;
import java.util.ArrayList; 

class Admin{
    private Database db = null;

    Admin(Database db){
        this.db = db;
    }

    //admin methods
    public void addBook(int id, String name, int quantity){
        Book obj = new Book(id,name, quantity);
        db.insert(obj);
    }

    public void addUser(int id, String name){
        User obj = new User(id,name);
        db.insert(obj);
    }

    public void removeUser(int id){
        db.removeUser(id);
    }

    public void removeBook(int id){
        db.removeBook(id);
    }

    public void issue(int bookId, int userId){
        db.insert(bookId,userId);
    }
    
    public void returnBook(int bookId, int userId){
        db.removeReturn(bookId,userId);
    }

    public void search(String bookName){

        db.searchSimilar(bookName);  
    }

    public void checkUnusedBooks(int days){
        ArrayList<String> bookNames = db.searchUnused(days);
        for (String obj:bookNames)
            System.out.println(obj);
    }

}