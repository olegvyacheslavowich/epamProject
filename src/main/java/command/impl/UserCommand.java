package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import entity.Account;
import entity.User;
import exception.DataBaseConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.MoneyService;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 17.05.2017.
 */
public class UserCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(UserCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        HttpSession session = rq.getSession();
        OrderService orderService = new OrderService();
        MoneyService moneyService = new MoneyService();

        String page = Page.INDEX;

        try {
            User user = (User) session.getAttribute(Attribute.USER);
            Account account = user.getAccount();
            session.setAttribute(Attribute.ORDERS, orderService.getAllByAccount(account));
            session.setAttribute(Attribute.MONEYS, moneyService.readByLogin(account));
            page = Page.USER;
        } catch (DataBaseConnectionException e) {
            logger.info("User page error ", e);
            return page;
        }

        return page;
    }
}
