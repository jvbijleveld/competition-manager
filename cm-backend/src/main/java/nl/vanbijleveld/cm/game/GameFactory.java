package nl.vanbijleveld.cm.game;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {

    public static Game build(GameEnt gameEnt) {
        Game game = new Game();
        game.setId(gameEnt.getId());
        game.setContenders(gameEnt.getContenders());
        game.setStatus(gameEnt.getStatus());
        game.setWinningTeam(gameEnt.getWinningTeam());
        return game;
    }

    public static List<Game> build(Iterable<GameEnt> listGameEnt) {
        List<Game> list = new ArrayList<Game>();

        for (GameEnt gameEnt : listGameEnt) {
            list.add(build(gameEnt));
        }
        return list;
    }

}
