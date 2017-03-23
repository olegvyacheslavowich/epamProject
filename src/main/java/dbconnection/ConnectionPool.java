package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 20_ok
 */
public class ConnectionPool {

    private static final int DEFAULT_CAPACITY = 10;

    private static ConnectionPool connection;
    static ArrayBlockingQueue<Connection> connections = new ArrayBlockingQueue<>(DEFAULT_CAPACITY);
    private String url;
    private String user;
    private String password;

    public ConnectionPool(String url, String user, String password, int capacity) {
        this.url = url;
        this.user = user;
        this.password = password;
        connections = new ArrayBlockingQueue<>(capacity);

    }

    public ConnectionPool(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Получить свободный connection
     *
     * @return connection
     * @throws SQLException
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
            System.out.println("new connect");
            connection = newConnection();
        }

        return connection;
    }

    /**
     * Конструктор с заданием размера пула
     *
     * @param url
     * @param capacity размер пула
     * @return объект ConnectionPool (вернуть существующий, либо создать новый)
     */
    public static ConnectionPool getInstance(String url, String user, String password, int capacity) {

        if (connection == null) {
            connection = new ConnectionPool(url, user, password, capacity);
        }

        return connection;
    }

    /**
     * Конструктор с дэфолтным значением (10) Singleton
     *
     * @param url
     * @return объект ConnectionPool (вернуть существующий, либо создать новый)
     */
    public static ConnectionPool getInstance(String url, String user, String password) {

        if (connection == null) {
            connection = new ConnectionPool(url, user, password);
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
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Congatulations");
        } catch (SQLException e) {
            System.out.println("exception");
            e.printStackTrace();
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * вернуть connection в пул
     *
     * @param connection
     */
    public void returnToPull(Connection connection) {

        if ((connection != null) && (connections.remainingCapacity() != 0)) {
            connections.add(connection);
        }

    }

    /**
     * Закрыть все connection и почисить пул
     */
    public void closeAll() {

        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }

        connections.clear();
    }

}

