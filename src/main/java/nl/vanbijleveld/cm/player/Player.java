package nl.vanbijleveld.cm.player;

import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = 37677839523521096L;
    private String firstName;
    private String infix;
    private String lastName;
    private EnumSex sex;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EnumSex getSex() {
        return sex;
    }

    public void setSex(EnumSex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
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
