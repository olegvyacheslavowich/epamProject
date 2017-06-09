package validation;

/**
 * Created by 20_ok on 11.05.2017.
 */
public class Validator {

    private static final String RUS_LETTERS_REGEX = "^[ а-яА-ЯёЁa]+$";
    private static final String ENG_LETTERS_REGEX = "^[ a-zA-Z]+$";
    private static final String RUS_AND_ENG_LETTERS_REGEX = "^[ а-яА-Яa-zA-Z]+$";
    private static final String NUMBER_REGEX = "^[0-9]+$";
    private static final String MOBILE_NUMBER_REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private static final String DATE_REGEX = "^(19|20)[0-9]{2}-[0|1][0-9]-[0-3][0-9]";
    private static final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String LOGIN_REGEX = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    private static final String CARD_NUMBER_REGEX = "[0-9]{13,16}";
    private static final String CVV_NUMBER_REGEX = "[0-9]{3}";

    private Validator() {
    }

    public static boolean isEmpty(String value) {
        return ((value == null) && (value.equals("")));
    }

    public static boolean isDate(String date) {
        return date.matches(DATE_REGEX);
    }

    public static boolean isRusString(String string) {
        return string.matches(RUS_LETTERS_REGEX);
    }

    public static boolean isEngString(String string) {
        return string.matches(ENG_LETTERS_REGEX);
    }

    public static boolean isRusEngString(String string) {
        return string.matches(RUS_AND_ENG_LETTERS_REGEX);
    }

    public static boolean isNumber(String number) {
        return number.matches(NUMBER_REGEX);
    }

    public static boolean isMobileNumber(String number) {
        return number.matches(MOBILE_NUMBER_REGEX);
    }

    public static boolean isEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static boolean isCardNumber(String cardNumber) {
        return cardNumber.matches(CARD_NUMBER_REGEX);
    }

    public static boolean isCvvNumber(String cvv) {
        return cvv.matches(CVV_NUMBER_REGEX);
    }

    public static boolean isPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

}
