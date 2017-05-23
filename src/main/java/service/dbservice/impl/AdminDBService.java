package service.dbservice.impl;

import daolayer.impl.AdminDao;
import entity.Account;
import entity.Admin;
import service.dbservice.DBService;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 19.05.2017.
 */
public class AdminDBService extends DBService {

    private AdminDao dao = new AdminDao();

    public AdminDBService(HttpSession session) {
        super(session);
    }

    public Admin readByLogin(Admin admin) {
        return dao.readByLogin(admin);
    }
}
