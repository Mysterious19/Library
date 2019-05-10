import java.sql.ResultSet;
import java.sql.SQLException;

class BookMap {
    private final String SQL_INSERT = "INSERT INTO books(name, quantity, available) VALUES (?, ?, ?)";
    private final String SQL_DELETE = "DELETE FROM books WHERE bookId = ?";
    private final String SQL_SEARCH = "SELECT bookId, name, quantity, available FROM books WHERE name = ?)";
    

    // public Book create(Book book) {
    //     Database db = Database.getDbConn();
    //     Object values[] = { book.getName(), book.getQuantity(), book.getAvailable() };
    //     Book returnBook = null;
        
    //     try {
    //         Result res = db.update(SQL_INSERT, values);
            
    //         while(res.next()) {
    //             returnBook = map(res);
    //         }
    //         return returnBook;

    //     } catch (Exception e) {
    //         System.out.println("ERROR in BookMap.create.");
    //         return null;
    //     }
    // }

    // Helpers
    // Map the row of the given ResultSet to a Book
    private Book map(ResultSet res) {
        Book book = new Book();

        try {
            book.setId(res.getInt("bookId"));
            book.setName(res.getString("name"));
            book.setQuantity(res.getInt("quantity"));
            book.setAvailable(res.getInt("available"));
        } catch (SQLException e) {
            return null;
        }

        return book;
    }
}