package library.mappers;

import library.*;
import library.entities.*;
import library.interfaces.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/*
Description : Mapper class to map Book class object to Database query and return data 
in Book class instance.
*/

public class BookMap implements BookInterface {
    private final String SQL_INSERT = "INSERT INTO books(name,lastIssue, quantity, available) VALUES (?, ?, ?, ?)";
    private final String SQL_DELETE = "DELETE FROM books WHERE bookId = ?";
    private final String SQL_SEARCH = "SELECT bookId, name, lastIssue, quantity, available FROM books WHERE name LIKE ?";
    private final String SQL_SEARCH_ID = "SELECT bookId, name, lastIssue, quantity, available FROM books WHERE bookId = ?";
    private final String SQL_UPDATE = "UPDATE books SET name = ?, lastIssue = ?, quantity = ?,available = ?  WHERE bookId = ?";
    private final String SQL_FIND_LAST = "SELECT * FROM books WHERE lastIssue < ?";

    // insert book
    public Integer create(Book book) {
        Database db = Database.getDbConn();
        Object values[] = { book.getName(), book.getLastIssue(), book.getQuantity(), book.getAvailable() };

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

    // -----search by prefix matching---
    public List<Book> search(String name) {
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

    // search book by Book ID
    public Book findById(Integer id) {
        Database db = Database.getDbConn();
        Object values[] = { id };
        Book book = null;

        try {
            ResultSet res = db.query(SQL_SEARCH_ID, values);

            while (res.next()) {
                book = map(res);
            }

            return book;
        } catch (Exception e) {
            System.out.println("ERROR in BookMap.finById");
            return null;
        }
    }

    // Update book record
    public Integer update(Book book) {
        Database db = Database.getDbConn();
        Object values[] = { book.getName(), book.getLastIssue(), book.getQuantity(), book.getAvailable(),
                book.getId() };

        try {
            Integer res = db.update(SQL_UPDATE, values);

            if (res == 0) {
                System.out.println("ERROR in BookMap.update : res =0");
                return null;
            }
            return res;
        } catch (Exception e) {
            System.out.println("ERROR in BookMap.update");
            return null;
        }
    }

    // delete book by book id
    public Integer delete(Integer id) {
        Database db = Database.getDbConn();
        Object values[] = { id };

        try {
            Integer res = db.update(SQL_DELETE, values);

            if (res == 0) {
                System.out.println("ERROR in BookMap.delete : res = 0");
                return null;
            }
            return res;
        } catch (Exception e) {
            System.out.println("ERROR in BookMap.delete");
            return null;
        }
    }

    // list of unused books
    public List<Book> unusedBooksOperation(Object values[]) {
        Database db = Database.getDbConn();
        List<Book> books = new ArrayList<>();

        try {
            ResultSet res = db.query(SQL_FIND_LAST, values);

            while (res.next()) {
                books.add(map(res));
            }

            return books;
        } catch (Exception e) {
            System.out.println("ERROR in BookMap.unusedBooksOperation");
            return books;
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