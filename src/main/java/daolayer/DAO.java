package daolayer;

import com.sun.istack.internal.logging.Logger;
import dbconnection.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * класс Data Access Object(DAO) для работы с БД
 *
 * @param <E> тип сущности
 * @param <K> тип ключа
 */
public abstract class DAO<E, K> {

    protected Logger logger = Logger.getLogger(DAO.class);

    private static final String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    private static final String URL = "jdbc:hsqldb:file:hypersqlDB/travel";
    private static final String USER_NAME = "SA";
    private static final String PASSWORD = "";
    private ConnectionPool pool = ConnectionPool.getInstance(DRIVER, URL, USER_NAME, PASSWORD);

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
                logger.info(e.getMessage());
            }
        }
    }

    protected final void close(Connection connection, ResultSet rs) {

        pool.returnToPoll(connection);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
    }

    protected final void close(Connection connection) {

        pool.returnToPoll(connection);
    }
}
