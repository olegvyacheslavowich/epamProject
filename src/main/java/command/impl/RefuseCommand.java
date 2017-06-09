package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Key;
import constant.Page;
import constant.Parameter;
import entity.*;
import exception.DataBaseConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Language;
import util.ResourceManager;
import service.MoneyService;
import service.OrderService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RefuseCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(RefuseCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page = Page.ADMIN;
        HttpSession session = rq.getSession();
        OrderService orderService = new OrderService();
        MoneyService moneyService = new MoneyService();

        if (Validator.isEmpty(rq.getParameter(Parameter.ORDER_ID))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_ORDER, Language.getCurrentLanguage(session)));
            logger.info("User page error :" + ResourceManager.getResource(Key.EX_ENTER_ORDER, Language.getCurrentLanguage(session)));
            return page;
        }

        String orderId = rq.getParameter(Parameter.ORDER_ID);

        try {
            User user = (User) session.getAttribute(Attribute.USER);
            Order order = orderService.read(Integer.parseInt(orderId));
            Client client = order.getClient();
            Voucher voucher = order.getVoucher();
            Account account = user.getAccount();
            Money money = moneyService.read(order.getMoney().getId());
            orderService.deleteOrder(order, client, voucher, money);
            rq.setAttribute(Attribute.ORDERS, orderService.getAllByAccount(account));
            rq.setAttribute(Attribute.MONEYS, moneyService.readByLogin(account));
            logger.info("Order was deleted");
        } catch (DataBaseConnectionException e) {
            logger.info("User page error : ", e);
            return page;
        }

        return page;
    }


}
