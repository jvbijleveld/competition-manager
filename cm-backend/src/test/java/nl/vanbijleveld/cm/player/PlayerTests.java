package nl.vanbijleveld.cm.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PlayerTests {

    private PlayerEnt mockPlayerEnt;
    private Player mockPlayer;
    private List<PlayerEnt> listEnts;
    private List<Player> listPlayers;

    @Mock
    private PlayerRepository playerRepo;

    @Mock
    private PlayerFactory playerFactory;

    @InjectMocks
    private PlayerTests playerEnt;

    @Before
    public void setup() {
        mockPlayerEnt = new PlayerEnt();
        mockPlayerEnt.setId(1l);
        mockPlayerEnt.setFirstName("firstName");
        mockPlayerEnt.setInfix("infix");
        mockPlayerEnt.setLastName("lastName");
        mockPlayerEnt.setSex(EnumSex.MALE);
        mockPlayerEnt.setEmail("email");

        listEnts = new ArrayList<PlayerEnt>();
        listEnts.add(mockPlayerEnt);
    }

    @Test
    public void testPlayer() {
        Player testPlayer = new Player("first", "infix", "last");
        assertEquals("first", testPlayer.getFirstName());
        assertEquals("infix", testPlayer.getInfix());
        assertEquals("last", testPlayer.getLastName());

        testPlayer.setFirstName("newFirst");
        assertEquals("newFirst", testPlayer.getFirstName());
        testPlayer.setInfix("newInfix");
        assertEquals("newInfix", testPlayer.getInfix());
        testPlayer.setLastName("newLast");
        assertEquals("newLast", testPlayer.getLastName());
    }

    @Test
    public void testPlayerFactory() {
        listPlayers = PlayerFactory.build(listEnts);
        mockPlayer = listPlayers.get(0);

        assertEquals(mockPlayer.getFirstName(), mockPlayerEnt.getFirstName());
        assertEquals(mockPlayer.getInfix(), mockPlayerEnt.getInfix());
        assertEquals(mockPlayer.getLastName(), mockPlayerEnt.getLastName());
        assertEquals(mockPlayer.getEmail(), mockPlayerEnt.getEmail());
        assertEquals(mockPlayer.getSex(), mockPlayerEnt.getSex());

        assertNotNull(mockPlayer.getFullName());
        assertNotNull(mockPlayer.getFullSalutation());
    }

}
