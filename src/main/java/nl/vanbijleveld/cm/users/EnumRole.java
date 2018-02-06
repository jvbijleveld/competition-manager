package nl.vanbijleveld.cm.users;

public enum EnumRole {
    PLAYER("PLAYER", "Speler"), COACH("COACH", "Coach"), ADMIN("ADMIN", "Beheerder");

    private final String role;
    private final String name;

    EnumRole(final String role, final String name) {
        this.role = role;
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

}
