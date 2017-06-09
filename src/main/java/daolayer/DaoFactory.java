package daolayer;

import daolayer.impl.*;
import dbconnection.ConnectionPool;
import exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.SQLException;


public class DaoFactory implements AutoCloseable {

    private static ConnectionPool pool;
    private Connection connection;

    public DaoFactory() throws ConnectionPoolException {
        if (pool == null) {
            throw new ConnectionPoolException("Connection pool was not initialize");
        }
        this.connection = pool.getConnection();
        Dao.connection = this.connection;
    }

    public AccountDao getAccountDao() {
        return new AccountDao();
    }

    public AdminDao getAdminDao() {
        return new AdminDao();
    }

    public CardTypeDao getCardTypeDao() {
        return new CardTypeDao();
    }

    public ClientDao getClientDao() {
        return new ClientDao();
    }

    public CountryDao getCountryDao() {
        return new CountryDao();
    }

    public CreditCardDao getCreditCardDao() {
        return new CreditCardDao();
    }

    public FlightDao getFlightDao() {
        return new FlightDao();
    }

    public HotTourDao getHotTourDao() {
        return new HotTourDao();
    }

    public MoneyDao getMoneyDao() {
        return new MoneyDao();
    }

    public OrderDao getOrderDao() {
        return new OrderDao();
    }

    public TourDao getTourDao() {
        return new TourDao();
    }

    public UserDao getUserDao() {
        return new UserDao();
    }

    public VoucherDao getVoucherDao() {
        return new VoucherDao();
    }

    public static void setPool(ConnectionPool pool) {
        DaoFactory.pool = pool;
    }

    public static ConnectionPool getPool() {
        return DaoFactory.pool;
    }

    public void startTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ;

    public void finishTransaction() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ;

    @Override
    public void close() {
        pool.returnToPoll(Dao.connection);
    }
}
