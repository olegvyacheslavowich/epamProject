package executor.impl.card.impl;

import constant.Key;
import constant.Parameter;
import exception.*;
import executor.impl.card.CardExecutor;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 21.05.2017.
 */
public class ReadCardExecutor extends CardExecutor {

    private CardExecutor next;


    public ReadCardExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String ownerName, String number, String cvv, String date, String typeName) throws EmptyFieldException, NoCreditCardsException, NoFlightsException, NoToursException, IncorrectDataException {

        String page = null;


        if (!readFromRequest(Parameter.OWNER_NAME.getParameter()).isEmpty()) {
            ownerName = readFromRequest(Parameter.OWNER_NAME.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_OWNER_NAME.getKey(), getCurrentLanguage()));
        }
        if (!readFromRequest(Parameter.NUMBER.getParameter()).isEmpty()) {
            number = readFromRequest(Parameter.NUMBER.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_CARD_NUMBER.getKey(), getCurrentLanguage()));
        }
        if (!readFromRequest(Parameter.CVV.getParameter()).isEmpty()) {
            cvv = readFromRequest(Parameter.CVV.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_CVV.getKey(), getCurrentLanguage()));
        }
        if (!readFromRequest(Parameter.DATE.getParameter()).isEmpty()) {
            date = readFromRequest(Parameter.DATE.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_DATE.getKey(), getCurrentLanguage()));

        }
        if (!readFromRequest(Parameter.TYPE.getParameter()).isEmpty()) {
            typeName = readFromRequest(Parameter.TYPE.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_CARD_TYPE.getKey(), getCurrentLanguage()));
        }

        if (next != null) {
            page = next.execute(ownerName, number, cvv, date, typeName);
        }

        return page;
    }

    public void setNext(CardExecutor next) {
        this.next = next;
    }
}
