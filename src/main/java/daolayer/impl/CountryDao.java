package daolayer.impl;

import com.sun.org.apache.regexp.internal.RE;
import constant.Num;
import daolayer.DAO;
import entity.Country;
import service.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 20_ok on 06.05.2017.
 */
public class CountryDao extends DAO<Country, Integer> {

    private Connection connection = getConnection();
    private static Query READ_ALL = new Query("SELECT COUNTRIES.NAME FROM COUNTRIES ORDER BY COUNTRIES.NAME");

    @Override
    public Integer create(Country entity) {
        return null;
    }

    @Override
    public Country read(Integer id) {
        return null;
    }

    @Override
    public boolean update(Country entity) {
        return false;
    }

    @Override
    public boolean delete(Country entity) {
        return false;
    }

    @Override
    public List<Country> readAll() {

        List<Country> countries = new ArrayList<>();
        ResultSet rs;

        try (Statement st = connection.createStatement()) {
            rs = st.executeQuery(READ_ALL.getQuery());
            while (rs.next()) {
                Country country = new Country();
                country.setName(rs.getString(Num.FIRST.getNum()));
                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }
}
