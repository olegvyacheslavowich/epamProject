package service.dbservice.impl;

import daolayer.impl.OrderDao;
import entity.Account;
import entity.Order;

import java.util.List;

/**
 * Created by 20_ok on 17.05.2017.
 */
public class OrderDBService {

    private OrderDao dao = new OrderDao();

    public List<Order> getAllByAccount(Account account) {
        return dao.readAllByAccount(account);
    }

    public Order read(int id) {
        return dao.read(id);
    }

    public void delete(Order order) {
        dao.delete(order);
    }

    public List<Order> readAll() {
        return dao.readAll();
    }

    /**
     * добавляет заказ в БД и возвращает id добавленного заказа
     *
     * @param order заказ, который нужно добавить
     * @return id добавленного заказа
     */
    public int create(Order order) {
        return dao.create(order);
    }

}
