package library.interfaces;

import java.util.*;
import library.entities.*;

/*
Description : issue interface to implement methods whose implemention is hidden from the user.
*/

public interface IssueInterface {
    public Issue find(Issue issue);

    public Integer create(Issue issue);

    public Integer countBooksIssuedByUser(Integer userId);

    public Integer delete(Issue issue);

    public List<Issue> listBooksByUser(Integer userId);
}
