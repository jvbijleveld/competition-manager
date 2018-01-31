package nl.vanbijleveld.cm.player;

import java.lang.invoke.MethodHandles;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.exception.ConflictException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private PlayerRepository playerRepo;

    public Player getPlayer(long id) throws NotFoundException {
        PlayerEnt ent = playerRepo.findOneById(id);
        if (ent == null) {
            LOGGER.error("Player with id " + id + " is not found");
            throw new NotFoundException("Player with id " + id + " is not found");
        }
        return PlayerFactory.build(ent);
    }

    public Player createNewPlayer(Player newPlayer) throws ConflictException {
        PlayerEnt existingPlayer = playerRepo.findOneByEmail(newPlayer.getEmail());

        if (existingPlayer != null) {
            throw new ConflictException("Player already exists");
        }

        return PlayerFactory.build(playerRepo.save(PlayerEntWrapper.wrap(newPlayer)));
    }

}
