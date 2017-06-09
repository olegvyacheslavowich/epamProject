package service;

import daolayer.DaoFactory;
import daolayer.impl.MoneyDao;
import entity.Account;
import entity.CreditCard;
import entity.Money;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;
import exception.ServiceException;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

/**
 * Created by 20_ok on 17.05.2017.
 */
public class MoneyService {

    public boolean update(Money money) throws DataBaseConnectionException {

        boolean result;
        try (DaoFactory factory = new DaoFactory()) {
            MoneyDao moneyDao = factory.getMoneyDao();
            result = moneyDao.update(money);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }

        return result;
    }

    public Money read(int id) throws DataBaseConnectionException {

        Money money;
        try (DaoFactory factory = new DaoFactory()) {
            MoneyDao moneyDao = factory.getMoneyDao();
            money = moneyDao.read(id);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return money;
    }

    public int create(Money money) throws DataBaseConnectionException {

        int id;
        try (DaoFactory factory = new DaoFactory()) {
            MoneyDao moneyDao = factory.getMoneyDao();
            id = moneyDao.create(money);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }

        return id;
    }

    public List<Money> readByLogin(Account account) throws DataBaseConnectionException {

        List<Money> moneys;
        try (DaoFactory factory = new DaoFactory()) {
            MoneyDao moneyDao = factory.getMoneyDao();
            moneys = moneyDao.readByLogin(account);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);

        }
        return moneys;
    }
}
