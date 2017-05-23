package constant;

/**
 * Created by 20_ok on 01.04.2017.
 */
public enum Language {

    EN_US ("en_US"),
    RU_RU ("ru_RU"),
    UPPER_RU ("RU"),
    UPPER_US ("US"),
    LOWER_RU ("ru"),
    LOWER_EN ("en");

    String language;

    Language(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
