package service.dbservice.impl;

import daolayer.impl.UserDAO;
import entity.Account;
import entity.User;
import service.dbservice.DBService;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 17.05.2017.
 */
public class UserDBService extends DBService {

    private UserDAO dao = new UserDAO();

    public UserDBService(HttpSession session) {
        super(session);
    }

    public User readUserByAccount(Account account) {
        return dao.readByAccount(account);
    }

    public int create(User user){
        return dao.create(user);
    }

}
