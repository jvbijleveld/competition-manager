package nl.vanbijleveld.cm.game;

public class GameEntWrapper {

    public static GameEnt wrap(Game game) {
        GameEnt gameEnt = new GameEnt();
        gameEnt.setId(game.getId());
        gameEnt.setContenders(game.getContenders());
        gameEnt.setStatus(game.getStatus());
        gameEnt.setWinningTeam(game.getWinningTeam());

        return gameEnt;
    }
}
