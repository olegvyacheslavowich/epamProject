package util;

import constant.Language;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Класс для работы с Resource bundle
 */
public class ResourceManager {

    private static final String RESOURCE_NAME = "bundle";

    /**
     * Метод возвращает найденный ресурс по заданному ключу заданного языка
     *
     * @param key  ключ, по которому ищем ресурс
     * @param lang язык возращаемого ресурса
     * @return ресурс
     */
    public static String getResource(String key, String lang) {

        if (lang == null) {
            lang = Language.RU_RU;
        }
        Locale locale;
        switch (lang) {
            case Language.RU_RU:
                locale = new Locale(Language.LOWER_RU, Language.UPPER_RU);
                break;
            case Language.EN_US:
                locale = new Locale(Language.LOWER_EN, Language.UPPER_US);
                break;
            default:
                locale = new Locale(Language.LOWER_RU, Language.UPPER_RU);
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
        return resourceBundle.getString(key);
    }

    /**
     * Метод возвращает найденный ресурс на английскм языке по заданному ключу
     *
     * @param key ключ, по которому ищем ресурс
     * @return ресурс
     */
    public static String getResource(String key) {

        Locale locale = new Locale(Language.LOWER_RU, Language.UPPER_RU);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);

        return resourceBundle.getString(key);
    }


}
