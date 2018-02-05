package nl.vanbijleveld.cm.users;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    public User findOneByEmail(String email);
}
