class User{
    private int UserId;
    private String name;

    User(int id, String name){
        this.UserId = id;
        this.name = name;
    }

    public int getId(){
        return UserId;
    }

    public String getName(){
        return name;
    }
}