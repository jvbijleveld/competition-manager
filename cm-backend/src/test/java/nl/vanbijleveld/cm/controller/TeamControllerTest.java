package nl.vanbijleveld.cm.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class TeamControllerTest {

    private Team mockTeam;
    private Player mockPlayer;

    @Autowired
    private WebApplicationContext context;

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

        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        mockPlayer = new Player("firstName", "", "lastname", "EMail", EnumSex.MALE);

        Team mockTeam = new Team();
        mockTeam.setId(1);
        mockTeam.setName("TeamName");
        mockTeam.setYell("TeamYell");
        mockTeam.addPlayer(mockPlayer);

    }

    @Test
    @WithMockUser(username = "admin")
    public void findTeam_returnJson() throws Exception {
        given(teamService.getTeam(Matchers.anyLong())).willReturn(mockTeam);
        mvc.perform(get("/teams/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    /*
     * @SuppressWarnings("unchecked")
     * 
     * @Test(expected = NotFoundException.class)
     * 
     * @WithMockUser(username = "admin") public void findTeam_returnNotFound() throws Exception {
     * given(teamService.getTeam(Matchers.anyLong())).willThrow(NotFoundException.class);
     * mvc.perform(get("/team/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()); }
     */

    // @Test
    @WithMockUser(username = "admin")
    public void createTeam() throws Exception {
        given(teamService.createTeam(Matchers.anyString(), Matchers.anyString())).willReturn(mockTeam);
        mvc.perform(put("/teams").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(mockTeam))).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin")
    public void addPlayerToTeam() throws Exception {
        given(teamService.getTeam(Matchers.anyLong())).willReturn(mockTeam);
        given(playerService.getPlayer(Matchers.anyLong())).willReturn(mockPlayer);
        mvc.perform(put("/teams/1/addPlayer/1").with(csrf()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    /*
     * @SuppressWarnings("unchecked")
     * 
     * @Test(expected = NotFoundException.class)
     * 
     * @WithMockUser(username = "admin") public void addPlayerNotFound() throws Exception {
     * given(teamService.addPlayer(Mockito.anyLong(), Mockito.anyLong())).willThrow(NotFoundException.class);
     * mvc.perform
     * (put("/team/1/addPlayer/1").with(csrf()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound
     * ()); }
     */

}
