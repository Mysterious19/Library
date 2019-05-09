import java.sql.*;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDate;

//database class for various database related methods
class Database {
    Connection conn = null;
    String sql;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    Database() {
        connect();
        onCreate();
    }

    // set up connection
    private void connect() {

        try {
            String url = "jdbc:sqlite:inventory.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection established");
        } catch (SQLException e) {
            System.out.println(e + " connection issue");
        }
    }

    // create tables
    private void onCreate() {
        try {
            // search for tables, if already created or not
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "BOOKS", null);
            if (!rs.next()) {

                // create new table - BOOKS
                try {
                    stmt = conn.createStatement();
                    sql = "CREATE TABLE BOOKS " + "(BOOKID INT PRIMARY KEY NOT NULL," + " NAME TEXT NOT NULL,"
                            + " LASTISSUED TEXT ," + " QUANTITY INT ," + " AVAIL INT)";
                    stmt.executeUpdate(sql);
                    stmt.close();

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            // table -USERS
            rs = dbm.getTables(null, null, "USERS", null);
            if (!rs.next()) {

                try {
                    stmt = conn.createStatement();
                    sql = "CREATE TABLE USERS " + "(USERID INT PRIMARY KEY NOT NULL," + " NAME TEXT NOT NULL)";
                    stmt.executeUpdate(sql);
                    stmt.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            // table - ISSUE
            rs = dbm.getTables(null, null, "ISSUE", null);
            if (!rs.next()) {

                try {
                    stmt = conn.createStatement();
                    sql = "CREATE TABLE ISSUE " + "(USERID INT NOT NULL REFERENCES USERS(USERID),"
                            + " BOOKID INT NOT NULL REFERENCES BOOKS(BOOKID)," + " ISSUEDATE TEXT, " + " DUEDATE TEXT ,"
                            + " PRIMARY KEY (USERID, BOOKID))";
                    stmt.executeUpdate(sql);
                    stmt.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // function overloaded - add user
    public void insert(User obj) {
        try {

            sql = "INSERT INTO USERS (USERID, NAME) " + "VALUES(?,?)";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            System.out.println(e + "yes");
        }
    }

    // function overloaded - add book
    public void insert(Book obj) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.of(1998, 03, 03);

            sql = "INSERT INTO BOOKS (BOOKID, NAME, LASTISSUED, QUANTITY, AVAIL ) " + "VALUES(?,?,?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, date.format(formatter));
            ps.setInt(4, obj.getQuantity());
            ps.setInt(5, obj.getAvailable());
            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // issue book method
    public void insert(int bookId, int userId) {
        try {

            //check for availability of the book
            sql = "SELECT AVAIL FROM BOOKS WHERE BOOKID = ?";
            int newQuantity = -1;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("AVAIL") > 0) {
                    newQuantity = rs.getInt("AVAIL") - 1;

                }
            }
            // System.out.println(newQuantity);
            ps.close();

            //if available - add to issue table and update availability in book table
            if (newQuantity >= 0) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.now();
                LocalDate dueDate = date.plusDays(10);

                sql = "INSERT INTO ISSUE (USERID, BOOKID, ISSUEDATE, DUEDATE ) " + "VALUES(?,?,?,?)";

                ps = conn.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, bookId);
                ps.setString(3, date.format(formatter));
                ps.setString(4, dueDate.format(formatter));
                ps.executeUpdate();

                ps.close();

                sql = "UPDATE BOOKS SET AVAIL = ?,LASTISSUED = ? WHERE BOOKID = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, newQuantity);
                ps.setString(2, date.format(formatter));
                ps.setInt(3, bookId);
                ps.executeUpdate();
                ps.close();
            } else {
                System.out.println("Not Available");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // remove user
    public void removeUser(int id) {
        try {

            sql = "DELETE FROM USERS " + "WHERE USERID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //remove book by the admin
    public void removeBook(int id) {
        try {
            //if available and not issued by any user
            int newQuantity = 0;
            sql = "SELECT QUANTITY, AVAIL FROM BOOKS WHERE BOOKID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            int newAvail = rs.getInt("AVAIL") - 1;
            while (rs.next()) {
                if (rs.getInt("AVAIL") == 0) {
                    System.out.println("Not available - wait for the user to return the book");
                    return;
                }
                if (rs.getInt("QUANTITY") > 1) {
                    newQuantity = rs.getInt("QUANTITY") - 1;

                }
            }
            ps.close();

            
            if (newQuantity == 0) {
                sql = "DELETE FROM BOOKS " + "WHERE BOOKID = ?";

                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();

                ps.close();
                System.out.println("Book successully removed.");
            } else {
                sql = "UPDATE BOOKS SET QUANTITY = ?, AVAIL = ? WHERE BOOKID = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, newQuantity);
                ps.setInt(2, newAvail);
                ps.setInt(3, id);
                ps.close();
                System.out.println("One copy of the book removed.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //returning book 
    public void removeReturn(int bookId, int userId) {
        try {

            int newAvail = 0;
            sql = "SELECT AVAIL FROM BOOKS WHERE BOOKID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();
            while (rs.next()) {
                newAvail = rs.getInt("AVAIL") + 1;
            }
            System.out.println(newAvail);
            ps.close();

            //remove from issue table
            sql = "DELETE FROM ISSUE " + "WHERE USERID = ? and BOOKID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.executeUpdate();
            ps.close();

            //update last issued and avail
            LocalDate updateDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            sql = "UPDATE BOOKS SET LASTISSUED = ?, AVAIL = ? WHERE BOOKID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, updateDate.format(formatter));
            ps.setInt(2, newAvail);
            ps.setInt(3, bookId);
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println(e + "prob");
        }
    }

    //search similar prefix-books
    public void searchSimilar(String bookName) {

        try {
            sql = "SELECT * FROM BOOKS " + "WHERE NAME LIKE ? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, bookName + "%");
            rs = ps.executeQuery();
            String tag = "NOT AVAIL";
            String nowAvail = "";
            //display availability of the books
            while (rs.next()) {
                if (rs.getInt("AVAIL") > 0) {
                    tag = "AVAIL";
                } else {
                    tag = "NOT AVAIL";
                    //if not available - display by when will it be available
                    try {
                        String sql1 = "SELECT DUEDATE FROM ISSUE WHERE BOOKID IN (SELECT BOOKID FROM BOOKS WHERE NAME = ?)";
                        PreparedStatement ps1 = conn.prepareStatement(sql1);
                        ps1.setString(1, rs.getString("NAME"));
                        ResultSet rs1 = ps1.executeQuery();
                         nowAvail = rs1.getString("DUEDATE");
                        while (rs1.next()) {
                            if (nowAvail.compareTo(rs1.getString("DUEDATE")) > 0)
                                nowAvail = rs1.getString("DUEDATE");
                        }
                        // System.out.print("Will be avail by : "+ nowAvail+"\t");
                        ps1.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                System.out.println(rs.getString("NAME") + " " + tag+ " "+nowAvail);
              
            }
            ps.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //query for unused books over a period of time
    public ArrayList<String> searchUnused(int days) {

        LocalDate date = (LocalDate.now()).minusDays(days);
        System.out.println(date);

        ArrayList<String> bookNames = new ArrayList<>();
        try {
            sql = "SELECT * FROM BOOKS ";

            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String prevString = rs.getString("LASTISSUED");
                LocalDate prev = LocalDate.parse(prevString);
                // System.out.println(prev);
                if (prev.isBefore(date) == true) {
                    bookNames.add(rs.getString("NAME"));
                }
            }

            stmt.close();
        } catch (Exception e) {
            System.out.println(e + "herer");
        }

        return bookNames;
    }

}
