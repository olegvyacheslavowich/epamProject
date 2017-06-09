package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HotTourDao extends Dao<HotTour, Integer> {

    private static final String READ_ALL =
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
                    "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID";

    private static final String CREATE = "INSERT INTO hot_tour(tour_id) VALUES (?)";

    @Override
    public Integer create(HotTour entity) {
        int result = -1;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(Number.FIRST, entity.getTour().getId());
            ps.execute();
            rs = ps.getGeneratedKeys();
            rs.next();
            result = rs.getInt(Number.FIRST);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
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

        List<HotTour> hotTours = new ArrayList<>();
        ResultSet rs = null;

        try (Statement ps = connection.createStatement()) {
            rs = ps.executeQuery(READ_ALL);
            while (rs.next()) {
                HotTour hotTour = new HotTour();
                Tour tour = new Tour();
                City city = new City();
                Hotel hotel = new Hotel();
                Country country = new Country();

                country.setName(rs.getString(Number.FIRST));
                city.setName(rs.getString(Number.SECOND));
                hotel.setName(rs.getString(Number.THIRD));
                hotel.setDescription(rs.getString(Number.FOURTH));
                hotel.setStarsNumber(rs.getInt(Number.FIFTH));
                tour.setDescription(rs.getString(Number.SIXTH));
                tour.setDays(rs.getInt(Number.SEVENTH));
                tour.setPrice(rs.getInt(Number.EIGHTH));
                tour.setId(rs.getInt(Number.NINTH));
                city.setId(rs.getInt(Number.TENTH));
                hotel.setId(rs.getInt(Number.ELEVENTH));
                hotTour.setId(rs.getInt(Number.TWELFTH));

                city.setCountry(country);
                tour.setCity(city);
                tour.setHotel(hotel);
                hotTour.setTour(tour);
                hotTours.add(hotTour);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return hotTours;
    }
}
