package nl.vanbijleveld.cm.team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import nl.vanbijleveld.cm.player.PlayerEnt;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TeamTests {

    private TeamEnt mockTeamEnt;
    private Team mockTeam;
    private PlayerEnt mockPlayerEnt;

    @Mock
    private TeamRepository teamRepo;

    @Mock
    private TeamFactory teamFactory;

    @InjectMocks
    private TeamTests teamtests;

    @Before
    public void setup() {
        mockPlayerEnt = new PlayerEnt();
        mockPlayerEnt.setFirstName("first");
        mockPlayerEnt.setLastName("lastName");
        mockPlayerEnt.setEmail("email");

        mockTeamEnt = new TeamEnt();
        mockTeamEnt.setId(1l);
        mockTeamEnt.setName("TeamName");
        mockTeamEnt.setYell("TeamYell");

        TeamMembersEnt mockMember = new TeamMembersEnt();
        mockMember.setId(1l);
        mockMember.setPlayer(mockPlayerEnt);
        mockMember.setTeam(mockTeamEnt);

        List<TeamMembersEnt> list = new ArrayList<TeamMembersEnt>();
        list.add(mockMember);

        mockTeamEnt.setMembers(list);
    }

    @Test
    public void testTeamFactory() {
        mockTeam = TeamFactory.build(mockTeamEnt);

        assertEquals(mockTeam.getId(), mockTeamEnt.getId());
        assertEquals(mockTeam.getName(), mockTeamEnt.getName());
        assertEquals(mockTeam.getYell(), mockTeamEnt.getYell());
        assertNotNull(mockTeam.getPlayers());
    }

}
