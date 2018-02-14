package nl.vanbijleveld.cm.team;

import nl.vanbijleveld.cm.player.PlayerFactory;
import java.util.List;
import java.util.ArrayList;

public class TeamFactory {

    public static Team build(TeamEnt entity) {
        Team team = new Team();
        team.setId(entity.getId());
        team.setName(entity.getName());
        team.setYell(entity.getYell());

        if (entity.getMembers() != null) {
            for (TeamMembersEnt member : entity.getMembers()) {
                team.addPlayer(PlayerFactory.build(member.getPlayer()));
            }
        }
        return team;
    }
    
    public static List<Team> build(List<TeamEnt> list){
        List<Team> teamList = new ArrayList();
        for(TeamEnt teamEnt : list){
            teamList.add(this.build(teamEnt));
        }
        return teamList;
    }
}
