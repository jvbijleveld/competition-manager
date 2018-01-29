package nl.vanbijleveld.cm.team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import javax.persistence.Id;
import javax.persistence.Table;

import nl.vanbijleveld.cm.team.TeamEnt;
import nl.vanbijleveld.cm.player.PlayerEnt;


@Entity
@Table(name = "teammembers")
public class TeamMembersEnt {
    
    @Id
    @Column(name = "id")
    private long id;
    
    @ManyToOne
    @JoinColumn(name="team", nullable=false)
    private TeamEnt team;
 
    @ManyToOne
    @JoinColumn(name="player", nullable=false)
    private PlayerEnt player;
 
}