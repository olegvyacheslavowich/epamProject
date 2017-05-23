package service.dbservice.impl;

import daolayer.impl.ClientDao;
import entity.Client;

/**
 * Created by 20_ok on 17.05.2017.
 */
public class ClientDBService {

    private ClientDao dao = new ClientDao();

    public boolean delete(Client client) {
        return dao.delete(client);
    }

    /**
     * добавляет клиента в БД и возвращает id добавленного клиента
     *
     * @param client клиент, которого нужно добавить
     * @return id добавленного клиента
     */
    public int create(Client client) {
        return dao.create(client);
    }


}
