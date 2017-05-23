package executor.impl.payment.impl;

import exception.EmptyFieldException;
import exception.NoCreditCardsException;
import exception.NoMoneyException;
import executor.impl.payment.PaymentExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class ValidatePaymentExecutor extends PaymentExecutor {

    private PaymentExecutor next;

    public ValidatePaymentExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String moneyId) throws NoMoneyException, NoCreditCardsException, EmptyFieldException {

        String page = null;

        if (next != null) {
            page = next.execute(moneyId);
        }

        return page;
    }

    public void setNext(PaymentExecutor next) {
        this.next = next;
    }
}
