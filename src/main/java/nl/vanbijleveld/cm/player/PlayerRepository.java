package nl.vanbijleveld.cm.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEnt, Long> {

    PlayerEnt findOneById(long id);

    PlayerEnt findOneByEmail(String email);

}