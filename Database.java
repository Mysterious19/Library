import java.sql.*;

class Database{
    Connection conn = null;
    String sql;
    Statement stmt = null;
    PreparedStatement ps = null;

    Database(){
        connect();
        onCreate();
    }

    private void connect(){
        
        try{
            String url = "jdbc:sqlite:inventory.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection established");
        } catch( SQLException e){
            System.out.println(e);
        }
    }

    private void onCreate() {
        try{
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, "BOOKS", null);
            if(!rs.next()){

                try{
                    stmt = conn.createStatement();
                    sql = "CREATE TABLE BOOKS "+
                        "(BOOKID INT PRIMARY KEY NOT NULL,"+
                        " NAME TEXT NOT NULL)";
                    stmt.executeUpdate(sql);
                    stmt.close();

                } catch(Exception e){
                    System.out.println(e);
                }
            }

            rs = dbm.getTables(null, null, "USERS", null);
            if(!rs.next()){

                try{
                    stmt = conn.createStatement();
                    sql = "CREATE TABLE USERS "+
                        "(USERID INT PRIMARY KEY NOT NULL,"+
                        " NAME TEXT NOT NULL)";
                    stmt.executeUpdate(sql);
                    stmt.close();
                } catch(Exception e){
                    System.out.println(e);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }

    //function overloaded - add user
    public void insert(User obj){
        try{
            stmt = conn.createStatement();
            sql = "INSERT INTO USERS (USERID, NAME) "+
                "VALUES(?,?)";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.executeUpdate();
            
            ps.close();
        }catch(Exception e){
            System.out.println(e+"yes");
        }
    }

    // function overloaded - add book
    public void insert(Book obj){
        try{
            stmt = conn.createStatement();
            sql = "INSERT INTO BOOKS (BOOKID, NAME) "+
                "VALUES(?,?)";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.executeUpdate();
            
            ps.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //remove user
    public void removeUser(int id){
        try{
            stmt = conn.createStatement();
            sql = "DELETE FROM USERS "+
                "WHERE USERID = ?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void removeBook(int id){
        try{
            stmt = conn.createStatement();
            sql = "DELETE FROM BOOKS "+
                "WHERE BOOKID = ?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

}

