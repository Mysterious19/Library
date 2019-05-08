import java.sql.*;

class Database{
    Connection conn = null;
    String sql;
    Statement stmt = null;

    Database(){
        connect();
        onCreate();
    }

    public void connect(){
        
        try{
            String url = "jdbc:sqlite:inventory.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection established");
        } catch( SQLException e){
            System.out.println(e);
        }
    }

    public void onCreate(){
        try{
            stmt = conn.createStatement();
            sql = "CREATE TABLE BOOKS "+
                "(BOOKID INT PRIMARY KEY NOT NULL,"+
                " NAME TEXT NOT NULL)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE USERS "+
                "(USERID INT PRIMARY KEY NOT NULL,"+
                " NAME TEXT NOT NULL)";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert(Book obj){
        ;
    }


}

