package executor.impl.payment.impl;

import constant.Attribute;
import constant.Key;
import exception.EmptyFieldException;
import exception.NoCreditCardsException;
import exception.NoMoneyException;
import executor.impl.payment.PaymentExecutor;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class ReadPaymentExecutor extends PaymentExecutor {

    private PaymentExecutor next;

    public ReadPaymentExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String moneyId) throws NoMoneyException, NoCreditCardsException, EmptyFieldException {

        String page = null;

        if (!readFromRequest(Attribute.MONEY_ID.getAttribute()).isEmpty()) {
            moneyId = readFromRequest(Attribute.MONEY_ID.getAttribute());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_CARD.getKey(), getCurrentLanguage()));
        }

        if (next != null) {
            page = next.execute(moneyId);
        }

        return page;
    }

    public void setNext(PaymentExecutor next) {
        this.next = next;
    }
}
