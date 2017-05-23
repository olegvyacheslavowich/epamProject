package executor.impl.refuse.impl;

import constant.Key;
import constant.Parameter;
import exception.EmptyFieldException;
import exception.NoCreditCardsException;
import executor.impl.refuse.RefuseExecutor;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class ReadRefuseExecutor extends RefuseExecutor {

    private RefuseExecutor next;

    public ReadRefuseExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String orderId) throws EmptyFieldException, NoCreditCardsException {

        String page = null;
        if (readFromRequest(Parameter.ORDER_ID.getParameter()) != null) {
            if (!readFromRequest(Parameter.ORDER_ID.getParameter()).isEmpty()) {
                orderId = readFromRequest(Parameter.ORDER_ID.getParameter());
            }
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_ORDER.getKey(), getCurrentLanguage()));
        }


        if (next != null) {
            page = next.execute(orderId);
        }

        return page;
    }

    public void setNext(RefuseExecutor next) {
        this.next = next;
    }
}
