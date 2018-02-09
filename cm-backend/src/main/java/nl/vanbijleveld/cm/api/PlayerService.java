package nl.vanbijleveld.cm.api;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.exception.ConflictException;
import nl.vanbijleveld.cm.player.Player;

public interface PlayerService {

    Player getPlayer(long id) throws NotFoundException;

    Player createNewPlayer(Player newPlayer) throws ConflictException;

}
