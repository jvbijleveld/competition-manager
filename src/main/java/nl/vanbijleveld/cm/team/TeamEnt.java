package nl.vanbijleveld.cm.team;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class TeamEnt {

    @Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "yell")
    private String yell;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
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

    public void addMember(TeamMembersEnt newMember) {
        if (this.members == null) {
            this.members = new ArrayList<TeamMembersEnt>();
        }
        this.members.add(newMember);
    }

    public void setMembers(List<TeamMembersEnt> list) {
        this.members = list;
    }

}
