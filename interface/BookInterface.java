import java.util.*;

public interface BookInterface {
    public Integer create(Book book);

    // public Boolean delete(Integer id);
    public Integer update(String date, Integer bookId);
    public List<Book> search(String name);
}