package nl.vanbijleveld.cm.player;

public class PlayerFactory {

    public static Player build(PlayerEnt entity) {
        Player player = new Player();

        player.setEmail(entity.getEmail());
        player.setFirstName(entity.getFirstName());
        player.setInfix(entity.getInfix());
        player.setLastName(entity.getLastName());
        player.setSex(entity.getSex());

        return player;
    }

}
