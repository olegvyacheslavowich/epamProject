package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.City;
import entity.Country;
import entity.Hotel;
import service.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 20_ok on 06.05.2017.
 */
public class HotelDao extends DAO<Hotel, Integer> {

    private Connection connection = getConnection();
    private static Query READ_ALL_BY_CITY_AND_STARS = new Query("");

    @Override
    public Integer create(Hotel entity) {
        return null;
    }

    @Override
    public Hotel read(Integer id) {
        return null;
    }

    @Override
    public boolean update(Hotel entity) {
        return false;
    }

    @Override
    public boolean delete(Hotel entity) {
        return false;
    }

    @Override
    public List<Hotel> readAll() {
        return null;
    }

    public List<Hotel> readAllByCountryCityStars(String countryName, String cityName, int stars) {

        ArrayList<Hotel> hotels = new ArrayList<>();
        ResultSet rs;
        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_CITY_AND_STARS.getQuery())) {
            ps.setString(Num.FIRST.getNum(), countryName);
            ps.setString(Num.SECOND.getNum(), cityName);
            ps.setInt(Num.THIRD.getNum(), stars);
            rs = ps.executeQuery();
            while (rs.next()) {
                Hotel hotel = new Hotel();
                Country country = new Country();
                country.setName(rs.getString(Num.FIRST.getNum()));
                City city = new City();
                city.setName(rs.getString(Num.SECOND.getNum()));
                hotel.setDescription(rs.getString(Num.SECOND.getNum()));
                hotel.setStarsNumber(rs.getInt(Num.THIRD.getNum()));
                hotels.add(hotel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }
}
