package nl.vanbijleveld.cm.team;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    public Team getTeam(long id) throws NotFoundException{
        TeamEnt ent = teamRepo.findOneById(id);
        if(ent == null){
            System.out.println("Team with id " + id + " is not found");
            throw new NotFoundException("Team with id " + id + " is not found");
        }
        return TeamFactory.build(ent);
    }

}