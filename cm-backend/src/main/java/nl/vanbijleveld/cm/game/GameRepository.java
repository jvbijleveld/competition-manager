package nl.vanbijleveld.cm.game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<GameEnt, Long> {

    GameEnt findOneById(long id);

}
