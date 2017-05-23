package service.dbservice.impl;

import daolayer.impl.AccountDAO;
import entity.Account;
import service.dbservice.DBService;

import javax.servlet.http.HttpSession;

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
