package executor.impl.login.impl;

import constant.Key;
import executor.impl.login.LoginExecutor;
import constant.Attribute;
import constant.Page;
import entity.Account;
import entity.Admin;
import entity.User;
import exception.*;
import org.apache.commons.codec.digest.DigestUtils;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BuildLoginExecutor extends LoginExecutor {

    private LoginExecutor next;

    public BuildLoginExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String login, String password) throws EmptyFieldException,
            NoCreditCardsException, NoFlightsException, NoToursException,
            IncorrectDataException, WrongAccountException {

        String page = Page.INDEX.getPage();
        password = DigestUtils.md5Hex(password);

        Account account = checkAccount(login, password);
        User user = getUserDBService().readUserByAccount(account);
        Admin admin = new Admin(account);
        admin = getAdminDBService().readByLogin(admin);

        setToSession(Attribute.USER.getAttribute(), user);
        setToSession(Attribute.ADMIN.getAttribute(), admin);
        removeFromSession(Attribute.TOURS.getAttribute());

        if (next != null) {
            page = next.execute(login, password);
        }

        return page;
    }

    public void setNext(LoginExecutor next) {
        this.next = next;
    }

    private Account checkAccount(String login, String password) throws WrongAccountException {

        Account account = getAccountDBService().readAccountByLoginAndPassword(login, password);
        if (account == null) {
            throw new WrongAccountException(ResourceManager.getResource(Key.EX_WRONG_ACCOUNT.getKey(), getCurrentLanguage()));
        }
        return account;
    }


}
