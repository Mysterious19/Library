package library;

/*
Description : TableCreation class is need to create tables for the database.
If the tables are already present in the database, it does not re-create them.
*/

import java.sql.*;

public class TableCreation {
    private Statement stmt = null;
    private String sql = null;

    // create tables method checks for th existing tables in the database, and if
    // not present creates them
    public void createTables() {
        Database db = Database.getDbConn();

        try {
            DatabaseMetaData dbm = db.conn.getMetaData();
            // table 'books'
            ResultSet rs = dbm.getTables(null, null, "books", null);

            if (!rs.next()) {
                try {
                    stmt = db.conn.createStatement();
                    sql = "CREATE TABLE books (bookId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT UNIQUE,"
                            + " lastIssue TEXT , quantity INTEGER, available INTEGER)";

                    stmt.executeUpdate(sql);
                } catch (Exception e) {
                    System.out.println("Table books not created.");
                }
            }

            // table 'groupUser'
            rs = dbm.getTables(null, null, "groupUser", null);
            if (!rs.next()) {
                try {
                    stmt = db.conn.createStatement();
                    sql = "CREATE TABLE groupUser(groupId INTEGER PRIMARY KEY NOT NULL ,groupName TEXT, "
                            + "maxBooks INTEGER, maxTime INTEGER )";

                    stmt.executeUpdate(sql);

                    stmt = db.conn.createStatement();
                    sql = "INSERT INTO groupUser(groupId, groupName, maxBooks, maxTime) VALUES (1,'Student',3,10)";

                    stmt.executeUpdate(sql);

                    stmt = db.conn.createStatement();
                    sql = "INSERT INTO groupUser(groupId, groupName, maxBooks, maxTime) VALUES (2,'Staff',5,20)";

                    stmt.executeUpdate(sql);

                    stmt = db.conn.createStatement();
                    sql = "INSERT INTO groupUser(groupId, groupName, maxBooks, maxTime) VALUES (3,'Faculty',10,30)";

                    stmt.executeUpdate(sql);
                } catch (Exception e) {
                    System.out.println("Table groupUser not created.");
                }
            }

            // table 'users'
            rs = dbm.getTables(null, null, "users", null);
            if (!rs.next()) {
                try {
                    stmt = db.conn.createStatement();
                    sql = "CREATE TABLE users(userId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,name TEXT UNIQUE, groupUser INTEGER REFERENCES groupUser(groupId))";

                    stmt.executeUpdate(sql);
                } catch (Exception e) {
                    System.out.println("Table users not created.");
                }
            }

            // table 'issue'
            rs = dbm.getTables(null, null, "issue", null);
            if (!rs.next()) {
                try {
                    stmt = db.conn.createStatement();
                    sql = "CREATE TABLE issue (userId INT NOT NULL REFERENCES users(userId),"
                            + " bookId INT NOT NULL REFERENCES books(bookId), issueDate TEXT,dueDate TEXT ,"
                            + " PRIMARY KEY (userId, bookId))";

                    stmt.executeUpdate(sql);
                } catch (Exception e) {
                    System.out.println("Table issue not created.");
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR in Database.createTables");
        }
    }
}