package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Key;
import constant.Page;
import constant.Parameter;
import daolayer.impl.AccountDAO;
import daolayer.impl.UserDAO;
import entity.Account;
import entity.User;
import exception.DifferentPasswordsException;
import exception.ExistingAccountException;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by 20_ok on 20.03.2017.
 */
public class RegistrationCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest rq) {
        return null;
    }
/*
    private static final String EXCEPTION = "exception";
    private String currentLanguage;


    @Override
    public String execute(HttpServletRequest rq) {
        return registration(rq);
    }

    private String registration(HttpServletRequest rq) {

        currentLanguage = (String) rq.getSession().getAttribute(Attribute.LOCALE.getAttribute());
        String page;
        UserDAO userDAO = new UserDAO();

        try {
            User user = getUser(rq);
            userDAO.create(user);
            page = Page.INDEX.getPage();

        } catch (ExistingAccountException e) {
            rq.setAttribute(EXCEPTION, ResourceManager.getResource(Key.ACCOUNT_IS_EXIST.getKey(),currentLanguage));
            page = Page.REGISTRATION.getPage();
        } catch (DifferentPasswordsException e) {
            rq.setAttribute(EXCEPTION, ResourceManager.getResource(Key.PASS_ARE_DIFFERENT.getKey(),currentLanguage));
            page = Page.REGISTRATION.getPage();
        }

        return page;
    }

    private User getUser(HttpServletRequest rq) throws ExistingAccountException, DifferentPasswordsException {

        User user = new User.UserBuilder()
                .accountId(getAccountId(rq))
                .name(rq.getParameter(Parameter.NAME.getParameter()))
                .surname(rq.getParameter(Parameter.SURNAME.getParameter()))
                .patronymic(rq.getParameter(Parameter.PATRONYMIC.getParameter()))
                .year(Date.valueOf(rq.getParameter(Parameter.YEAR.getParameter())))
                .city(rq.getParameter(Parameter.CITY.getParameter()))
                .mobile(rq.getParameter(Parameter.MOBILE.getParameter()))
                .email(rq.getParameter(Parameter.EMAIL.getParameter()))
                .build();

        return user;

    }

    private int getAccountId(HttpServletRequest rq) throws ExistingAccountException, DifferentPasswordsException {

        Account account = new Account();
        AccountDAO accountDAO = new AccountDAO();
        account.setLogin(rq.getParameter(Parameter.LOGIN.getParameter()));
        account.setPassword(rq.getParameter(Parameter.PASSWORD.getParameter()));

        comparePass(account.getPassword(), rq.getParameter(Parameter.REPEAT_PASSWORD.getParameter()));

        int id;
        if ((id = accountDAO.create(account)) != -1) {
            account.setId(id);
        } else {
            throw new ExistingAccountException(ResourceManager.getResource(Key.ACCOUNT_IS_EXIST.getKey(),currentLanguage));
        }

        return account.getId();
    }

    private void comparePass(String pass1, String pass2) throws DifferentPasswordsException {

        if (!Objects.equals(pass1, pass2)) {
            throw new DifferentPasswordsException(ResourceManager.getResource(Key.PASS_ARE_DIFFERENT.getKey(),currentLanguage));
        }
    }*/
}
