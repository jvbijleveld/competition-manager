package nl.vanbijleveld.cm.team;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.ArrayList;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.player.PlayerEnt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private TeamRepository teamRepo;

    public Team getTeam(long id) throws NotFoundException {
        TeamEnt ent = teamRepo.findOneById(id);
        if (ent == null) {
            LOGGER.error("Team with id " + id + " is not found");
            throw new NotFoundException("Team with id " + id + " is not found");
        }
        return TeamFactory.build(ent);
    }

    public Team createTeam(String name, String yell, List<PlayerEnt> players) {
        TeamEnt team = new TeamEnt();
        team.setName(name);
        team.setYell(yell);

        team.setMembers(TeamMembersEntFactory.build(players, team));

        return TeamFactory.build(teamRepo.save(team));

    }
    
    public Team createTeam(String name, String yell, PlayerEnt player) {
        List<PlayerEnt> newList = new ArrayList<PlayerEnt>();
        newList.add(player);
        return createTeam(name, yell, newList);
    }

}
