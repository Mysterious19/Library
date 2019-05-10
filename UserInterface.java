
public interface UserInterface{
    public User find(Integer id);
    public User find(String name);
    // public List<User> list();
    public User create(User user);
    // public void update(User user);
    public Boolean delete(User user);
}