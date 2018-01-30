package nl.vanbijleveld.cm.team;

import nl.vanbijleveld.cm.TeamEnt;
import nl.vanbijleveld.cm.Player;

public class TeamFactory {

    public static Team build(TeamEnt entity) {
        Team team = new Team();

        team.setId(entity.getId());
        team.setName(entity.getName());
        team.setYell(entity.getYell());
        
        
        
        
        
        
        return team;
    }

}
