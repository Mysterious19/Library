package library.mappers;

import java.sql.*;
import library.entities.*;
import library.*;

/*
Description : Mapper class to map Issue class object to Database query and return data 
in Issue class instance.
*/

public class IssueMap {
    private final String SQL_INSERT = "INSERT INTO issue (userId, bookId, issueDate, dueDate) VALUES (?,?,?,?)";
    private final String SQL_DELETE = "DELETE FROM issue WHERE userId = ? and bookId = ?";
    private final String SQL_SEARCH = "SELECT * FROM issue WHERE userId = ? and bookId = ?";
    private final String SQL_COUNT = "SELECT COUNT(*) AS count FROM issue WHERE userId = ?";
    
    //find user and book in the issue table
    public Issue find(Issue issue) {
        Database db = Database.getDbConn();
        Object values[] = {issue.getUserId(), issue.getBookId()};
        Issue newIssue = null;

        try {
            ResultSet res = db.query(SQL_SEARCH, values);

            while(res.next()) {
                newIssue = map(res);
            }
            
            return newIssue;
        } catch (Exception e) {
            System.out.println("ERROR in IssueMap.find");
            return null;
        }
    }

    // insert entry upon issue
    public Integer create(Issue issue) {
        Database db = Database.getDbConn();
        Object values[] = {issue.getUserId(), issue.getBookId(), issue.getIssueDate(), issue.getDueDate() };


        try {
            Integer res = db.update(SQL_INSERT, values);

            if ( res == 0) {
                System.out.println("ERROR in IssueMap.create : res = 0");
                return null;
            }
            return res;
        } catch (Exception e) {
            System.out.println("ERROR in IssueMap.create");
            return null;
        }
    }

    // Count the numbers of books issued by the user
    public Integer countBooksIssuedByUser(Integer userId) {
        Database db = Database.getDbConn();
        Object values[] = {userId};

        try {
            ResultSet res = db.query(SQL_COUNT, values);
            Integer count = 0;
            while(res.next()) {
                count = res.getInt("count");
            }
            return count;
        } catch (Exception e) {
            System.out.println("ERROR in IssueMap.countBooksIssuedByUser");
            return null;
        }
    }

    //delete record
    public Integer delete(Issue issue) {
        Database db = Database.getDbConn();
        Object values[] = { issue.getUserId(), issue.getBookId()};

        try {
            Integer res = db.update(SQL_DELETE, values);

            if ( res == 0) {
                System.out.println("ERROR in IssueMap.delete : res = 0");
                return null;
            }

            return res;
        } catch (Exception e) {
            System.out.println("ERROR in IssueMap.delete");
            return null;
        }
    }

    //----Helpers-------
    //map method to convert ResultSet to Object
    public Issue map(ResultSet res) {
        Issue issue = new Issue();
        try{
            issue.setUserId(res.getInt("userId"));
            issue.setBookId(res.getInt("bookId"));
            issue.setissueDate(res.getString("issueDate"));
            issue.setDueDate(res.getString("dueDate"));
            return issue;
        } catch(SQLException e) {
            return null;
        }   
    }
}