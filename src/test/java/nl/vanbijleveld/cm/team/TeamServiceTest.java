package nl.vanbijleveld.cm.team;

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
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepo;

    @Mock
    private TeamEnt mockTeamEnt;

    @InjectMocks
    private TeamService teamService;

    @Before
    public void setup() {
        when(teamRepo.findOneById(Matchers.anyLong())).thenReturn(mockTeamEnt);
    }

    @Test
    public void getTeamTest() throws NotFoundException {
        when(teamRepo.findOneById(Matchers.anyLong())).thenReturn(mockTeamEnt);
        final Team team = teamService.getTeam(1l);
        assertNotNull(team);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NotFoundException.class)
    public void getTeamNotFoundTest() throws NotFoundException {
        when(teamRepo.findOneById(Matchers.anyLong())).thenReturn(null);
        final Team team = teamService.getTeam(1l);
        assertNull(team);
    }

}
