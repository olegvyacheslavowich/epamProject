package dbconnection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 20_ok
 */
public class ConnectionPool {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private static final int DEFAULT_CAPACITY = 10;

    private static ConnectionPool connection;
    static ArrayBlockingQueue<Connection> connections = new ArrayBlockingQueue<>(DEFAULT_CAPACITY);
    private String driver;
    private String url;
    private String user;
    private String password;

    private ConnectionPool() {
    }

    private ConnectionPool(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Получить свободный connection
     *
     * @return connection
     */
    public Connection getConnection() {
        Connection connection;

        if (!connections.isEmpty()) {
            connection = connections.poll();
            try {
                if (connection.isClosed()) {
                    connection = getConnection();
                }
            } catch (SQLException e) {
                connection = getConnection();
            }
        } else {
            connection = newConnection();
        }
        logger.info("Connection was added");
        return connection;
    }


    /**
     * Конструктор с дэфолтным значением (10) Singleton
     *
     * @param url
     * @return объект ConnectionPool (вернуть существующий, либо создать новый)
     */
    public static ConnectionPool getInstance(String driver, String url, String user, String password) {

        if (connection == null) {
            connection = new ConnectionPool(driver, url, user, password);
        }

        return connection;
    }

    /**
     * создать новый connection
     *
     * @return
     */
    private Connection newConnection() {

        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            logger.info("Class did not find");
        } catch (SQLException e) {
            logger.info("Connection error");
        }

        return connection;
    }

    /**
     * вернуть connection в пул
     *
     * @param connection
     */
    public void returnToPoll(Connection connection) {

        if ((connection != null) && (connections.remainingCapacity() != 0)) {
            connections.add(connection);
            logger.info("Connection was returned to the pool");
        }

    }


}

