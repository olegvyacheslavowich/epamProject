package executor.impl.payment.impl;

import constant.Attribute;
import constant.Key;
import constant.Page;
import entity.*;
import exception.EmptyFieldException;
import exception.NoCreditCardsException;
import exception.NoMoneyException;
import executor.impl.payment.PaymentExecutor;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class BuildPaymentExecutor extends PaymentExecutor {

    private PaymentExecutor next;

    public BuildPaymentExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String moneyId) throws NoMoneyException, NoCreditCardsException, EmptyFieldException {

        String page = Page.USER.getPage();

        User user = (User) readFromSession(Attribute.USER.getAttribute());
        Account account = user.getAccount();
        Voucher voucher = (Voucher) readFromSession(Attribute.VOUCHER.getAttribute());
        List<Client> clients = (List<Client>) readFromSession(Attribute.CLIENTS.getAttribute());

        Money money = getMoneyDBService().read(Integer.parseInt(moneyId));
        money = getMoneyAfterSale(money, voucher);
        voucher.setId(getVoucherDBService().create(voucher));
        clients = createClients(clients);
        List<Order> orders = buildOrders(clients, voucher, account, money);
        createOrders(orders);
        getMoneyDBService().update(money);

        orders = getOrderDBService().getAllByAccount(account);
        List<Money> moneys = getMoneyDBService().readByLogin(account);

        setToRequest(Attribute.ORDERS.getAttribute(), orders);
        setToRequest(Attribute.MONEYS.getAttribute(), moneys);
        removeFromSession(Attribute.TOUR.getAttribute());

        if (next != null)

        {
            page = next.execute(moneyId);
        }

        return page;
    }

    public void setNext(PaymentExecutor next) {
        this.next = next;
    }


    /**
     * добавляет заказы в БД и возвращает список заказов с ID
     * заказов, добавленных ранее
     *
     * @param orders заказы
     */
    private List<Order> createOrders(List<Order> orders) {

        List<Order> ordersId = new ArrayList<>();
        for (Order order : orders) {
            Order orderId = new Order();
            orderId.setId(getOrderDBService().create(order));
            ordersId.add(orderId);
        }
        return ordersId;
    }

    /**
     * метод собирает все заказы клиентов в список, используя
     *
     * @param clients всех клиентов, которые сделали заказ
     * @param voucher путевку для этих клиентов
     * @param account аккаунт, через который совершена покупка
     * @return список заказов
     */
    private List<Order> buildOrders(List<Client> clients, Voucher voucher, Account account, Money money) {

        List<Order> orders = new ArrayList<>();
        for (Client client : clients) {
            Order order = buildOrder(client, voucher, account, money);
            orders.add(order);
        }
        return orders;
    }

    /**
     * собирает заказ из
     *
     * @param client  клиента
     * @param voucher его путевки
     * @param account аккаунта, через который совершена покупка
     * @return заказ
     */
    private Order buildOrder(Client client, Voucher voucher, Account account, Money money) {
        return new Order(client, voucher, account, money);
    }


    private Money getMoneyAfterSale(Money money, Voucher voucher) throws NoMoneyException {

        int moneyI = money.getMoney();
        int price = voucher.getPrice();

        if (moneyI >= price) {
            money.setMoney(moneyI - price);
        } else {
            throw new NoMoneyException(ResourceManager.getResource(Key.EX_NOT_ENOUGH_MONEY.getKey(), getCurrentLanguage()));
        }
        return money;
    }

    /**
     * метод добавляет клиентов в БД и
     * возвращает список клиентов с ID клиентов добавленных ранее
     *
     * @param clients список клиентов
     */
    private List<Client> createClients(List<Client> clients) {

        List<Client> clientsId = new ArrayList<>();
        for (Client client : clients) {
            Client clientId = new Client();
            clientId.setId(getClientDBService().create(client));
            clientsId.add(clientId);
        }
        return clientsId;
    }

}
