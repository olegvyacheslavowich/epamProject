package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import constant.Parameter;
import entity.Account;
import entity.Client;
import entity.User;
import entity.Voucher;
import exception.DataBaseConnectionException;
import exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Language;
import util.ResourceManager;
import service.MoneyService;
import service.OrderService;
import service.PaymentService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PaymentCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(PaymentCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page = Page.USER;
        HttpSession session = rq.getSession();
        OrderService orderService = new OrderService();
        MoneyService moneyService = new MoneyService();
        PaymentService paymentService = new PaymentService();

        if (!Validator.isEmpty(rq.getParameter(Parameter.MONEY_ID))) {
            logger.info("Card was not chose");
            return page;
        }
        int moneyId = Integer.parseInt(rq.getParameter(Parameter.MONEY_ID));

        User user = (User) session.getAttribute(Attribute.USER);
        Account account = user.getAccount();
        Voucher voucher = (Voucher) session.getAttribute(Attribute.VOUCHER);
        List<Client> clients = (List<Client>) session.getAttribute(Attribute.CLIENTS);

        try {
            paymentService.payOrder(moneyId, voucher, clients, account);
            rq.setAttribute(Attribute.ORDERS, orderService.getAllByAccount(account));
            rq.setAttribute(Attribute.MONEYS, moneyService.readByLogin(account));
            session.removeAttribute(Attribute.TOUR);
            logger.info("Voucher was bought");
        } catch (ServiceException e) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            logger.info("Payment page error :" + ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            return page;
        } catch (DataBaseConnectionException e) {
            logger.info("Payment page error :", e);
        }

        return page;
    }


}
