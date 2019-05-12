package library.entities;

/*
Description : Book class to create instance for corresponding record in books table
*/

public class Book {
    private Integer bookId;
    private String name;
    private Integer available;
    private Integer quantity;
    private String lastIssue;

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

    public String getLastIssue() {
        return lastIssue;
    }

    // -------setters----------
    public void setId(Integer id) {
        this.bookId = id;
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

    public void setLastIssue(String lastIssue) {
        this.lastIssue = lastIssue;
    }
    public String toString() {
        return "\nBook Id : " + bookId + "\nName : " + name + "\nLast Used :" + lastIssue + "\nAvailable : " + available;
    }
}