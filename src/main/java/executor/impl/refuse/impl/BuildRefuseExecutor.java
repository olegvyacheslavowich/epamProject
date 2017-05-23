package executor.impl.refuse.impl;

import constant.Attribute;
import constant.Num;
import constant.Page;
import entity.*;
import exception.EmptyFieldException;
import exception.NoCreditCardsException;
import executor.impl.refuse.RefuseExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BuildRefuseExecutor extends RefuseExecutor {

    private RefuseExecutor next;

    public BuildRefuseExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String orderId) throws NoCreditCardsException, EmptyFieldException {

        String page = Page.ADMIN.getPage();

        User user = (User) readFromSession(Attribute.USER.getAttribute());
        Order order = getOrderDBService().read(Integer.parseInt(orderId));
        Client client = order.getClient();
        Voucher voucher = order.getVoucher();
        Account account = user.getAccount();
        Money money = getMoneyDBService().read(order.getMoney().getId());

        deleteOrder(order, client, voucher, money);
        List<Order> orders = getOrderDBService().getAllByAccount(account);
        List<Money> moneys = getMoneyDBService().readByLogin(account);

        setToRequest(Attribute.ORDERS.getAttribute(), orders);
        setToRequest(Attribute.MONEYS.getAttribute(), moneys);

        if (next != null)

        {
            page = next.execute(orderId);
        }

        return page;
    }

    public void setNext(RefuseExecutor next) {
        this.next = next;
    }

    private void deleteOrder(Order order, Client client, Voucher voucher, Money money) {

        getOrderDBService().delete(order);
        getClientDBService().delete(client);
        money = updateMoney(money, voucher);
        if (voucher.getClientNumber() == Num.FIRST.getNum()) {
            getMoneyDBService().update(money);
            getVoucherDBService().delete(voucher);
        } else {
            voucher = updateVoucher(voucher);
            getVoucherDBService().update(voucher);
            getMoneyDBService().update(money);
        }
    }

    private Voucher updateVoucher(Voucher voucher) {
        int clientNumber = voucher.getClientNumber();
        int tourPrice = voucher.getTour().getPrice();
        int voucherPrice = voucher.getPrice();
        voucher.setClientNumber(clientNumber - Num.FIRST.getNum());
        voucher.setPrice(voucherPrice - tourPrice);

        return voucher;
    }

    private Money updateMoney(Money money, Voucher voucher) {
        int tourPrice = voucher.getTour().getPrice();
        int currentMoney = money.getMoney();
        money.setMoney(currentMoney + tourPrice);

        return money;
    }

}
