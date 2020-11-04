package otus.enums;

/**
 * Enum for available contact types
 */
public enum ContactType {
    EMPTY(""),
    FACEBOOK("facebook"),
    VK("vk"),
    OK("ok"),
    SKYPE("skype"),
    VIBER("viber"),
    TELEGRAM("telegram"),
    WHATS_APP("whatsapp");

    private final String value;

    ContactType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * @param value contact type string value
     * @return Contact type enum
     */
    public static ContactType getTypeByValue(String value) {
        for (ContactType type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("No enum found for value: %s", value));
    }
}
