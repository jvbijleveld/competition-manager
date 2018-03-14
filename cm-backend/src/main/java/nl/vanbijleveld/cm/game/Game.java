package nl.vanbijleveld.cm.game;

import java.io.Serializable;
import java.util.List;

import nl.vanbijleveld.cm.team.TeamEnt;

public class Game implements Serializable {

    private static final long serialVersionUID = -5674735434924338380L;
    private long id;
    private List<GameMembersEnt> contenders;
    private TeamEnt winningTeam;
    private GameStatusEnum status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<GameMembersEnt> getContenders() {
        return contenders;
    }

    public void setContenders(List<GameMembersEnt> contenders) {
        this.contenders = contenders;
    }

    public TeamEnt getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(TeamEnt winningTeam) {
        this.winningTeam = winningTeam;
    }

    public GameStatusEnum getStatus() {
        return status;
    }

    public void setStatus(GameStatusEnum status) {
        this.status = status;
    }

}
