

public class Book{
    private int bookId;
    private String name;

    Book(int id, String name){
        this.bookId = id;
        this.name = name;
    }

    public int getId(){
        return bookId;
    }

    public String getName(){
        return name;
    }
}