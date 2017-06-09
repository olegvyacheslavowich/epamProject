package daolayer;

import com.sun.istack.internal.logging.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * класс Data Access Object(Dao) для работы с БД
 *
 * @param <E> тип сущности
 * @param <K> тип ключа
 */
public abstract class Dao<E, K> {

    protected Logger logger = Logger.getLogger(Dao.class);
    protected static Connection connection;

    public abstract K create(E entity);

    public abstract E read(K id);

    public abstract boolean update(E entity);

    public abstract boolean delete(E entity);

    public abstract List<E> readAll();



    protected final void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
    }

    protected void start() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






}
