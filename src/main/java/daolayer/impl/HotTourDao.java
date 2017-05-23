package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.*;
import service.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotTourDao extends DAO<HotTour, Integer> {

    private static final Query READ_ALL = new Query(
            "SELECT\n" +
                    "  COUNTRIES.NAME,\n" +
                    "  CITIES.NAME,\n" +
                    "  HOTEL.NAME,\n" +
                    "  HOTEL.DESCRIPTION,\n" +
                    "  HOTEL.STARS_NUMBER,\n" +
                    "  TOUR.DESCRIPTION,\n" +
                    "  TOUR.DAYS,\n" +
                    "  TOUR.PRICE,\n" +
                    "  TOUR_ID," +
                    "  CITIES.CITY_ID," +
                    "  HOTEL.HOTEL_ID," +
                    "  HOT_TOUR_ID " +
                    "FROM HOT_TOUR\n" +
                    "  INNER JOIN TOUR ON HOT_TOUR.TOUR_ID = TOUR.TOUR_ID " +
                    "INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID " +
                    "INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND " +
                    "COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID " +
                    "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID");

    private static final Query CREATE = new Query("INSERT INTO hot_tour(tour_id) VALUES (?)");

    @Override
    public Integer create(HotTour entity) {
        Connection connection = getConnection();
        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE.getQuery(), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Num.FIRST.getNum(), entity.getTour().getId());
            ps.execute();
            rs = ps.getGeneratedKeys();
            rs.next();
            result = rs.getInt(Num.FIRST.getNum());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return result;
    }

    @Override
    public HotTour read(Integer id) {
        return null;
    }

    @Override
    public boolean update(HotTour entity) {
        return false;
    }

    @Override
    public boolean delete(HotTour entity) {
        return false;
    }

    @Override
    public List<HotTour> readAll() {

        Connection connection = getConnection();
        List<HotTour> hotTours = new ArrayList<>();
        ResultSet rs = null;

        try (Statement ps = connection.createStatement()) {
            rs = ps.executeQuery(READ_ALL.getQuery());
            while (rs.next()) {
                HotTour hotTour = new HotTour();
                Tour tour = new Tour();
                City city = new City();
                Hotel hotel = new Hotel();
                Country country = new Country();

                country.setName(rs.getString(Num.FIRST.getNum()));
                city.setName(rs.getString(Num.SECOND.getNum()));
                hotel.setName(rs.getString(Num.THIRD.getNum()));
                hotel.setDescription(rs.getString(Num.FOURTH.getNum()));
                hotel.setStarsNumber(rs.getInt(Num.FIFTH.getNum()));
                tour.setDescription(rs.getString(Num.SIXTH.getNum()));
                tour.setDays(rs.getInt(Num.SEVENTH.getNum()));
                tour.setPrice(rs.getInt(Num.EIGHTH.getNum()));
                tour.setId(rs.getInt(Num.NINTH.getNum()));
                city.setId(rs.getInt(Num.TENTH.getNum()));
                hotel.setId(rs.getInt(Num.ELEVENTH.getNum()));
                hotTour.setId(rs.getInt(Num.TWELFTH.getNum()));

                city.setCountry(country);
                tour.setCity(city);
                tour.setHotel(hotel);
                hotTour.setTour(tour);
                hotTours.add(hotTour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return hotTours;
    }
}
