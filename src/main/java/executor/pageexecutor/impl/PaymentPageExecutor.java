package executor.pageexecutor.impl;

import constant.Attribute;
import constant.Page;
import daolayer.impl.ClientDao;
import daolayer.impl.OrderDao;
import daolayer.impl.VoucherDao;
import entity.*;
import exception.NoMoneyException;
import executor.pageexecutor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class PaymentPageExecutor extends PageExecutor {

    private String page = Page.INDEX.getPage();
    private Voucher voucher;
    private List<Client> clients;
    private User user;
    private int moneyId;

    public PaymentPageExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public void read() {
        user = (User) readFromSession(Attribute.USER.getAttribute());
        voucher = (Voucher) readFromSession(Attribute.VOUCHER.getAttribute());
        clients = (List<Client>) readFromSession(Attribute.CLIENTS.getAttribute());
        moneyId = Integer.parseInt(readFromRequest(Attribute.MONEY_ID.getAttribute()));
    }

    @Override
    public void validate() {
    }

    @Override
    public void build() {

        Money money = getMoneyDBService().read(moneyId);
        try {
            money = getMoneyAfterSale(money, voucher);
            Account accountId = user.getAccount();
            Voucher voucherId = new Voucher();
            voucherId.setId(createVoucher(voucher));
            List<Client> clients = createClients(this.clients);
            List<Order> orders = buildOrders(clients, voucherId, accountId, money);
            createOrders(orders);
            getMoneyDBService().update(money);
        } catch (NoMoneyException e) {
            setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            page = Page.FLIGHT.getPage();
        }

    }

    @Override
    public void write() {

    }

    @Override
    public String returnPage() {
        return page;
    }

    /**
     * добавляет клиента в БД и возвращает id добавленного клиента
     *
     * @param client клиент, которого нужно добавить
     * @return id добавленного клиента
     */
    private int createClient(Client client) {

        ClientDao dao = new ClientDao();
        return dao.create(client);
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
            clientId.setId(createClient(client));
            clientsId.add(clientId);
        }
        return clientsId;
    }


    /**
     * добавляет путевку в БД и возвращает id добавленной путевки
     *
     * @param voucher путевка, которую нужно добавить
     * @return id добавленной путевки
     */
    private int createVoucher(Voucher voucher) {

        VoucherDao dao = new VoucherDao();
        return dao.create(voucher);
    }

    /**
     * добавляет заказ в БД и возвращает id добавленного заказа
     *
     * @param order заказ, который нужно добавить
     * @return id добавленного заказа
     */
    private int createOrder(Order order) {

        OrderDao dao = new OrderDao();
        return dao.create(order);
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
            orderId.setId(createOrder(order));
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
            throw new NoMoneyException("Недостаточно денег на счету");
        }
        return money;
    }


}
