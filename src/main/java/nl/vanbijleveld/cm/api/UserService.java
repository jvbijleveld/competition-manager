package nl.vanbijleveld.cm.api;

import nl.vanbijleveld.cm.users.User;

public interface UserService {

    public void save(User user);

    public User findByUsername(String username);

}
