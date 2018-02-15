package nl.vanbijleveld.cm.team;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<TeamEnt, Long> {

    TeamEnt findOneById(long id);

    List<TeamEnt> findByMembersPlayerIdIs(long playerId);

}
