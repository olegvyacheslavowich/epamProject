package service.dbservice.impl;

import daolayer.impl.MoneyDao;
import entity.Account;
import entity.Money;
import exception.NoCreditCardsException;

import java.util.List;

/**
 * Created by 20_ok on 17.05.2017.
 */
public class MoneyDBService {

    private MoneyDao dao = new MoneyDao();

    public List<Money> readByLogin(Account account) {
        return dao.readByLogin(account);
    }

    public boolean update(Money money) {
        return dao.update(money);
    }

    public Money read(int id) {

        return dao.read(id);
    }

    public int create(Money money) {
        return dao.create(money);
    }
}
