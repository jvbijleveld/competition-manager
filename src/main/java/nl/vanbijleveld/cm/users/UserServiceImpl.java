package nl.vanbijleveld.cm.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        // user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // user.setRole(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findOneByEmail(username);
    }
}