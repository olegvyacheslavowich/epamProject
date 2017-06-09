package service;

import constant.Key;
import daolayer.DaoFactory;
import daolayer.impl.AccountDao;
import daolayer.impl.UserDao;
import entity.Account;
import entity.User;
import exception.*;
import validation.Validator;

import java.sql.Date;

/**
 * Created by 20_ok on 6/9/2017.
 */
public class RegistrationService {


    public Account register(String fullName, String paper,
                            String documentNum, String phone, String birthday,
                            String email, String login, String password,
                            String rpassword) throws ServiceException, ValidationException, DataBaseConnectionException {

        if (!Validator.isRusEngString(fullName)) {
            throw new ValidationException(Key.EX_INCORRECT_LOGIN);
        } else if (!Validator.isRusEngString(paper)) {
            throw new ValidationException(Key.EX_INCORRECT_PAPER);
        } else if (!Validator.isNumber(paper)) {
            throw new ValidationException(Key.EX_INCORRECT_PAPER_NUM);
        } else if (!Validator.isMobileNumber(paper)) {
            throw new ValidationException(Key.EX_INCORRECT_PHONE);
        } else if (!Validator.isDate(paper)) {
            throw new ValidationException(Key.EX_INCORRECT_BIRTHDAY);
        } else if (!Validator.isEmail(paper)) {
            throw new ValidationException(Key.EX_INCORRECT_EMAIL);
        } else if (!Validator.isEngString(paper)) {
            throw new ValidationException(Key.EX_INCORRECT_LOGIN);
        } else if (!Validator.isPassword(paper)) {
            throw new ValidationException(Key.EX_INCORRECT_PASSWORD);
        } else if (!Validator.isPassword(paper)) {
            throw new ValidationException(Key.EX_INCORRECT_PASSWORD);
        }
        Account account = null;
        if (checkLogin(login, password, rpassword)) {
            account = new Account(login, password);
            User user = new User.UserBuilder()
                    .fullName(fullName)
                    .paper(paper)
                    .documentNum(Long.parseLong(documentNum))
                    .phone(phone)
                    .birthday(Date.valueOf(birthday))
                    .email(email)
                    .account(account)
                    .build();
            try (DaoFactory factory = new DaoFactory()) {
                AccountDao accountDao = factory.getAccountDao();
                UserDao userDao = factory.getUserDao();
                factory.startTransaction();
                accountDao.create(account);
                userDao.create(user);
                factory.finishTransaction();
            } catch (ConnectionPoolException e) {
                throw new DataBaseConnectionException(e);
            }
        }
        return account;
    }

    private boolean checkPasswords(String password, String rpassword) throws ServiceException {

        if (!password.equals(rpassword)) {
            throw new ServiceException(Key.EX_DIFFERENT_PASSWORDS);
        }
        return true;
    }

    private boolean checkLogin(String login, String password, String rpassword) throws ServiceException, DataBaseConnectionException {

        AccountService accountService = new AccountService();
        Account account = null;
        if (checkPasswords(password, rpassword)) {
            account = accountService.readAccountByLoginAndPassword(login, password);
        }
        if (account != null) {
            throw new ServiceException(Key.EX_ACCOUNT_IS_EXIST);
        }

        return true;
    }


}
