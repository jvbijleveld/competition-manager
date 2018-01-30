package nl.vanbijleveld.cm.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PlayerTests {

    private PlayerEnt mockPlayerEnt;
    private Player mockPlayer;

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
    }

    @Test
    public void testPlayerFactory() {
        mockPlayer = PlayerFactory.build(mockPlayerEnt);

        assertEquals(mockPlayer.getFirstName(), mockPlayerEnt.getFirstName());
        assertEquals(mockPlayer.getInfix(), mockPlayerEnt.getInfix());
        assertEquals(mockPlayer.getLastName(), mockPlayerEnt.getLastName());
        assertEquals(mockPlayer.getEmail(), mockPlayerEnt.getEmail());
        assertEquals(mockPlayer.getSex(), mockPlayerEnt.getSex());

        assertNotNull(mockPlayer.getFullName());
        assertNotNull(mockPlayer.getFullSalutation());
    }

}
