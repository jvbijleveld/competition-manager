package nl.vanbijleveld.cm.team;

import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.invoke.MethodHandles;
import nl.vanbijleveld.cm.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private TeamRepository teamRepo;

    public Team getTeam(long id) throws NotFoundException{
        TeamEnt ent = teamRepo.findOneById(id);
        if(ent == null){
            LOGGER.error("Team with id " + id + " is not found");
            throw new NotFoundException("Team with id " + id + " is not found");
        }
        return TeamFactory.build(ent);
    }

}