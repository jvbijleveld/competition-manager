package nl.vanbijleveld.cm.mock;

import nl.vanbijleveld.cm.player.EnumSex;
import nl.vanbijleveld.cm.player.PlayerEnt;
import org.springframework.stereotype.Service;

@Service
public class PlayerRepositoryMock {

    private final PlayerEnt player = new PlayerEnt();

    public PlayerRepositoryMock() {
        player.setFirstName("Mark");
        player.setLastName("Mocker");
        player.setInfix("von");
        player.setId(1L);
        player.setSex(EnumSex.MALE);
        player.setEmail("mock@test.mock");
    }

    public PlayerEnt findOneById(long id) {
        return player;
    }

    public PlayerEnt findOneByEmail(String email) {
        return player;
    }
}
