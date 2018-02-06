package nl.vanbijleveld.cm.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javassist.NotFoundException;
import nl.vanbijleveld.cm.api.PlayerService;
import nl.vanbijleveld.cm.api.TeamService;
import nl.vanbijleveld.cm.player.EnumSex;
import nl.vanbijleveld.cm.player.Player;
import nl.vanbijleveld.cm.team.Team;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeamControllerTest {

    private Team mockTeam;
    private String newMockTeam;
    private Player mockPlayer;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TeamService teamService;

    @MockBean
    private PlayerService playerService;

    @InjectMocks
    private TeamControllerTest controller;

    @Before
    public void setup() {
        mockPlayer = new Player("firstName", "", "lastname", "EMail", EnumSex.MALE);

        Team mockTeam = new Team();
        mockTeam.setId(1);
        mockTeam.setName("TeamName");
        mockTeam.setYell("TeamYell");

        newMockTeam = "{\"name\":\"teamName\", \"yell\": \"yell\"}";
    }

    @Test
    @WithMockUser(username = "user")
    public void findTeam_returnJson() throws Exception {
        given(teamService.getTeam(1l)).willReturn(mockTeam);
        mvc.perform(get("/team/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @SuppressWarnings("unchecked")
    @Test
    @WithMockUser(username = "user")
    public void findTeam_returnNotFound() throws Exception {
        given(teamService.getTeam(1l)).willThrow(NotFoundException.class);
        mvc.perform(get("/team/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user")
    public void createTeam() throws Exception {
        given(teamService.createTeam(Matchers.anyString(), Matchers.anyString())).willReturn(mockTeam);
        mvc.perform(put("/team").contentType(MediaType.APPLICATION_JSON).content(newMockTeam)).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user")
    public void addPlayerToTeam() throws Exception {
        given(teamService.getTeam(Matchers.anyLong())).willReturn(mockTeam);
        given(playerService.getPlayer(Matchers.anyLong())).willReturn(mockPlayer);
        mvc.perform(put("/team/1/addPlayer/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @SuppressWarnings("unchecked")
    @Test
    @WithMockUser(username = "user")
    public void addPlayerNotFound() throws Exception {
        given(teamService.addPlayer(Mockito.anyLong(), Mockito.anyLong())).willThrow(NotFoundException.class);
        mvc.perform(put("/team/1/addPlayer/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

}
