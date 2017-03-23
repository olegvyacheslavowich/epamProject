package daolayer;

import entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 20_ok on 21.03.2017.
 */
public class AccountDAO extends DAO<Client> {

    private static final String CREATE_ACCOUNT = "INSERT INTO accounts(login, password) VALUES (?,?);";

    private Connection connection;

    public AccountDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(Client entity) {

        boolean result = false;
        ResultSet rs = null;

        try (PreparedStatement st = connection.prepareStatement(CREATE_ACCOUNT)) {
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
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
        }
        return result;
    }

    @Override
    public int checkField(String field, String checkQuery) {
        return 0;
    }

}
