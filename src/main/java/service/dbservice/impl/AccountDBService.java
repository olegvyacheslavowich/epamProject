package service.dbservice.impl;

import constant.Key;
import daolayer.impl.AccountDAO;
import entity.Account;
import exception.WrongAccountException;
import service.ResourceManager;
import service.dbservice.DBService;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 17.05.2017.
 */
public class AccountDBService extends DBService {

    private AccountDAO dao = new AccountDAO();

    public AccountDBService(HttpSession session) {
        super(session);
    }

    public Account readAccountByLoginAndPassword(String login, String password) {
        return dao.readByLoginAndPassword(login, password);
    }

    public String create(Account account) {
        return dao.create(account);
    }
}
