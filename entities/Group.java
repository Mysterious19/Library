package library.entities;

/*
Description : Group class to create instance for the corresponding record in Group table 
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