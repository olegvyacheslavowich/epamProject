import dbconnection.ConnectionPool;

/**
 * Created by 20_ok on 23.05.2017.
 */
public class Start {
    public static void main(String[] args) {
        String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
        String URL = "jdbc:hsqldb:file:hypersqlDB/travel";
        String USER_NAME = "SA";
        String PASSWORD = "";
        ConnectionPool pool = ConnectionPool.getInstance(DRIVER, URL, USER_NAME, PASSWORD);
        pool.getConnection();
    }
}
