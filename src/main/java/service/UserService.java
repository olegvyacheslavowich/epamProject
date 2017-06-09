package service;

import daolayer.DaoFactory;
import daolayer.impl.UserDao;
import entity.Account;
import entity.User;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;
import exception.ServiceException;


public class UserService {


    public User readUserByAccount(Account account) throws DataBaseConnectionException {

        User user;
        try (DaoFactory factory = new DaoFactory()) {
            UserDao dao = factory.getUserDao();
            user = dao.readByAccount(account);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return user;
    }

    public int create(User user) throws DataBaseConnectionException {

        int result;
        try (DaoFactory factory = new DaoFactory()) {
            UserDao dao = factory.getUserDao();
            result = dao.create(user);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return result;
    }

}
