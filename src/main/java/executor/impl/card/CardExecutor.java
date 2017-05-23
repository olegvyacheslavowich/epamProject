package executor.impl.card;

import exception.*;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 21.05.2017.
 */
public abstract class CardExecutor extends PageExecutor {

    public CardExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public abstract String execute(String ownerName, String number, String cvv, String date, String typeName) throws EmptyFieldException, NoCreditCardsException, NoFlightsException, NoToursException, IncorrectDataException;

}
