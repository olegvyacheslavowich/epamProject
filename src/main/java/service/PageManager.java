package service;

import java.util.ResourceBundle;

/**
 * Created by 20_ok on 22.03.2017.
 */
public class PageManager {

    private final static String PAGES_BUNDLE = "pages";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(PAGES_BUNDLE);

    private PageManager() {
    }

    public static String getPage(String key) {
        return resourceBundle.getString(key);
    }
}
