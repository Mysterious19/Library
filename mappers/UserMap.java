package library.mappers;

import library.entities.*;
import library.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
Description : Mapper class to map User class object to Database query and return data 
in User class instance.
*/

public class UserMap{
    private final String SQL_FIND_ID = "SELECT userId, name, groupUser FROM users WHERE userId = ?";
    private final String SQL_FIND_name = "SELECT userId, name, groupUser FROM users WHERE name = ?";
    private final String SQL_LIST_BY_ID = "SELECT userId, name, groupUser FROM users ORDER BY userId";
    private final String SQL_INSERT = "INSERT INTO users (name, groupUser) VALUES (?,?)";
    private final String SQL_UPDATE = "UPDATE users SET name = ? WHERE userId = ?";
    private final String SQL_DELETE = "DELETE FROM users WHERE userId = ?";

    // --------find user by id--------------
    public User find(Integer id) {
        User user = null;
        Object values[] = { id };
        Database db = Database.getDbConn();

        ResultSet res = db.query(SQL_FIND_ID, values);

        try {
            if (res != null) {
                
                while (res.next()) {
                    user = map(res);
                }

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    // ----------find user by name-----------
    public User find(String name) {
        User user = null;
        Object values[] = { name };
        Database db = Database.getDbConn();

        ResultSet res = db.query(SQL_FIND_name, values);

        try {
            if (!res.next()) {
                return null;
            }
            
            do {
                user = map(res);
            } while (res.next());

            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    // ------create new user and return user id------------
    public Integer create(User user) {
        Object values[] = { user.getName(), user.getGroup() };
        Database db = Database.getDbConn();

        try {
            Integer res = db.update(SQL_INSERT, values);

            if (res == 0) {
                System.out.println("ERROR in UserMap.create : res = 0");
                return null;
            }
            return res;
        } catch (Exception e) {
            System.out.println("ERROR in UserMap.create : Unknown.");
            return null;
        }
    }

    // --------delete user by id----------
    public Boolean delete(Integer id) {
        Object values[] = { id };
        Database db = Database.getDbConn();

        try {
            Integer res = db.update(SQL_DELETE, values);

            if (res == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("ERROR in UserMap.delete : res");
            return false;
        }
    }

    //--------------Helpers-------------
    // Map the row of the given ResultSet to an User
    private User map(ResultSet res) {
        User user = new User();

        try {
            user.setId(res.getInt("userId"));
            user.setName(res.getString("name"));
            user.setGroup(res.getInt("groupUser"));
        } catch (SQLException e) {
            return null;
        }
        return user;
    }
}