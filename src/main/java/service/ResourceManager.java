package service;

import constant.Language;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by 20_ok on 23.03.2017.
 */

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

        Locale locale;
        if (lang == null){
            lang = Language.RU_RU.getLanguage();
        }
        Language language = Language.valueOf(lang.toUpperCase());
        switch (language) {
            case RU_RU:
                locale = new Locale(Language.LOWER_RU.getLanguage(), Language.UPPER_RU.getLanguage());
                break;
            case EN_US:
                locale = new Locale(Language.LOWER_EN.getLanguage(), Language.UPPER_US.getLanguage());
                break;
            default:
                locale = new Locale(Language.LOWER_RU.getLanguage(), Language.UPPER_RU.getLanguage());
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

        Locale locale = new Locale(Language.LOWER_RU.getLanguage(), Language.UPPER_RU.getLanguage());
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);

        return resourceBundle.getString(key);
    }


}
