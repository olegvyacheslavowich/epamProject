package constant;

/**
 * Created by 20_ok on 01.04.2017.
 */
public enum Key {

    WRONG_ACCOUNT("exception.wrong_account"),
    PASS_ARE_DIFFERENT("exception.different_passwords"),
    ACCOUNT_IS_EXIST("exception.exciting_account"),
    ANY_HOTEL("hotel.any_class"),
    ANY_COUNTRY("country.any_country");

    String key;

    Key(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
