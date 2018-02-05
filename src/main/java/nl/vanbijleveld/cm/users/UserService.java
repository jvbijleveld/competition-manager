package nl.vanbijleveld.cm.users;

public interface UserService {

    public void save(User user);

    public User findByUsername(String username);

}
