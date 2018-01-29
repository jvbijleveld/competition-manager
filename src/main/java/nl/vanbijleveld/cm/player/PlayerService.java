package nl.vanbijleveld.cm.player;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepo;

    public Player getPlayer(long id) throws NotFoundException{
        PlayerEnt ent = playerRepo.findOneById(id);
        if(ent == null){
            System.out.println("Player with id " + id + " is not found");
            throw new NotFoundException("Player with id " + id + " is not found");
        }
        return PlayerFactory.build(ent);
    }

}
