package constant;

public enum Parameter {

    COMMAND("command"),
    TRANSLATOR("translator"),
    LOGIN("login"),
    PASSWORD("password"),
    LANGUAGE("language"),
    LOCALE("locale"),
    NAME("name"),
    FULL_NAME("fullName"),
    SURNAME("surname"),
    PATRONYMIC("patronymic"),
    YEAR("year"),
    DEPARTURE_CITY("departureCity"),
    COUNTRY("country"),
    CITY("city"),
    MOBILE("mobile"),
    EMAIL("email"),
    REPEAT_PASSWORD("rpassword"),
    DATE("date"),
    PRICE_FROM("priceFrom"),
    PRICE_TO("priceTo"),
    TOURS("tours"),
    TOUR("tour"),
    DESCRIPTION("description"),
    DAYS("days"),
    PRICE("price"),
    HOTEL_STARS("hotelStars"),
    ADULT_NUMBER("adultNumber"),
    CHILDREN_NUMBER("childrenNumber"),
    TOUR_ID("tourId"),
    SORT("sort"),
    FLIGHT_TO("flightTo"),
    FLIGHT_FROM("flightFrom"),
    PAPER("paper"),
    DOCUMENT_NUM("documentNum"),
    PHONE("phone"),
    BIRTHDAY("birthday"),
    DEPARTURE_DATE("departureDate"),
    ORDER_ID("orderId"),
    OWNER_NAME("ownerName"),
    NUMBER("number"),
    CVV("cvvNumber"),
    TYPE("type");

    String parameter;

    Parameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
