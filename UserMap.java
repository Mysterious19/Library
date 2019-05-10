import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMap {
    private final String SQL_FIND_ID = "SELECT userID, name FROM users WHERE userID = ?";
    private final String SQL_FIND_name = "SELECT userID, name FROM users WHERE name = ?";
    private final String SQL_LIST_BY_ID = "SELECT userID, name FROM users ORDER BY userID";
    private final String SQL_INSERT = "INSERT INTO users (name) VALUES (?)";
    private final String SQL_UPDATE = "UPDATE users SET name = ? WHERE userID = ?";
    private final String SQL_DELETE = "DELETE FROM users WHERE userID = ?";

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

    // ------create new user and return user id------------
    public User create(User user) {
        Object values[] = { user.getName() };
        System.out.println(Database.getDbConn());
        Database db = Database.getDbConn();
        
        User returnUser = null;

        try {
            ResultSet existingUsers = db.query(SQL_FIND_name, values);
            // System.out.print(existingUsers);
            if (existingUsers.next()) {
                return null;
            }

            Integer res = db.update(SQL_INSERT, values);
            System.out.println(res);
            if (res == 0) {
                System.out.println("ERROR in UserMap.create : res = 0");
                return null;
            }

            ResultSet resultSet = db.query(SQL_FIND_name, values);

            if (resultSet != null) {
                while (resultSet.next()) {
                    returnUser = map(resultSet);
                }

                return returnUser;
            } else {
                System.out.println("ERROR in UserMap.create : resultSet = null");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            // System.out.println("ERROR in UserMap.create : Unknown.");
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

    // Helpers
    // Map the row of the given ResultSet to an User
    private User map(ResultSet res) {
        User user = new User();

        try {
            user.setId(res.getInt("userID"));
            user.setName(res.getString("name"));
        } catch (SQLException e) {
            return null;
        }

        return user;
    }
}