package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 20_ok on 06.05.2017.
 */
public class CountryDao extends Dao<Country, Integer> {

    private static String READ_ALL = "SELECT COUNTRIES.NAME FROM COUNTRIES ORDER BY COUNTRIES.NAME";

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
        ResultSet rs = null;

        try (Statement st = connection.createStatement()) {
            rs = st.executeQuery(READ_ALL);
            while (rs.next()) {
                Country country = new Country();
                country.setName(rs.getString(Number.FIRST));
                countries.add(country);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return countries;
    }

}
