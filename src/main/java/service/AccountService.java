package service;

import constant.Key;
import daolayer.DaoFactory;
import daolayer.impl.AccountDao;
import entity.Account;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;
import exception.ServiceException;
import exception.ValidationException;
import org.apache.commons.codec.digest.DigestUtils;
import validation.Validator;

public class AccountService {

    public Account readAccountByLoginAndPassword(String login, String password) throws DataBaseConnectionException {

        Account account;
        try (DaoFactory daoFactory = new DaoFactory()) {
            AccountDao accountDao = daoFactory.getAccountDao();
            account = accountDao.readByLoginAndPassword(login, password);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return account;
    }

    public String create(Account account) throws DataBaseConnectionException {

        String result;
        try (DaoFactory daoFactory = new DaoFactory()) {
            AccountDao accountDao = daoFactory.getAccountDao();
            result = accountDao.create(account);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }

        return result;
    }

    public Account checkAccount(String login, String password) throws DataBaseConnectionException, ValidationException, ServiceException {

        if (!Validator.isEngString(login)) {
            throw new ValidationException(Key.EX_INCORRECT_LOGIN);
        } else if (!Validator.isPassword(password)) {
            throw new ValidationException(Key.EX_INCORRECT_PASSWORD);
        }

        password = DigestUtils.md5Hex(password);
        Account account = readAccountByLoginAndPassword(login, password);
        if (account == null) {
            throw new ServiceException(Key.EX_WRONG_ACCOUNT);
        }
        return account;
    }

}
