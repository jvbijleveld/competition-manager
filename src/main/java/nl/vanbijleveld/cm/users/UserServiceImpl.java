package nl.vanbijleveld.cm.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {

        // user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findOneByEmail(username);
    }

}
