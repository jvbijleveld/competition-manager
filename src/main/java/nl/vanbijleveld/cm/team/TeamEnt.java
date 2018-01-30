package nl.vanbijleveld.cm.team;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class TeamEnt {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "yell")
    private String yell;

    @OneToMany(mappedBy = "team")
    private List<TeamMembersEnt> members;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYell() {
        return this.yell;
    }

    public void setYell(String yell) {
        this.yell = yell;
    }

    public List<TeamMembersEnt> getMembers() {
        return this.members;
    }

    public void setMembers(List<TeamMembersEnt> list) {
        this.members = list;
    }

}
