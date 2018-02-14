package nl.vanbijleveld.cm.team;

import java.util.ArrayList;
import java.util.List;

import nl.vanbijleveld.cm.player.PlayerFactory;

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

    public static List<Team> build(Iterable<TeamEnt> list) {
        List<Team> teamList = new ArrayList<Team>();
        for (TeamEnt teamEnt : list) {
            teamList.add(build(teamEnt));
        }
        return teamList;
    }
}
