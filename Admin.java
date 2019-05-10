import java.sql.*;
import java.util.ArrayList;

class Admin {
    UserMap userMapper;
    BookMap bookMapper;

    Admin() {
        userMapper = new UserMap();
        bookMapper = new BookMap();
    }

    // ----------Admin-User related methods--------

    // Adding new user
    public void addUser(String name) {
        User user = new User();
        user.setName(name);

        User returnUser = userMapper.create(user);

        if (returnUser == null) {
            System.out.println("User already created by this name.");
            return;
        }
        System.out.println("User created. \n " + returnUser);
    }

    // find user by id
    public void findUserById(Integer id) {
        User user = userMapper.find(id);

        if (user == null) {
            System.out.println("No such user found.");
            return;
        }
        System.out.println(user);
    }

    // find user by name
    public void findUserByName(String name) {
        User user = userMapper.find(name);

        if (user == null) {
            System.out.println("No such user found.");
        }
        System.out.println(user);
    }

    // remove user by id
    public void removeUser(Integer id) {
        Boolean flag = userMapper.delete(id);

        if (flag) {
            System.out.println("User deleted succesfully.");
        } else {
            System.out.println("User not deleted.");
        }

    }

    // --------Admin-Book related methods---------

    // Add new book
    // public void addBook(String name, Integer quantity) {
    //     Book book = new Book();
    //     book.setName(name);
    //     book.setQuantity(quantity);

    //     Book returnBook = bookMapper.create(book);

    //     if (returnBook == null) {
    //         System.out.println("ERROR in Admin.addBook : book not created.");
    //         return;
    //     }
    //     System.out.println("Book created. \n " + returnBook);
    // }

    // public void removeBook(int id) {
    // db.removeBook(id);
    // }

    // public void issue(int bookId, int userId) {
    // db.insert(bookId, userId);
    // }

    // public void returnBook(int bookId, int userId) {
    // db.removeReturn(bookId, userId);
    // }

    // public void search(String bookName) {

    // db.searchSimilar(bookName);
    // }

    // public void checkUnusedBooks(int days) {
    // ArrayList<String> bookNames = db.searchUnused(days);
    // for (String obj : bookNames)
    // System.out.println(obj);
    // }

}