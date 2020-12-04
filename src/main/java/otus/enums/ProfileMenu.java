package otus.enums;

/**
 * Enum for profile menu items
 */
public enum ProfileMenu {
    PERSONAL("/lk/biography/"),
    LEARNING("/learning"),
    PAYMENT("/lk/payment"),
    SCHEDULE("/schedule"),
    LOGOUT("/logout");

    private final String linkPrefix;

    ProfileMenu(String linkPrefix) {
        this.linkPrefix = linkPrefix;
    }

    public String getLinkPrefix() {
        return linkPrefix;
    }
}
