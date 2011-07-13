package decidingFactorReturns.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author visitante
 */
public class I18n {

    public static String DEFAULT_LANGUAGE = "en";
    public static String DEFAULT_COUNTRY = "US";
    public static String PROPERTIES_FILE = "decidingFactorReturns.messages";
    private static Locale locale;
    private static ResourceBundle bundle;

    /**
     * Translate the message to the current locale.
     * @param property The name of the message to be translated.
     * @return The translated message.
     */
    public static String t(String property) {
        if (locale == null) {
            locale = new Locale(DEFAULT_LANGUAGE, DEFAULT_COUNTRY);
        }
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(PROPERTIES_FILE, locale);
        }
        return bundle.getString(property);
    }

    /**
     * Set a new locale for the application.
     * @param language
     * @param country
     */
    public static void setLocale(String language, String country) {
        locale = new Locale(language, country);
    }
}
