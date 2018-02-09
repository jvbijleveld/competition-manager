package nl.vanbijleveld.cm.team;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import nl.vanbijleveld.cm.player.Player;


public class Team implements Serializable {
    
    private static final long serialVersionUID = 37677839523845212L;
    private long id;
    private String name;
    private String yell;
    private List<Player> members = new ArrayList<Player>();
    
    
    public long getId(){
        return this.id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getYell(){
        return this.yell;
    }
    
    public void setYell(String yell){
        this.yell = yell;
    }
    
    public void addPlayer(Player newPlayer){
        members.add(newPlayer);    
    }
    
    public List<Player> getPlayers(){
        return this.members;
    }
    
}