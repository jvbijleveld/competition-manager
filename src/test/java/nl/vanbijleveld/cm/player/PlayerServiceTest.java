package nl.vanbijleveld.cm.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import javassist.NotFoundException;
import nl.vanbijleveld.cm.exception.ConflictException;
import nl.vanbijleveld.cm.team.Team;
import nl.vanbijleveld.cm.team.TeamService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTest {

    private Player mockPlayer;
    private Team mockTeam;

    @Mock
    private PlayerRepository playerRepo;

    @Mock
    private TeamService teamService;

    @Mock
    private PlayerEnt mockPlayerEnt;

    @InjectMocks
    private PlayerService playerService;

    @Before
    public void setup() {
        mockPlayer = new Player("first", "", "last", "email", EnumSex.MALE);
        mockPlayerEnt = new PlayerEnt();
        mockPlayerEnt.setFirstName("first");
        mockPlayerEnt.setInfix("");
        mockPlayerEnt.setLastName("last");
        mockPlayerEnt.setEmail("email");
        mockPlayerEnt.setSex(EnumSex.MALE);

        mockTeam = new Team();
        mockTeam.setName("TeamName");
        mockTeam.setYell("Yell");
    }

    @Test
    public void getPlayerTest() throws NotFoundException {
        when(playerRepo.findOneById(Matchers.anyLong())).thenReturn(mockPlayerEnt);
        final Player player = playerService.getPlayer(1l);
        assertNotNull(player);
    }

    @Test(expected = NotFoundException.class)
    public void getPlayerNotFoundTest() throws NotFoundException {
        when(playerRepo.findOneById(Matchers.anyLong())).thenReturn(null);
        final Player player = playerService.getPlayer(1l);
        assertNull(player);
    }

    @Test
    public void testCreatePlayer() throws ConflictException {
        when(playerRepo.findOneByEmail(Matchers.anyString())).thenReturn(null);
        when(playerRepo.save(Matchers.any(PlayerEnt.class))).thenReturn(mockPlayerEnt);
        when(teamService.createTeam(Matchers.anyString(), Matchers.anyString(), Matchers.any(PlayerEnt.class))).thenReturn(mockTeam);
        final Player player = playerService.createNewPlayer(mockPlayer);
        assertEquals(mockPlayer.getFirstName(), player.getFirstName());
        assertEquals(mockPlayer.getInfix(), player.getInfix());
        assertEquals(mockPlayer.getLastName(), player.getLastName());
        assertEquals(mockPlayer.getEmail(), player.getEmail());
        assertEquals(mockPlayer.getSex(), player.getSex());
    }

    @Test(expected = ConflictException.class)
    public void testCreateDuplicatePlayer() throws ConflictException {
        when(playerRepo.findOneByEmail(Matchers.anyString())).thenReturn(mockPlayerEnt);
        final Player player = playerService.createNewPlayer(mockPlayer);
        assertNull(player);
    }

}
