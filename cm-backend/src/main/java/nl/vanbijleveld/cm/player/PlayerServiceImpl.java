package nl.vanbijleveld.cm.player;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.api.PlayerService;
import nl.vanbijleveld.cm.api.TeamService;
import nl.vanbijleveld.cm.exception.ConflictException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private TeamService teamService;

    @Override
    public List<Player> listPLayers(){
        return PlayerFactory.build(playerRepo.findAll());
    }
        
    @Override
    public Player getPlayer(long id) throws NotFoundException {
        PlayerEnt ent = playerRepo.findOneById(id);
        if (ent == null) {
            LOGGER.error("Player with id " + id + " is not found");
            throw new NotFoundException("Player with id " + id + " is not found");
        }
        return PlayerFactory.build(ent);
    }

    @Override
    public Player createNewPlayer(Player newPlayer) throws ConflictException {
        PlayerEnt existingPlayer = playerRepo.findOneByEmail(newPlayer.getEmail());

        if (existingPlayer != null) {
            throw new ConflictException("Player already exists");
        }

        PlayerEnt newPlayerEnt = playerRepo.save(PlayerEntWrapper.wrap(newPlayer));
        Player createdPlayer = PlayerFactory.build(newPlayerEnt);
        teamService.createTeam(createdPlayer.getFullName(), "", newPlayerEnt);
        return createdPlayer;
    }

}
