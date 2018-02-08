package nl.vanbijleveld.cm.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UserRepository extends CrudRepository<User, String> {

    public User findOneByEmail(String email);
}
