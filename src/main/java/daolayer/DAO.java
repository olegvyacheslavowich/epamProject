package daolayer;

import dbconnection.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 20_ok on 21.03.2017.
 */

/**
 * класс Data Access Object(DAO) для работы с БД
 *
 * @param <E> тип сущности
 * @param <K> тип ключа
 */
public abstract class DAO<E, K> {

    private static final String URL = "jdbc:hsqldb:file:hypersqlDB/travel";
    private static final String USER_NAME = "SA";
    private static final String PASSWORD = "";
    private ConnectionPool pool = new ConnectionPool(URL, USER_NAME, PASSWORD);
    protected Connection connection = getConnection();

    public abstract K create(E entity);

    public abstract E read(K id);

    public abstract boolean update(E entity);

    public abstract boolean delete(E entity);

    public abstract List<E> readAll();


    protected final Connection getConnection() {

        return pool.getConnection();
    }

    protected final void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected final void close(Connection connection, ResultSet rs) {

        pool.returnToPull(connection);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
