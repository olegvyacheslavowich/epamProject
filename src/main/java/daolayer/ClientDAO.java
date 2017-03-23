package daolayer;

import dbconnection.ConnectionPool;
import entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 20_ok on 19.03.2017.
 */
public class ClientDAO extends DAO<Client> {

    private static final String CHECK_LOG_PASS_QUERY = "SELECT * FROM ACCOUNTS WHERE login = ? AND password = ?";
    private static final String CREATE_CLIENT = "INSERT INTO client(name, surname, patronymic, year, city_id, mobile, email, account_id)\n" +
            "VALUES (?,?,?,?,?,?,?,?);";
    private static final String CHECK_CITY = "SELECT city_id FROM cities WHERE name LIKE ?";
    public static final String CHECK_ACCOUNT = "SELECT account_id FROM accounts WHERE login LIKE ?";


    private static final String URL = "jdbc:hsqldb:file:hypersqlDB/travel";
    private static final String USER_NAME = "SA";
    private static final String PASSWORD = "";

    private ConnectionPool pool = new ConnectionPool(URL, USER_NAME, PASSWORD);
    private Connection connection = pool.getConnection();

    public String getData(String parameter1, String parameter2) {

        String result = null;

        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(CHECK_LOG_PASS_QUERY)) {
            st.setString(1, parameter1);
            st.setString(2, parameter2);
            rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pool.returnToPull(connection);
        }
        return result;
    }

    public boolean create(Client entity) {

        AccountDAO accountDAO = new AccountDAO(connection);
        CityDAO cityDAO = new CityDAO(connection);
        boolean result = false;

        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(CREATE_CLIENT)) {
            st.setString(1, entity.getName());
            st.setString(2, entity.getSurname());
            st.setString(3, entity.getPatronymic());
            st.setString(4, entity.getYear());
            st.setInt(5, getField(cityDAO, entity.getCity(), entity, CHECK_CITY));
            st.setString(6, entity.getMobile());
            st.setString(7, entity.getEmail());
            st.setInt(8, getField(accountDAO, entity.getLogin(), entity, CHECK_ACCOUNT));
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            pool.returnToPull(connection);
        }

        return result;
    }


    /**
     * создает поле field или возвращает существующее
     *
     * @param dao - объект класса DAO
     * @param field - поле, id которого надо вернуть
     * @param entity - сущность
     * @param checkQuery - запрос для проверки
     * @return id поля field
     */
    private int getField(DAO<Client> dao, String field, Client entity, String checkQuery) {

        int result = checkField(field, checkQuery);
        if (result != -1) {
            return result;
        } else {
            dao.create(entity);
            result = checkField(field, checkQuery);
        }
        return result;
    }

    /**
     * Метод для проверки существования записи
     *
     * @param field      проверяемая запись
     * @param checkQuery запрос для проверки
     * @return если существует, то id записи в бд, иначе -1
     */

    public int checkField(String field, String checkQuery) {

        int result = -1;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(checkQuery)) {
            st.setString(1, field);
            rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            pool.returnToPull(connection);

        }
        return result;
    }

}
