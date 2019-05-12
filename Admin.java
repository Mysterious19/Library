package library;

import library.mappers.*;
import library.entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
Description : Admin class operates all the functions/operations of the library,
such as create user, add books, issue book etc.
*/

class Admin {
    UserMap userMapper;
    BookMap bookMapper;
    IssueMap issueMapper;
    GroupMap groupMapper;

    
    Admin() {
        userMapper = new UserMap();
        bookMapper = new BookMap();
        issueMapper = new IssueMap();
        groupMapper = new GroupMap();
    }

    // ----------Admin-User related operation methods--------

    // Adding a new user
    public void addUser(String name, Integer group) {
        User user = new User();
        user.setName(name);
        user.setGroup(group);

        User existingUser = userMapper.find(name);

        if (existingUser != null) {
            System.out.println("User already created by this name.");
            return ;
        }

        Integer res = userMapper.create(user);

        if (res == null) {
            System.out.println("User not added successfully.");
            return;
        }

        User newUser = userMapper.find(name);

        System.out.println("\nUser successfully created. \n" + newUser);
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
        Integer count = issueMapper.countBooksIssuedByUser(id);

        if ( count != 0) {
            System.out.println("User has issued some books. Entry can not be deleted.");
            return;
        }
        Boolean flag = userMapper.delete(id);

        if (flag) {
            System.out.println("User deleted succesfully.");
        } else {
            System.out.println("No such user found.");
        }

    }

    // --------Admin-Book related operation methods---------

    // Add a new book
    public void addBook(String name, Integer quantity) {
        Book book = new Book();
        book.setName(name);
        book.setLastIssue("1998-03-19");
        book.setQuantity(quantity);
        book.setAvailable(quantity);

        List<Book> existingBook = bookMapper.search(name);

        if(!existingBook.isEmpty()) {
            System.out.println("Book already created by this name.");
            return ;
        }

        Integer res = bookMapper.create(book);

        if(res == 0) {
            System.out.println("Book not added succesfully");
            return;
        }

        List<Book> returnBook = bookMapper.search(name);
        
        System.out.println("Book added successfully. \n " + returnBook.get(0));
    }

    // Search similar name matching books
    public void search(String name) {
        name = name+"%";
        
        List<Book> similarBooks = bookMapper.search(name);
       
        if (similarBooks.isEmpty()) {
            System.out.println("No similar books found.");
            return;
        }

        String label = "";
        String lastIssueString = "nil";
        
        for (Book book : similarBooks) {
            if ( book.getAvailable() > 0) {
                label = "Availlable";
                System.out.println(book + "\nStatus : " + label);
            } else {
                label = "Not Available";
                lastIssueString = book.getLastIssue();
                System.out.println(book + "\nStatus : " + label + "\nWill be Available by : " + lastIssueString);
            }
        }
    }

    //delete a book
    public void removeBook(Integer id) {
        Book existingBook = bookMapper.findById( id );

        if (existingBook == null) {
            System.out.println("No such book found.");
            return;
        }

        if (existingBook.getAvailable() != existingBook.getQuantity()) {
            System.out.println("Book cannot be deleted as it is been issued by user.");
            return;
        }

        Integer res = bookMapper.delete(id);

        if ( res == 0) {
            System.out.println("Book not deleted successfully.");
            return;
        }
        System.out.println("Book deleted succesfully");
    }

    //--------Admin - issue/return book related operation methods-------

    //Issue book
    public void issueBook(Integer userId, Integer bookId) {
        //check user is valid
        User existingUser = userMapper.find(userId);

        if (existingUser == null) {
            System.out.println("No such user");
            return;
        }

        //get group details for max days and max books he is allowed to issue
        Integer groupId = existingUser.getGroup();
        
        Group group;
        Integer count;

        try {
            group = groupMapper.find(groupId);
            count = issueMapper.countBooksIssuedByUser(userId);    
            
            if ( group == null ) {
                System.out.println("No such group.");
                return;
            }
        } catch (Exception e) {
            return;
        }
        
        if (count >  group.getMaxBooks()) {
            System.out.println("Reached max books limit.");
            return;
        }

        //check whether book is available
        Book book = bookMapper.findById(bookId);

        if (book == null) {
            System.out.println("No such book found.");
            return;
        }

        if (book.getAvailable() <= 0) {
            System.out.println("Book not available currently.");
            System.out.println("It will be available by : " + book.getLastIssue());
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(group.getMaxTime());
        String issueDateString = issueDate.format(formatter);
        String dueDateString = dueDate.format(formatter);

        Issue issue = new Issue();
        issue.setUserId(userId);
        issue.setBookId(bookId);
        issue.setissueDate(issueDateString);
        issue.setDueDate(dueDateString);

        //check whether is already has issued the same book
        Issue existingIssue = issueMapper.find(issue);

        if (existingIssue != null ) {
            System.out.println("User can not issue same book twice.");
            return;
        }

        Integer res = issueMapper.create(issue);

        if (res == null) {
            System.out.println("Book issue failed.");
            return;
        }

        //update availability count and last issue
        book.setAvailable(book.getAvailable()-1);
        book.setLastIssue(dueDateString);
        
        Integer result = bookMapper.update(book);

        Issue newIssue = issueMapper.find(issue);

        System.out.println("Book issue successful." + newIssue);
    }
}