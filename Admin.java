class Admin{
    private Database db = null;

    Admin(Database db){
        this.db = db;
    }

    // public void addBook(int id, String name){
    //     Book obj = new Book(id,name);
    //     db.insert(obj);
    // }

    public void addUser(int id, String name){
        User obj = new User(id,name);
        db.insert(obj);
    }

    // public void removeUser(int id){
    //     db.removeUser(id);
    // }

    // public void removeBook(int id){
    //     db.removeBook(id);
    // }
}