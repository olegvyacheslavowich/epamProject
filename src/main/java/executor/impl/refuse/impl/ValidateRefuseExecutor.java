package executor.impl.refuse.impl;

import exception.EmptyFieldException;
import exception.NoCreditCardsException;
import executor.impl.refuse.RefuseExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class ValidateRefuseExecutor extends RefuseExecutor {

    private RefuseExecutor next;

    public ValidateRefuseExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String orderId) throws NoCreditCardsException, EmptyFieldException {

        String page = null;

        if (next != null) {
            page = next.execute(orderId);
        }

        return page;
    }

    public void setNext(RefuseExecutor next) {
        this.next = next;
    }
}
