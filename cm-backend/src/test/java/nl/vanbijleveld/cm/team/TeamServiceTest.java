package nl.vanbijleveld.cm.team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.api.PlayerService;
import nl.vanbijleveld.cm.player.Player;
import nl.vanbijleveld.cm.player.PlayerEnt;
import nl.vanbijleveld.cm.player.PlayerFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceTest {

    private List<PlayerEnt> mockPlayers;
    private PlayerEnt mockPlayerEnt;
    private Player mockPlayer;
    private TeamEnt mockTeamEnt;

    @Mock
    private TeamRepository teamRepo;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockPlayerEnt = new PlayerEnt();
        mockPlayerEnt.setFirstName("first");
        mockPlayerEnt.setInfix("infix");
        mockPlayerEnt.setLastName("lastName");
        mockPlayerEnt.setEmail("email");

        mockPlayer = PlayerFactory.build(mockPlayerEnt);

        mockPlayers = new ArrayList<PlayerEnt>();
        mockPlayers.add(mockPlayerEnt);

        mockTeamEnt = new TeamEnt();
        mockTeamEnt.setName("teamName");
        mockTeamEnt.setYell("Yell");
        mockTeamEnt.addMember(TeamMembersEntFactory.build(mockPlayerEnt, mockTeamEnt));
    }

    @Test
    public void getTeamTest() throws NotFoundException {
        when(teamRepo.findOneById(Matchers.anyLong())).thenReturn(mockTeamEnt);
        final Team team = teamService.getTeam(1l);
        assertNotNull(team);
    }

    @Test
    public void testTeam() {
        Team team;
        mockTeamEnt = new TeamEnt();
        mockTeamEnt.setName("teamName");
        mockTeamEnt.setYell("Yell");
        mockTeamEnt.addMember(TeamMembersEntFactory.build(mockPlayerEnt, mockTeamEnt));
        team = TeamFactory.build(mockTeamEnt);
        assertEquals(team.getPlayers().size(), mockPlayers.size());

        mockTeamEnt.setMembers(TeamMembersEntFactory.build(mockPlayers, mockTeamEnt));
        team = TeamFactory.build(mockTeamEnt);
        assertEquals(team.getPlayers().size(), mockPlayers.size());
        assertEquals(team.getName(), mockTeamEnt.getName());
        assertEquals(team.getYell(), mockTeamEnt.getYell());
    }

    @Test(expected = NotFoundException.class)
    public void getTeamNotFoundTest() throws NotFoundException {
        when(teamRepo.findOneById(Matchers.anyLong())).thenReturn(null);
        final Team team = teamService.getTeam(1l);
        assertNull(team);
    }

    @Test
    public void createTeamTest() {
        when(teamRepo.save(Matchers.any(TeamEnt.class))).thenReturn(mockTeamEnt);
        Team team = teamService.createTeam("teamName", "Yell", mockPlayers);
        assertEquals("teamName", team.getName());
        assertEquals("Yell", team.getYell());
        assertEquals(team.getPlayers().size(), mockPlayers.size());
    }

    @Test
    public void addPlayerTest() throws NotFoundException {
        when(teamRepo.findOneById(Matchers.anyLong())).thenReturn(mockTeamEnt);
        when(playerService.getPlayer(Matchers.anyLong())).thenReturn(mockPlayer);
        int expectedTeamSize = mockTeamEnt.getMembers().size() + 1;
        Team team = teamService.addPlayer(1l, 1l);

        assertEquals(team.getPlayers().size(), expectedTeamSize);
    }

    @Test(expected = NotFoundException.class)
    public void addPlayerTestPLayerNotFound() throws NotFoundException {
        when(teamRepo.findOneById(Matchers.anyLong())).thenReturn(mockTeamEnt);
        when(playerService.getPlayer(Matchers.anyLong())).thenThrow(new NotFoundException("player not found"));
        teamService.addPlayer(1l, 1l);
    }
}
