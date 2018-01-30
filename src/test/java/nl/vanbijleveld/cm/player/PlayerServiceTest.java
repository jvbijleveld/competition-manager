package nl.vanbijleveld.cm.player;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import javassist.NotFoundException;

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

    @Mock
    private PlayerRepository playerRepo;

    @Mock
    private PlayerEnt mockPlayerEnt;

    @InjectMocks
    private PlayerService playerService;

    @Before
    public void setup() {

    }

    @Test
    public void getPlayerTest() throws NotFoundException {
        when(playerRepo.findOneById(Matchers.anyLong())).thenReturn(mockPlayerEnt);
        final Player player = playerService.getPlayer(1l);
        assertNotNull(player);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NotFoundException.class)
    public void getPlayerNotFoundTest() throws NotFoundException {
        when(playerRepo.findOneById(Matchers.anyLong())).thenReturn(null);
        final Player player = playerService.getPlayer(1l);
        assertNull(player);
    }

}
