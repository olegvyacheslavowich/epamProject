package service;

import constant.Key;
import daolayer.DaoFactory;
import daolayer.impl.ClientDao;
import daolayer.impl.MoneyDao;
import daolayer.impl.OrderDao;
import daolayer.impl.VoucherDao;
import entity.*;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;
import exception.NoMoneyException;
import exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    public void payOrder(int moneyId, Voucher voucher, List<Client> clients, Account account) throws ServiceException, DataBaseConnectionException {

        try (DaoFactory factory = new DaoFactory()) {
            MoneyDao moneyDao = factory.getMoneyDao();
            VoucherDao voucherDao = factory.getVoucherDao();
            ClientDao clientDao = factory.getClientDao();
            OrderDao orderDao = factory.getOrderDao();
            factory.startTransaction();
            Money money = moneyDao.read(moneyId);
            money = getMoneyAfterSale(money, voucher);
            voucher.setId(voucherDao.create(voucher));
            clients = createClients(clients, clientDao);
            List<Order> orders = buildOrders(clients, voucher, account, money);
            createOrders(orders, orderDao);
            moneyDao.update(money);
            factory.finishTransaction();
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }

    }

    /**
     * метод добавляет клиентов в БД и
     * возвращает список клиентов с ID клиентов добавленных ранее
     *
     * @param clients список клиентов
     */
    private List<Client> createClients(List<Client> clients, ClientDao clientDao) throws ServiceException {

        List<Client> clientsId = new ArrayList<>();
        for (Client client : clients) {
            Client clientId = new Client();
            clientId.setId(clientDao.create(client));
            clientsId.add(clientId);
        }
        return clientsId;
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


    private Money getMoneyAfterSale(Money money, Voucher voucher) throws ServiceException {

        int moneyI = money.getMoney();
        int price = voucher.getPrice();

        if (moneyI >= price) {
            money.setMoney(moneyI - price);
        } else {
            throw new ServiceException(Key.EX_NOT_ENOUGH_MONEY);
        }
        return money;
    }

    /**
     * добавляет заказы в БД и возвращает список заказов с ID
     * заказов, добавленных ранее
     *
     * @param orders заказы
     */
    private List<Order> createOrders(List<Order> orders, OrderDao orderDao) throws ServiceException {

        List<Order> ordersId = new ArrayList<>();
        for (Order order : orders) {
            Order orderId = new Order();
            orderId.setId(orderDao.create(order));
            ordersId.add(orderId);
        }
        return ordersId;
    }

}
