package nl.vanbijleveld.cm.player;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEnt, Long> {

    PlayerEnt findOneById(long id);

    PlayerEnt findOneByEmail(String email);

}
