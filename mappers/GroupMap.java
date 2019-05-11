package library.mappers;

import java.sql.*;
import library.*;
import library.entities.*;

/*
Description : Mapper class to map Group class object to Database query and return data 
in Group class instance.
*/

public class GroupMap {
    private final String SQL_FIND = "SELECT * FROM groupUser WHERE groupId = ?";

    // find Group details by id
    public Group find(Integer id) {
        Database db = Database.getDbConn();
        Object values[] = {id};
        Group group = null;
        
        ResultSet res = db.query(SQL_FIND, values);

        try {
        
            while(res.next()) {
                group = new Group();
                group.setGroupName(res.getString("groupName"));
                group.setMaxTime(res.getInt("maxTime"));
                group.setMaxBooks(res.getInt("maxBooks"));
            }
        
            return group;
        } catch (Exception e) {
            System.out.println("No such group in GroupMap");
            return null;
        }
    }
}