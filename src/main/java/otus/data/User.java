package otus.data;

import otus.enums.ContactType;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple user for testing
 */
public final class User {

    // Personal data
    public static final String NAME = "Александр";
    public static final String NAME_LATIN = "Alexander";
    public static final String SURNAME = "Щерба";
    public static final String SURNAME_LATIN = "Scherba";
    public static final String BIRTH_DATE = "02.12.1993";
    public static final String BLOG_NAME = "Sanya.nett";

    // Main information
    public static final String COUNTRY = "Россия";
    public static final String CITY = "Санкт-Петербург";
    public static final String LANGUAGE_LEVEL = "Средний (Intermediate)";

    // Contact data
    public static final Map<ContactType, String> EXTRA_CONTACT_DATA = new HashMap<ContactType, String>() {{
        put(ContactType.VK, "https://vk.com");
        put(ContactType.FACEBOOK, "https://www.facebook.com/");
    }};

}
