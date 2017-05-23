package constant;

/**
 * Created by 20_ok on 01.04.2017.
 */
public enum Attribute {

    LOGIN("login"),
    PASSWORD("password"),
    LOCALE("locale"),
    EX_INCORRECT_LOGIN_PASS("incorrectLoginOrPassword"),
    EX_DIFFERENT_PASS(""),
    EXCEPTION("exception"),
    TOUR("tour"),
    TOURS("tours"),
    DEPARTURE_CITY("departureCity"),
    ARRIVAL_CITY("arrivalCity"),
    COUNTRY("country"),
    PLANE("plane"),
    HOTEL_STARS("hotelStars"),
    ADULT_NUMBER("adultNumber"),
    ADULTS("adults"),
    CHILDREN_NUMBER("childrenNumber"),
    HOTELS("hotels"),
    ARRIVAL_TIME("arrivalTime"),
    DATE("date"),
    FLIGHT("flight"),
    FLIGHT_TO("flightTo"),
    FLIGHT_FROM("flightFrom"),
    VOUCHER("voucher"),
    CLIENTS("clients"),
    MONEYS("moneys"),
    ACCOUNT("account"),
    USER("user"),
    MONEY_ID("moneyId"),
    FULL_NAME("fullName1"),
    ORDERS("orders"),
    ADMIN("admin"),
    HOT_TOURS("hotToursNumber"),
    ALL_TOURS("allTours"),

    SOLD("sold");

    String attribute;

    Attribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
