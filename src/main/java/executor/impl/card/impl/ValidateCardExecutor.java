package executor.impl.card.impl;

import exception.*;
import executor.impl.card.CardExecutor;
import validation.impl.CardPageValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 21.05.2017.
 */
public class ValidateCardExecutor extends CardExecutor {

    private CardExecutor next;

    public ValidateCardExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String ownerName, String number, String cvv, String date, String typeName) throws EmptyFieldException, NoCreditCardsException, NoFlightsException, NoToursException, IncorrectDataException {

        String page = null;

        CardPageValidation validation = new CardPageValidation(getSession(), ownerName, number, cvv, date, typeName);
        validation.isValid();

        if (next != null) {
            page = next.execute(ownerName, number, cvv, date, typeName);
        }

        return page;
    }


    public void setNext(CardExecutor next) {
        this.next = next;
    }
}
