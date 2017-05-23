package executor.impl.user.impl;

import constant.Attribute;
import constant.Page;
import entity.Account;
import entity.Money;
import entity.Order;
import entity.User;
import executor.impl.user.UserExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class BuildUserExecutor extends UserExecutor {

    private UserExecutor next;

    public BuildUserExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute() {

        String page = Page.USER.getPage();

        User user = (User) readFromSession(Attribute.USER.getAttribute());
        Account account = user.getAccount();

        List<Order> orders = getOrderDBService().getAllByAccount(account);
        List<Money> moneys = getMoneyDBService().readByLogin(account);

        setToSession(Attribute.ORDERS.getAttribute(), orders);
        setToSession(Attribute.MONEYS.getAttribute(), moneys);

        if (next != null) {
            next.execute();
        }

        return page;
    }

    public void setNext(UserExecutor next) {
        this.next = next;
    }
}
