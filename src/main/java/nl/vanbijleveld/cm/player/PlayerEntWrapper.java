package nl.vanbijleveld.cm.player;

public class PlayerEntWrapper {

    public static PlayerEnt wrap(Player player) {
        PlayerEnt playerEnt = new PlayerEnt();

        playerEnt.setEmail(player.getEmail());
        playerEnt.setFirstName(player.getFirstName());
        playerEnt.setInfix(player.getInfix());
        playerEnt.setLastName(player.getLastName());
        playerEnt.setSex(player.getSex());

        return playerEnt;
    }

}
