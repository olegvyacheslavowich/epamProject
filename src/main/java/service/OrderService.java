package service;

import constant.Number;
import daolayer.DaoFactory;
import daolayer.impl.OrderDao;
import entity.*;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;
import exception.ServiceException;

import java.util.List;

public class OrderService {

    public Order read(int id) throws DataBaseConnectionException {

        Order order;
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            order = orderDao.read(id);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return order;
    }

    public boolean delete(Order order) throws DataBaseConnectionException {

        boolean result;
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            result = orderDao.delete(order);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);

        }
        return result;
    }

    public List<Order> readAll() throws DataBaseConnectionException {

        List<Order> orders;
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            orders = orderDao.readAll();
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return orders;
    }

    public int create(Order order) throws ServiceException {

        int id;
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            id = orderDao.create(order);
        } catch (ConnectionPoolException e) {
            throw new ServiceException(e.getMessage());
        }
        return id;
    }

    public List<Order> getAllByAccount(Account account) throws DataBaseConnectionException {

        List<Order> orders;
        try (DaoFactory factory = new DaoFactory()) {
            OrderDao orderDao = factory.getOrderDao();
            orders = orderDao.readAllByAccount(account);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return orders;
    }


    public void deleteOrder(Order order, Client client, Voucher voucher, Money money) throws DataBaseConnectionException {

        ClientService clientService = new ClientService();
        MoneyService moneyService = new MoneyService();
        VoucherService voucherService = new VoucherService();

        delete(order);
        clientService.delete(client);
        money = updateMoney(money, voucher);
        if (voucher.getClientNumber() == Number.FIRST) {
            moneyService.update(money);
            voucherService.delete(voucher);
        } else {
            voucher = updateVoucher(voucher);
            voucherService.update(voucher);
            moneyService.update(money);
        }
    }

    private Money updateMoney(Money money, Voucher voucher) {

        int tourPrice = voucher.getTour().getPrice();
        int currentMoney = money.getMoney();
        money.setMoney(currentMoney + tourPrice);

        return money;
    }

    private Voucher updateVoucher(Voucher voucher) {

        int clientNumber = voucher.getClientNumber();
        int tourPrice = voucher.getTour().getPrice();
        int voucherPrice = voucher.getPrice();
        voucher.setClientNumber(clientNumber - Number.FIRST);
        voucher.setPrice(voucherPrice - tourPrice);

        return voucher;
    }

}
