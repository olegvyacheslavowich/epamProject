package service;

import daolayer.DaoFactory;
import daolayer.impl.AdminDao;
import entity.Admin;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;

public class AdminService {


    public Admin readByLogin(Admin admin) throws DataBaseConnectionException {

        try (DaoFactory factory = new DaoFactory()) {
            AdminDao adminDao = factory.getAdminDao();
            admin = adminDao.readByLogin(admin);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return admin;
    }
}
