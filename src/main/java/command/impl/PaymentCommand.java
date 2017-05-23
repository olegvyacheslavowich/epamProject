package command.impl;

import com.sun.istack.internal.logging.Logger;
import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import exception.EmptyFieldException;
import exception.NoCreditCardsException;
import exception.NoMoneyException;
import executor.impl.payment.impl.BuildPaymentExecutor;
import executor.impl.payment.impl.ReadPaymentExecutor;
import executor.impl.payment.impl.ValidatePaymentExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PaymentCommand implements ActionCommand {

    private Logger logger = Logger.getLogger(PaymentCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page;

        HttpSession session = rq.getSession();

        ReadPaymentExecutor read = new ReadPaymentExecutor(rq, session);
        ValidatePaymentExecutor validate = new ValidatePaymentExecutor(rq, session);
        BuildPaymentExecutor build = new BuildPaymentExecutor(rq, session);

        read.setNext(validate);
        validate.setNext(build);
        try {
            page = read.execute(null);
        } catch (NoMoneyException | NoCreditCardsException | EmptyFieldException e) {
            rq.setAttribute(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            page = Page.PAYMENT.getPage();
            logger.info(e.getMessage());
        }

        return page;
    }
}
