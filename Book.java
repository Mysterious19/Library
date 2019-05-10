
public class Book {
    private Integer bookId;
    private String name;
    private Integer available;
    private Integer quantity;

    // ----------getters-----------
    public Integer getId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getAvailable() {
        return available;
    }

    // -------setters----------
    public void setId(Integer id) {
        this.bookId = id;
        ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String toString() {
        return "Book Id : " + bookId + " Name : " + name;
    }
}