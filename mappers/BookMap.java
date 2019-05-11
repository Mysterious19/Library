package library.mappers;

import library.*;
import library.entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/*
Description : Mapper class to map Book class object to Database query and return data 
in Book class instance.
*/

public class BookMap{
    private final String SQL_INSERT = "INSERT INTO books(name,lastIssue, quantity, available) VALUES (?, ?, ?, ?)";
    private final String SQL_DELETE = "DELETE FROM books WHERE bookId = ?";
    private final String SQL_SEARCH = "SELECT bookId, name, lastIssue, quantity, available FROM books WHERE name LIKE ?";
    private final String SQL_UPDATE_DATE = "UPDATE books SET lastIssue = ? WHERE bookId = ?";

    // insert book
    public Integer create(Book book) {
        Database db = Database.getDbConn();
        Object values[] = { book.getName(),book.getLastIssue(), book.getQuantity(), book.getAvailable() };
        
        try {
            Integer res = db.update(SQL_INSERT, values);
            
            if (res == 0) {
                System.out.println("ERROR in BookMap.create : res = 0");
                return null;
            }

            return res;
        } catch (Exception e) {
            System.out.println(e + "ERROR in BookMap.create");
            return null;
        }    
    }

    //-----search by prefix matching---
    public List<Book> search(String name){
        Database db = Database.getDbConn();
        Object values[] = { name };

        List<Book> similarBooks = new ArrayList<>();

        try {
            ResultSet simBooks = db.query(SQL_SEARCH, values);
              
            while (simBooks.next()) {
                similarBooks.add(map(simBooks));
            }

            return similarBooks;
        } catch (Exception e) {
            System.out.println("ERROR in BookMap.search");
            return null;
        }
    }

    //Update lastIssue date method
    public Integer update(String date, Integer bookId) {
        Database db = Database.getDbConn();
        Object values[] = { date, bookId };

        try {
            Integer res = db.update(SQL_UPDATE_DATE, values);

            if ( res == 0) {
                System.out.println("ERROR in BookMap.update : res =0");
                return null;
            }
            return res;
        } catch (Exception e) {
            System.out.println("ERROR in BookMap.update");
            return null;
        }
    }


    // ---------------Helpers--------------
    // Map the row of the given ResultSet to a Book
    private Book map(ResultSet res) {
        Book book = new Book();

        try {
            book.setId(res.getInt("bookId"));
            book.setName(res.getString("name"));
            book.setLastIssue(res.getString("lastIssue"));
            book.setQuantity(res.getInt("quantity"));
            book.setAvailable(res.getInt("available"));
        } catch (SQLException e) {
            return null;
        }
        return book;
    }
}