package library.entities;

public class User {
    private Integer userId;
    private String name;
    private Integer group;

    //-------------Getters----------
    public Integer getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Integer getGroup(){
        return group;
    }
    //-----------Setters-------------
    public void setId(Integer id){
        this.userId = id;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }
    
    public String toString() {
        return "User ID : " + this.userId + " Name: " + this.name;
    }
}