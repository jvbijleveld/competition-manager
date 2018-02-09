package nl.vanbijleveld.cm.player;

public enum EnumSex {
    MALE("Man", "Dhr"), FEMALE("Vrouw", "Mevr"), U("Onbekend", "");

    private final String sex;
    private final String salutation;

    EnumSex(final String sex, final String salutation) {
        this.sex = sex;
        this.salutation = salutation;
    }

    public String getSex() {
        return sex;
    }

    public String getSalutation() {
        return salutation;
    }

}