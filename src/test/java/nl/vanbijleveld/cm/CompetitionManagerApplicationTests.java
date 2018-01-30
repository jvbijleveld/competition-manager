package nl.vanbijleveld.cm;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javassist.NotFoundException;
import nl.vanbijleveld.cm.player.Player;
import nl.vanbijleveld.cm.player.PlayerService;
import nl.vanbijleveld.cm.team.Team;
import nl.vanbijleveld.cm.team.TeamService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CompetitionManagerApplication.class)
public class CompetitionManagerApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Mock
    private Player mockPlayer;

    @Mock
    private Team mockTeam;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private TeamService teamService;

    @InjectMocks
    private CompetitionManagerApplicationTests controller;

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
        given(playerService.getPlayer(1l)).willReturn(mockPlayer);
        mvc.perform(get("/player/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findPlayer_returnNotFound() throws Exception {
        given(playerService.getPlayer(1l)).willThrow(NotFoundException.class);
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
