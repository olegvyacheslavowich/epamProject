package constant;

/**
 * Created by 20_ok on 22.03.2017.
 */
public enum Pages {

    INDEX ("/jsp/index.jsp"),
    LOGIN ("/jsp/login.jsp"),
    REGISTRATION("/jsp/registration.jsp");

    Pages(String page) {
        this.page = page;
    }

    String page;

    public String getPage() {
        return page;
    }
}
