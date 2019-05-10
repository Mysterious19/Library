
public class User {
    private Integer userId;
    private String name;

    //-------------Getters----------
    public Integer getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    //-----------Setters-------------
    public void setId(Integer id){
        this.userId = id;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public String toString() {
        return "User ID : " + this.userId + " Name: " + this.name;
    }
}