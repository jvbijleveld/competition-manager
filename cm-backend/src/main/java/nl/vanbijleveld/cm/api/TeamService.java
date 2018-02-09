package nl.vanbijleveld.cm.api;

import java.util.List;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.player.PlayerEnt;
import nl.vanbijleveld.cm.team.Team;

public interface TeamService {

    Team getTeam(long id) throws NotFoundException;
    
    List<Team> listTeams();
    
    List<Team> getAllTeamsByPlayer(long playerId);

    Team addPlayer(long teamId, long playerId) throws NotFoundException;

    Team createTeam(String name, String yell);

    Team createTeam(String name, String yell, PlayerEnt player);

    Team createTeam(String name, String yell, List<PlayerEnt> players);

}
