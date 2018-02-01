package nl.vanbijleveld.cm.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

    public static Player build(PlayerEnt entity) {
        Player newPlayer = new Player(entity.getFirstName(), entity.getInfix(), entity.getLastName(), entity.getEmail(), entity.getSex());
        newPlayer.setId(entity.getId());
        return newPlayer;
    }

    public static List<Player> build(List<PlayerEnt> players) {
        List<Player> list = new ArrayList<Player>();

        for (PlayerEnt player : players) {
            list.add(build(player));
        }
        return list;
    }

}
