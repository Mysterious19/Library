import java.sql.*;

public class TableCreation {
    private Statement stmt = null;
    private String sql = null;
    // ----------create tables (only once if table not present in database)-----
    public void createTables() {
        Database db = Database.getDbConn();
        try {
            
            DatabaseMetaData dbm = db.conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "books", null);

            if (!rs.next()) {
                try {
                    stmt = db.conn.createStatement();
                    sql = "CREATE TABLE books (bookId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT UNIQUE,"
                            + " lastIssued TEXT , quantity INT, available INT)";
                    stmt.executeUpdate(sql);

                } catch (Exception e) {
                    System.out.println(e + "\nTable books not created. ");
                }
            }

            rs = dbm.getTables(null, null, "users", null);
            if (!rs.next()) {

                try {
                    stmt = db.conn.createStatement();
                    sql = "CREATE TABLE users(userId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,name TEXT UNIQUE)";
                    stmt.executeUpdate(sql);

                } catch (Exception e) {
                    System.out.println(e.getMessage()+ "\nTable users not created.");
                }
            }

            rs = dbm.getTables(null, null, "issue", null);
            if (!rs.next()) {

                try {
                    stmt = db.conn.createStatement();
                    sql = "CREATE TABLE issue (userId INT NOT NULL REFERENCES users(userId),"
                            + " bookId INT NOT NULL REFERENCES books(bookId), issueDate TEXT,dueDate TEXT ,"
                            + " PRIMARY KEY (userId, bookId))";
                    stmt.executeUpdate(sql);

                } catch (Exception e) {
                    System.out.println(e+"\nTable issue not created.");
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR in Database.createTables");
        }

    }
}