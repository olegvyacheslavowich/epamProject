package constant;

/**
 * Created by 20_ok on 11.05.2017.
 */
public enum Regex {

    RUS_LETTERS("^[ а-яА-ЯёЁa]+$"),
    ENG_LETTERS("^[ a-zA-Z]+$"),
    RUS_AND_ENG_LETTERS("^[ а-яА-Яa-zA-Z]+$"),
    NUMBER("^[0-9]+$"),
    MOBILE_NUMBER("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$"),
    DATE("^(19|20)[0-9]{2}-[0|1][0-9]-[0-3][0-9]"),
    EMAIL("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$"),
    LOGIN("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$"),
    PASSWORD("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$"),
    CARD_NUMBER("[0-9]{13,16}"),
    CVV_NUMBER ("[0-9]{3}");


    private String regex;

    Regex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
