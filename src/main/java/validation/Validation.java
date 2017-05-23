package validation;

import constant.Attribute;
import exception.*;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 11.05.2017.
 */
public abstract class Validation {

    private HttpSession session;

    public Validation(HttpSession session) {
        this.session = session;
    }

    public abstract void isValid() throws EmptyFieldException, IncorrectDataException, NoFlightsException, NoCreditCardsException, NoToursException;

    protected String getCurrentLanguage() {
        return (String) session.getAttribute(Attribute.LOCALE.getAttribute());
    }
}
