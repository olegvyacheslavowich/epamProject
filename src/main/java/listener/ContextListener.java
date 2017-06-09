package listener;

import daolayer.Dao;
import daolayer.DaoFactory;
import dbconnection.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextListener implements ServletContextListener {

    private static final String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    private static final String URL = "jdbc:hsqldb:file:hypersqlDB/travel";
    private static final String USER_NAME = "SA";
    private static final String PASSWORD = "";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ConnectionPool pool = ConnectionPool.getInstance(DRIVER, URL, USER_NAME, PASSWORD);
        DaoFactory.setPool(pool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
