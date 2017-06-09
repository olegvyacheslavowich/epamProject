package util;

import constant.Attribute;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 6/8/2017.
 */
public class Language {

    public static String getCurrentLanguage(HttpSession session) {
        return (String) session.getAttribute(Attribute.LOCALE);
    }
}
