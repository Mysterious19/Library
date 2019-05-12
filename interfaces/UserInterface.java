package library.interfaces;

import library.entities.*;
import java.util.*;

/*
Description : user interface to implement methods whose implemention is hidden from the user.
*/

public interface UserInterface {
    public User find(Integer id);

    public User find(String name);

    public Integer create(User user);

    public Boolean delete(Integer id);
}