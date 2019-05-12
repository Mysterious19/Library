package library.interfaces;

import java.util.*;
import library.entities.*;

/*
Description : book interface to implement methods whose implemention is hidden from the user.
*/

public interface BookInterface {
    public Integer create(Book book);

    public List<Book> search(String name);

    public Book findById(Integer id);

    public Integer update(Book book);

    public Integer delete(Integer id);

    public List<Book> unusedBooksOperation(Object values[]);
}