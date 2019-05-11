
public interface UserInterface{
    public User find(Integer id);
    public User find(String name);
    // public List<User> list();
    public Integer create(User user);
    // public void update(User user);
    // public Boolean delete(User user);
}