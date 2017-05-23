package constant;

/**
 * Created by 20_ok on 22.03.2017.
 */
public enum Page {

    INDEX("/jsp/index.jsp"),
    LOGIN("/jsp/login.jsp"),
    REGISTRATION("/jsp/registration.jsp"),
    TOURS("/jsp/tours.jsp"),
    FLIGHT("/jsp/flight.jsp"),
    PAYMENT("/jsp/payment.jsp"),
    USER("/jsp/user.jsp"),
    ADMIN("/jsp/admin.jsp"),
    CARD("/jsp/card.jsp");

    Page(String page) {
        this.page = page;
    }

    String page;

    public String getPage() {
        return page;
    }
}
