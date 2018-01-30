package nl.vanbijleveld.cm;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javassist.NotFoundException;
import nl.vanbijleveld.cm.player.Player;
import nl.vanbijleveld.cm.player.PlayerService;
import nl.vanbijleveld.cm.team.Team;
import nl.vanbijleveld.cm.team.TeamService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CompetitionManagerApplication.class)
public class CompetitionManagerApplicationTests {

    private Player mockPlayer;
    private Team mockTeam;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private TeamService teamService;

    @InjectMocks
    private CompetitionManagerApplicationTests controller;

    @Before
    public void setup() {
        Player mockPlayer = new Player();
        mockPlayer.setFirstName("firstName");
        mockPlayer.setLastName("lastname");
        mockPlayer.setEmail("EMail");

        Team mockTeam = new Team();
        mockTeam.setId(1);
        mockTeam.setName("TeamName");
        mockTeam.setYell("TeamYell");
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRootContext() throws Exception {
        mvc.perform(get("/").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());
    }

    // PLAYERS
    @Test
    public void findPlayer_returnJson() throws Exception {
        given(playerService.getPlayer(Mockito.anyLong())).willReturn(mockPlayer);
        mvc.perform(get("/player/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findPlayer_returnNotFound() throws Exception {
        given(playerService.getPlayer(Mockito.anyLong())).willThrow(NotFoundException.class);
        mvc.perform(get("/player/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    // TEAMS
    @Test
    public void findTeam_returnJson() throws Exception {
        given(teamService.getTeam(1l)).willReturn(mockTeam);
        mvc.perform(get("/team/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findTeam_returnNotFound() throws Exception {
        given(teamService.getTeam(1l)).willThrow(NotFoundException.class);
        mvc.perform(get("/team/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

}
