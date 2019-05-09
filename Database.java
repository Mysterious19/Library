import java.sql.*;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter; 
import java.util.Date;
import java.time.LocalDate;

class Database{
    Connection conn = null;
    String sql;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

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
                        " NAME TEXT NOT NULL,"+
                        " LASTISSUED TEXT )";
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


            rs = dbm.getTables(null, null, "ISSUE", null);
            if(!rs.next()){

                try{
                    stmt = conn.createStatement();
                    sql = "CREATE TABLE ISSUE "+
                        "(USERID INT NOT NULL REFERENCES USERS(USERID),"+
                        " BOOKID INT NOT NULL REFERENCES BOOKS(BOOKID),"+
                        " ISSUEDATE TEXT, "+
                        " DUEDATE TEXT ,"+
                        " PRIMARY KEY (USERID, BOOKID))";
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.of(1998, 03, 03);
            
            sql = "INSERT INTO BOOKS (BOOKID, NAME, LASTISSUED ) "+
                "VALUES(?,?,?)";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, date.format(formatter));
            ps.executeUpdate();
            
            ps.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert(int bookId, int userId){
        try{

            sql = "INSERT INTO ISSUE (USERID, BOOKID, ISSUEDATE, DUEDATE ) "+
                "VALUES(?,?,?,?)";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.setString(3, "date(now)");
            ps.setString(4, "date(now, +10 days)");
            ps.executeUpdate();
            

            ps.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //remove user
    public void removeUser(int id){
        try{

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

    public void remove(int bookId, int userId){
        try{

            sql = "DELETE FROM ISSUE "+
                "WHERE USERID = ? and BOOKID = ?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.executeUpdate();
            ps.close();

            sql = "UPDATE BOOKS SET LASTISSUED = ? WHERE BOOKID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "1996-07-04");
            ps.setInt(2,bookId);
            ps.executeUpdate();
            ps.close();

        }catch(Exception e){
            System.out.println(e+"prob");
        }
    }

    public ArrayList<String> search(String bookName){
        ArrayList<String> bookNames = new ArrayList<>();
        try{
            sql = "SELECT * FROM BOOKS "+
                "WHERE NAME LIKE ? ";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, bookName+"%");
            rs = ps.executeQuery();
            
            while(rs.next())
                bookNames.add(rs.getString("NAME"));
            ps.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return bookNames;
    }

    public ArrayList<String> search(int days){

        LocalDate date = (LocalDate.now()).minusDays(days);
        System.out.println(date);

        ArrayList<String> bookNames = new ArrayList<>();
        try{
            sql = "SELECT * FROM BOOKS ";
            
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
          
            while(rs.next()){
                String prevString = rs.getString("LASTISSUED");
                LocalDate prev = LocalDate.parse(prevString);
                System.out.println(prev);
                if(prev.isBefore(date) == true){
                    bookNames.add(rs.getString("NAME"));
                }
            }
                
            stmt.close();
        }catch(Exception e){
            System.out.println(e+"herer");
        }

        return bookNames;
    }

}

