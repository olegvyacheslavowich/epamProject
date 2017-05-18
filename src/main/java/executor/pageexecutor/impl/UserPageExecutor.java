package executor.pageexecutor.impl;

import constant.Attribute;
import constant.Page;
import entity.Account;
import entity.Money;
import entity.Order;
import entity.User;
import exception.NoCreditCardsException;
import executor.pageexecutor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 20_ok on 17.05.2017.
 */
public class UserPageExecutor extends PageExecutor {

    private String page = Page.USER.getPage();
    private List<Order> orders;
    private User user;
    private List<Money> moneys;

    public UserPageExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public void read() {
        user = (User) readFromSession(Attribute.USER.getAttribute());
    }

    @Override
    public void validate() {

    }

    @Override
    public void build() {
        Account account = user.getAccount();

        orders = getOrderDBService().getAllByAccount(account);
        try {
            moneys = getMoneyDBService().readByLogin(account);
        } catch (NoCreditCardsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write() {

        setToRequest(Attribute.ORDERS.getAttribute(), orders);
        setToRequest(Attribute.MONEYS.getAttribute(), moneys);
    }

    @Override
    public String returnPage() {
        return page;
    }
}
