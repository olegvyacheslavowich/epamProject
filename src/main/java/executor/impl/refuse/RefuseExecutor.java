package executor.impl.refuse;

import exception.EmptyFieldException;
import exception.NoCreditCardsException;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public abstract class RefuseExecutor extends PageExecutor {

    public RefuseExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public abstract String execute(String orderId) throws NoCreditCardsException, EmptyFieldException;
}
