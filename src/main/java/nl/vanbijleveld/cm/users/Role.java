package nl.vanbijleveld.cm.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import nl.vanbijleveld.cm.team.TeamEnt;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "email")
    private User user;

    @OneToOne(mappedBy = "id")
    private TeamEnt team;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TeamEnt getTeam() {
        return team;
    }

    public void setTeam(TeamEnt team) {
        this.team = team;
    }

}
