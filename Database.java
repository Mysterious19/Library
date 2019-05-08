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

    public void insert(User obj){
        try{
            stmt = conn.createStatement();
            sql = "INSERT INTO USERS (USERID, NAME) "+
                "VALUES(?,?)";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.UserId);
            ps.setString(2, obj.name);
            ps.executeUpdate();
            // stmt.executeUpdate(sql,(obj.UserId, obj.name));
            ps.close();
        }catch(Exception e){
            System.out.println(e+"yes");
        }
    }




}

