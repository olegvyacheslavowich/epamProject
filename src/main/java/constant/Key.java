package constant;

/**
 * Created by 20_ok on 01.04.2017.
 */
public enum Key {

    EX_WRONG_ACCOUNT("exception.wrong_account"),
    EX_DIFFERENT_PASSWORDS("exception.different_passwords"),
    EX_ACCOUNT_IS_EXIST("exception.exciting_account"),
    EX_ENTER_FULL_NAME("exception.enter_full_name"),
    EX_ENTER_BIRTHDAY("exception.enter_birthday"),
    EX_ENTER_PAPER("exception.enter_paper"),
    EX_ENTER_PAPER_NUM("exception.enter_paper_num"),
    EX_ENTER_PHONE("exception.enter_phone"),
    EX_ENTER_EMAIL("exception.enter_email"),
    EX_ENTER_OWNER_NAME("exception.enter_owner_name"),
    EX_ENTER_CARD_NUMBER("exception.enter_card_number"),
    EX_ENTER_CVV("exception.enter_cvv"),
    EX_ENTER_DATE("exception.enter_date"),
    EX_ENTER_CARD_TYPE("exception.enter_card_type"),
    EX_SELECT_TOUR("exception.select_tour"),
    EX_ENTER_LOGIN("exception.enter_login"),
    EX_ENTER_PASSWORD("exception.enter_password"),
    EX_NOT_ENOUGH_MONEY("exception.not_enough_money"),
    EX_ENTER_CARD("exception.enter_card"),
    EX_ENTER_ORDER("exception.enter_order"),
    EX_REPEAT_PASSWORD("exception.repeat_password"),
    EX_NO_FLIGHT_FOR_DATE("exception.no_flight_for_date"),
    EX_NO_FLIGHT_FOR_PARAMETERS("exception.no_flight_for_parameters"),
    EX_TOURS_NOT_FOUND("exception.tours_not_found"),
    EX_INCORRECT_OWNER_NAME("exception.incorrect_owner_name"),
    EX_INCORRECT_CARD_NUMBER("exception.incorrect_card_number"),
    EX_INCORRECT_CVV("exception.incorrect_cvv"),
    EX_INCORRECT_DATE("exception.incorrect_date"),
    EX_INCORRECT_CARD_TYPE("exception.incorrect_card_type"),
    EX_INCORRECT_FULL_NAME("exception.incorrect_full_name"),
    EX_INCORRECT_BIRTHDAY("exception.incorrect_birthday"),
    EX_INCORRECT_PAPER_NUM("exception.incorrect_paper_num"),
    EX_INCORRECT_PAPER("exception.incorrect_paper"),
    EX_INCORRECT_PHONE("exception.incorrect_phone"),
    EX_INCORRECT_EMAIL("exception.incorrect_email"),
    EX_INCORRECT_CITY("exception.incorrect_city"),
    EX_ENTER_CITY("exception.enter_city"),
    EX_INCORRECT_LOGIN("exception.incorrect_login"),
    EX_INCORRECT_PASSWORD("exception.incorrect_password");

    String key;

    Key(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
