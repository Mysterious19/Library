import java.sql.*;
import java.util.ArrayList; 

class Admin{
    private Database db = null;

    Admin(Database db){
        this.db = db;
    }

    public void addBook(int id, String name){
        Book obj = new Book(id,name);
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
        db.remove(bookId,userId);
    }

    public void search(String bookName){

        ArrayList<String> bookNames = db.search(bookName);
        for (String obj:bookNames)
            System.out.println(obj);
    }

    public void checkUnusedBooks(int days){
        ArrayList<String> bookNames = db.search(days);
        for (String obj:bookNames)
            System.out.println(obj);
    }

}