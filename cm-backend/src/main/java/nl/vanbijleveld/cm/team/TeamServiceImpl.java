package nl.vanbijleveld.cm.team;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.ArrayList;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.api.PlayerService;
import nl.vanbijleveld.cm.api.TeamService;
import nl.vanbijleveld.cm.player.Player;
import nl.vanbijleveld.cm.player.PlayerEnt;
import nl.vanbijleveld.cm.player.PlayerEntWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private TeamRepository teamRepo;
    
    @Autowired
    private PlayerService playerService;

    public Team getTeam(long id) throws NotFoundException {
        return TeamFactory.build(fetchTeam(id));
    }
    
    private TeamEnt fetchTeam(long id) throws NotFoundException{
    	TeamEnt ent = teamRepo.findOneById(id);
        if (ent == null) {
            LOGGER.error("Team with id " + id + " is not found");
            throw new NotFoundException("Team with id " + id + " is not found");
        }
        return ent;
    }
    
    public Team addPlayer(long teamId, long playerId) throws NotFoundException{
    	TeamEnt team  = fetchTeam(teamId);
    	Player player = playerService.getPlayer(playerId);
    	TeamMembersEnt memberEnt = new TeamMembersEnt();
    	memberEnt.setPlayer(PlayerEntWrapper.wrap(player));
    	memberEnt.setTeam(team);
    	team.addMember(memberEnt);
    	teamRepo.save(team);
    	return TeamFactory.build(team);
    }

    public Team createTeam(String name, String yell){
    	TeamEnt newTeam = buildTeam(name, yell);
    	return TeamFactory.build(teamRepo.save(newTeam));
    }
    
    public Team createTeam(String name, String yell, List<PlayerEnt> players) {
    	TeamEnt newTeam = buildTeam(name, yell);
    	newTeam.setMembers(TeamMembersEntFactory.build(players, newTeam));
    	return TeamFactory.build(teamRepo.save(newTeam));
    }
    
    public Team createTeam(String name, String yell, PlayerEnt player) {
        List<PlayerEnt> newList = new ArrayList<PlayerEnt>();
        newList.add(player);
        return createTeam(name, yell, newList);
    }

    private TeamEnt buildTeam(String name, String yell){
    	TeamEnt team = new TeamEnt();
        team.setName(name);
        team.setYell(yell);
        
        return team;
    }

	public List<Team> listTeams() {
		 return TeamFactory.build(teamRepo.findAll());
	}

	@Override
	public List<Team> getAllTeamsByPlayer(long playerId) {
		// TODO Auto-generated method stub
		return null;
	}
   
}
