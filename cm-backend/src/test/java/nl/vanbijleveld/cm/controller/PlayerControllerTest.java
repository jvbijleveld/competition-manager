package nl.vanbijleveld.cm.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javassist.NotFoundException;
import nl.vanbijleveld.cm.api.PlayerService;
import nl.vanbijleveld.cm.exception.ConflictException;
import nl.vanbijleveld.cm.player.EnumSex;
import nl.vanbijleveld.cm.player.Player;
import nl.vanbijleveld.cm.player.PlayerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
public class PlayerControllerTest {

    private Player mockPlayer;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerControllerTest controller;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        mockPlayer = new Player("firstName", "", "lastname", "EMail@test.com", EnumSex.MALE);
    }

    @Test
    @WithMockUser(username = "user")
    public void findPlayer_returnJson() throws Exception {
        given(playerService.getPlayer(Mockito.anyLong())).willReturn(mockPlayer);
        mvc.perform(get("/player/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    // @Test
    @SuppressWarnings("unchecked")
    @WithMockUser(username = "user")
    public void findPlayer_returnNotFound() throws Exception {
        given(playerService.getPlayer(Mockito.anyLong())).willThrow(NotFoundException.class);
        mvc.perform(get("/player/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    // @Test
    @WithMockUser(username = "user")
    public void createNewPlayer() throws Exception {
        mvc.perform(put("/player").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(mockPlayer))).andExpect(status().isCreated());
    }

    // @Test
    @SuppressWarnings("unchecked")
    @WithMockUser(username = "user")
    public void createNewPlayerExpectConflict() throws Exception {
        given(playerService.createNewPlayer(Mockito.any(Player.class))).willThrow(ConflictException.class);
        mvc.perform(put("/player").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(mockPlayer))).andExpect(status().isConflict());
    }

}
