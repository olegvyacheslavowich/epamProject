package service.dbservice;

import constant.Attribute;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 17.05.2017.
 */
public abstract class DBService {

    private HttpSession session;

    public DBService(HttpSession session) {
        this.session = session;
    }

    protected String getCurrentLanguage() {
        return (String) session.getAttribute(Attribute.LOCALE.getAttribute());
    }
}
