package nl.vanbijleveld.cm.users;

public enum EnumRole {
    PLAYER("Speler"), COACH("Coach"), ADMIN("Beheerder");

    private final String role;

    EnumRole(final String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
