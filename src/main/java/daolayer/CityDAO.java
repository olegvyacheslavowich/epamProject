package daolayer;

import entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 20_ok on 21.03.2017.
 */
public class CityDAO extends DAO<Client> {

    private static final String CREATE_CITY = "INSERT INTO cities(name) VALUES (?)";


    private Connection connection;

    public CityDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected boolean create(Client entity) {

        boolean result = false;

        try (PreparedStatement st = connection.prepareStatement(CREATE_CITY)) {
            st.setString(1, entity.getCity());
            result = st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
   public int checkField(String field, String checkQuery) {
        return 0;
    }
}
