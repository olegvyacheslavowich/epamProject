package service;

import daolayer.DaoFactory;
import daolayer.impl.ClientDao;
import entity.Client;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;


public class ClientService {


    public boolean delete(Client client) throws DataBaseConnectionException {

        boolean result;
        try (DaoFactory daoFactory = new DaoFactory()) {
            ClientDao clientDao = daoFactory.getClientDao();
            result = clientDao.delete(client);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return result;
    }

    /**
     * добавляет клиента в БД и возвращает id добавленного клиента
     *
     * @param client клиент, которого нужно добавить
     * @return id добавленного клиента
     */
    public int create(Client client) throws DataBaseConnectionException {

        int id;
        try (DaoFactory daoFactory = new DaoFactory()) {
            ClientDao clientDao = daoFactory.getClientDao();
            id = clientDao.create(client);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }

        return id;
    }


}
