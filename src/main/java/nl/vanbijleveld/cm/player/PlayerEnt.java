package nl.vanbijleveld.cm.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class PlayerEnt {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "infix")
    private String infix;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "sex")
    private EnumSex sex;

    @Column(name = "email")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getInfix() {
        return this.infix;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EnumSex getSex() {
        return this.sex;
    }

    public void setSex(EnumSex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return this.firstName + ((!this.infix.isEmpty()) ? this.infix + " " : " ") + this.lastName;
    }

    public String getFullSalutation() {
        return ((!this.sex.getSalutation().isEmpty()) ? this.sex.getSalutation() + " " : "") + getFullName();
    }

}
