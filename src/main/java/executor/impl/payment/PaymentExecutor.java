package executor.impl.payment;

import exception.EmptyFieldException;
import exception.NoCreditCardsException;
import exception.NoMoneyException;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class PaymentExecutor extends PageExecutor {


    public PaymentExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public abstract String execute(String moneyId) throws NoMoneyException, NoCreditCardsException, EmptyFieldException;
}
