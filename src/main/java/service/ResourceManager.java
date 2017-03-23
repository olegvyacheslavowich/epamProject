package service;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by 20_ok on 23.03.2017.
 */
public class ResourceManager {

    private static final String RESOURCE_NAME = "exception";

    public static String getResource(String key, String lang) {

        Locale locale = null;
        switch (lang){
            case("Русский"):
                locale = new Locale("ru", "RU");
                break;
            case("English"):
                locale = new Locale("en", "US");
                break;

        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);

        return resourceBundle.getString(key);
    }

    public static String getResource(String key) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);

        return resourceBundle.getString(key);
    }

}
