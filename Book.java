

public class Book{
    private int bookId;
    private String name;
    private int available;
    private int quantity;

    Book(int id, String name, int quantity){
        this.bookId = id;
        this.name = name;
        this.quantity = quantity;
        this.available = quantity;
    }

    public int getId(){
        return bookId;
    }

    public String getName(){
        return name;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getAvailable(){
        return available;
    }

}