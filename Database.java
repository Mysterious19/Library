import java.sql.*;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDate;

//a singleton database access class 
class Database {
    public Connection conn = null;
    private PreparedStatement ps = null;
    
    public static Database db = new Database();
    
    //----------constructor----------
    private Database() {
        try {
            String url = "jdbc:sqlite:inventory.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection established");

        } catch (SQLException e) {
            System.out.println("Connection issue");
        }
    }

    public static Database getDbConn() {
        return db;
    }

    //------------------query operation-----------------
    public ResultSet query(String query, Object values[]) {
        try {
            ps = db.conn.prepareStatement(query);

            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
            }

            ResultSet res = ps.executeQuery();
            return res;
        } catch (SQLException e) {
            return null;
        }
    }

    // --------update operation - (create, update, delete operation in db)---
    public Integer update(String query, Object values[]) throws SQLException {
        ps = db.conn.prepareStatement(query);

        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }

        Integer res = ps.executeUpdate();
        return res;
    }

    

}
