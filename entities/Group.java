package library.entities;

/*
Description : Group class to create instance for the corresponding record in Group table 
Student : 10 days and max 3 books at a time
Staff : 20 days and max 5 books at a time
Faculty : 30 days and max 10 books at a time
*/

public class Group {
    private Integer groupId;
    private String groupName;
    private Integer maxTime;
    private Integer maxBooks;

    //-----------getters-----------
    public Integer getGroupId(){
        return groupId;
    }

    public String getGroupName(){
        return groupName;
    }

    public Integer getMaxTime(){
        return maxTime;
    }

    public Integer getMaxBooks(){
        return maxBooks;
    }

    //------setters-------
    public void setGroupId(Integer id){
         this.groupId = id;
    }

    public void setGroupName(String name){
         this.groupName = name;
    }

    public void setMaxTime(Integer time){
         this.maxTime = time;
    }

    public void setMaxBooks(Integer books){
         this.maxBooks = books;
    }
}