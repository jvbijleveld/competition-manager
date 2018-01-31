package nl.vanbijleveld.cm.team;

import java.util.ArrayList;
import java.util.List;

import nl.vanbijleveld.cm.player.PlayerEnt;

public class TeamMembersEntFactory {

    public final static TeamMembersEnt build(PlayerEnt player, TeamEnt team) {
        TeamMembersEnt newMember = new TeamMembersEnt();
        newMember.setPlayer(player);
        newMember.setTeam(team);
        return newMember;
    }

    public final static List<TeamMembersEnt> build(List<PlayerEnt> players, TeamEnt team) {
        List<TeamMembersEnt> list = new ArrayList<TeamMembersEnt>();

        for (PlayerEnt player : players) {
            list.add(build(player, team));
        }
        return list;

    }

}
