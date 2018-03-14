package nl.vanbijleveld.cm.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nl.vanbijleveld.cm.team.TeamEnt;

@Entity
@Table(name = "gamemembers")
public class GameMembersEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    private GameEnt game;

    @ManyToOne
    @JoinColumn(name = "team", nullable = false)
    private TeamEnt team;

    @Column(name = "score", nullable = false)
    private Integer score;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GameEnt getGame() {
        return game;
    }

    public void setGame(GameEnt game) {
        this.game = game;
    }

    public TeamEnt getTeam() {
        return team;
    }

    public void setTeam(TeamEnt team) {
        this.team = team;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
