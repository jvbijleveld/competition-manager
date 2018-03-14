package nl.vanbijleveld.cm.api;

import java.util.List;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.game.Game;
import nl.vanbijleveld.cm.game.GameStatusEnum;
import nl.vanbijleveld.cm.team.Team;

public interface GameService {

    Game getGame(long id) throws NotFoundException;

    List<Game> getAllGames();

    List<Game> getGamesByStatus(GameStatusEnum status);

    Game createNewGame(List<Team> contenders);

    Game addTeamsToGame(long id, List<Team> contenders);

}
