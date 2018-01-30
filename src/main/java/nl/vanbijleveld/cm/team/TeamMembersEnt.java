package nl.vanbijleveld.cm.team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nl.vanbijleveld.cm.player.PlayerEnt;

@Entity
@Table(name = "teammembers")
public class TeamMembersEnt {

    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "team", nullable = false)
    private TeamEnt team;

    @ManyToOne
    @JoinColumn(name = "player", nullable = false)
    private PlayerEnt player;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TeamEnt getTeam() {
        return team;
    }

    public void setTeam(TeamEnt team) {
        this.team = team;
    }

    public PlayerEnt getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEnt player) {
        this.player = player;
    }

}
