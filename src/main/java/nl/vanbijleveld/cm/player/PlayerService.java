package nl.vanbijleveld.cm.player;

import nl.vanbijleveld.cm.mock.PlayerRepositoryMock;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepositoryMock service;

    public Player getPlayer(long id) {
        return PlayerFactory.build(service.findOneById(id));
    }

}
