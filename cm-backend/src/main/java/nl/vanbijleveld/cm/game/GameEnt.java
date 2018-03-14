package nl.vanbijleveld.cm.game;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import nl.vanbijleveld.cm.team.TeamEnt;

@Entity
@Table(name = "game")
public class GameEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameMembersEnt> contenders;

    @ManyToOne
    @JoinColumn(name = "team", nullable = false)
    private TeamEnt winningTeam;

    @Column(name = "status")
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
