package nl.vanbijleveld.cm.team;

import nl.vanbijleveld.cm.player.PlayerFactory;

public class TeamFactory {

    public static Team build(TeamEnt entity) {
        Team team = new Team();

        team.setId(entity.getId());
        team.setName(entity.getName());
        team.setYell(entity.getYell());

        for (TeamMembersEnt member : entity.getMembers()) {
            team.addPlayer(PlayerFactory.build(member.getPlayer()));
        }

        return team;
    }

}
