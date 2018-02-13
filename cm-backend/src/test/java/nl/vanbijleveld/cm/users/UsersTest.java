package nl.vanbijleveld.cm.users;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
public class UsersTest {

    private User mockUser;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup() {
        mockUser = new User();
        mockUser.setEmail("email@test");
        mockUser.setActivated(true);
        mockUser.setPassword("password");
        mockUser.setRole(EnumRole.COACH);
    }

    @Test
    public void findUserTest() {
        when(userRepo.findOneByEmail(Matchers.anyString())).thenReturn(mockUser);
        User foundUser = userService.findByUsername("someOne");

        assertEquals(mockUser.getEmail(), foundUser.getEmail());
        assertEquals(mockUser.getPassword(), foundUser.getPassword());
        assertEquals(mockUser.getRole(), foundUser.getRole());
    }

}
