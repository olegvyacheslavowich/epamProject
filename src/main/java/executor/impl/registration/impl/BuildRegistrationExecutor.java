package executor.impl.registration.impl;

import constant.Attribute;
import constant.Key;
import constant.Page;
import entity.Account;
import entity.Admin;
import entity.User;
import exception.DifferentPasswordsException;
import exception.EmptyFieldException;
import exception.ExistingAccountException;
import exception.IncorrectDataException;
import executor.impl.registration.RegistrationExecutor;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class BuildRegistrationExecutor extends RegistrationExecutor {

    public BuildRegistrationExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String fullName, String paper,
                          String documentNum, String phone, String birthday,
                          String email, String login, String password,
                          String rpassword) throws EmptyFieldException, IncorrectDataException,
            ExistingAccountException, DifferentPasswordsException {

        String page = Page.CARD.getPage();

        if (checkLogin(login, password, rpassword)) {
            Account account = new Account(login, password);
            User user = new User.UserBuilder()
                    .fullName(fullName)
                    .paper(paper)
                    .documentNum(Long.parseLong(documentNum))
                    .phone(phone)
                    .birthday(Date.valueOf(birthday))
                    .email(email)
                    .account(account)
                    .build();
            getAccountDBService().create(account);
            getUserDBService().create(user);
            user = getUserDBService().readUserByAccount(account);
            Admin admin = new Admin(account);
            admin = getAdminDBService().readByLogin(admin);

            setToSession(Attribute.USER.getAttribute(), user);
            setToSession(Attribute.ADMIN.getAttribute(), admin);
        }

        return page;
    }

    private boolean checkPasswords(String password, String rpassword) throws DifferentPasswordsException {

        if (!password.equals(rpassword)) {
            throw new DifferentPasswordsException(ResourceManager.getResource(Key.EX_DIFFERENT_PASSWORDS.getKey()));
        }
        return true;
    }

    private boolean checkLogin(String login, String password, String rpassword) throws DifferentPasswordsException, ExistingAccountException {

        Account account = null;
        if (checkPasswords(password, rpassword)) {
            account = getAccountDBService().readAccountByLoginAndPassword(login, password);
        }
        if (account != null) {
            throw new ExistingAccountException(ResourceManager.getResource(Key.EX_ACCOUNT_IS_EXIST.getKey()));
        }

        return true;
    }
}
