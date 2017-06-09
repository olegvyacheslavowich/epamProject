package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.City;
import entity.Country;
import entity.Hotel;
import entity.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TourDao extends Dao<Tour, Integer> {

    private static final String UPDATE =
            "UPDATE tour SET TOUR_ID = ?, CITY_ID = ?, DESCRIPTION = ?, DAYS = ?, PRICE = ?, HOTEL_ID = ?" +
                    "WHERE tour.tour_id = ?";

    private static final String READ_ALL = "SELECT\n" +
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
            "  HOTEL.HOTEL_ID \n" +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "    COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID";

    private static final String READ =
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
                    "  HOTEL.HOTEL_ID \n" +
                    "FROM TOUR\n" +
                    "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
                    "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
                    "    COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
                    "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
                    "WHERE TOUR_ID = ?";

    private static final String READ_ALL_BY_COUNTRY_AND_HOTEL = "SELECT\n" +
            "  COUNTRIES.NAME,\n" +
            "  CITIES.NAME,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS," +
            "  TOUR.PRICE,\n" +
            "  TOUR_ID," +
            "  CITIES.CITY_ID," +
            "  HOTEL.HOTEL_ID \n" +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "    COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "WHERE COUNTRIES.NAME = ? AND HOTEL.STARS_NUMBER = ?\n" +
            "\n";

    private static final String READ_ALL_BY_COUNTRY =
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
                    "  HOTEL.HOTEL_ID \n" +
                    "FROM TOUR\n" +
                    "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
                    "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
                    "    COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
                    "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
                    "WHERE COUNTRIES.NAME = ?";

    private static final String READ_ALL_BY_DATE =
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
                    "  HOTEL.HOTEL_ID \n" +
                    "FROM TOUR\n" +
                    "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
                    "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
                    "                          COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
                    "  INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
                    "  INNER JOIN PLANE ON CITIES.CITY_ID = PLANE.ARRIVAL_CITY_ID\n" +
                    "  INNER JOIN FLIGHT ON PLANE.NAME = FLIGHT.PLANE_ID\n" +
                    "WHERE FLIGHT.DATE = ?";

    private static final String READ_ALL_BY_HOTEL_STARS = "SELECT\n" +
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
            "  HOTEL.HOTEL_ID \n" +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "    COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "WHERE HOTEL.STARS_NUMBER = ?";

    private static final String READ_ALL_BY_COUNTRY_DATE =
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
                    "  HOTEL.HOTEL_ID \n" +
                    "FROM TOUR\n" +
                    "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
                    "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
                    "                          COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
                    "  INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
                    "  INNER JOIN PLANE ON CITIES.CITY_ID = PLANE.ARRIVAL_CITY_ID\n" +
                    "  INNER JOIN FLIGHT ON PLANE.NAME = FLIGHT.PLANE_ID\n" +
                    "WHERE FLIGHT.DATE = ? AND COUNTRIES.NAME = ?";

    private static final String READ_ALL_BY_COUNTRY_DATE_HOTEL =
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
                    "  HOTEL.HOTEL_ID \n" +
                    "FROM TOUR\n" +
                    "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
                    "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
                    "                          COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
                    "  INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
                    "  INNER JOIN PLANE ON CITIES.CITY_ID = PLANE.ARRIVAL_CITY_ID\n" +
                    "  INNER JOIN FLIGHT ON PLANE.NAME = FLIGHT.PLANE_ID\n" +
                    "WHERE COUNTRIES.NAME = ? AND FLIGHT.DATE = ?  AND HOTEL.STARS_NUMBER = ?";

    @Override
    public Integer create(Tour entity) {
        return null;
    }

    @Override
    public Tour read(Integer id) {

        Tour tour = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ)) {
            ps.setInt(Number.FIRST, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                tour = new Tour();
                Country country = new Country();
                City city = new City();
                Hotel hotel = new Hotel();

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

                city.setCountry(country);
                tour.setCity(city);
                tour.setHotel(hotel);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return tour;
    }

    @Override
    public boolean update(Tour entity) {

        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setInt(Number.FIRST, entity.getId());
            ps.setInt(Number.SECOND, entity.getCity().getId());
            ps.setString(Number.THIRD, entity.getDescription());
            ps.setInt(Number.FOURTH, entity.getDays());
            ps.setInt(Number.FIFTH, entity.getPrice());
            ps.setInt(Number.SIXTH, entity.getHotel().getId());
            ps.setInt(Number.SEVENTH, entity.getId());
            result = ps.execute();
        } catch (SQLException e) {
            logger.info(e.getMessage());

        }
        return result;
    }

    @Override
    public boolean delete(Tour entity) {
        return false;
    }

    @Override
    public List<Tour> readAll() {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (Statement ps = connection.createStatement()) {
            rs = ps.executeQuery(READ_ALL);
            setTours(tours, rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return tours;
    }


    public List<Tour> readAllByCountryAndDateAndHotelStars(String countryName, Date date, int stars) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_COUNTRY_DATE_HOTEL)) {
            ps.setString(Number.FIRST, countryName);
            ps.setDate(Number.SECOND, date);
            ps.setInt(Number.THIRD, stars);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return tours;
    }

    public List<Tour> readAllByCountryAndHotelStars(String countryName, int stars) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_COUNTRY_AND_HOTEL)) {
            ps.setString(Number.FIRST, countryName);
            ps.setInt(Number.SECOND, stars);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return tours;
    }

    public List<Tour> readAllByCountry(String countryName) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_COUNTRY)) {
            ps.setString(Number.FIRST, countryName);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return tours;
    }

    public List<Tour> readAllByDate(Date date) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_DATE)) {
            ps.setDate(Number.FIRST, date);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return tours;
    }

    public List<Tour> readAllByHotelStars(int hotelStars) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_HOTEL_STARS)) {
            ps.setInt(Number.FIRST, hotelStars);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return tours;
    }

    public List<Tour> readAllByCountryDate(String country, Date date) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_COUNTRY_DATE)) {
            ps.setDate(Number.FIRST, date);
            ps.setString(Number.SECOND, country);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return tours;
    }

    private void setTours(List<Tour> tours, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Tour tour = new Tour();
            Country country = new Country();
            City city = new City();
            Hotel hotel = new Hotel();

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

            city.setCountry(country);
            tour.setCity(city);
            tour.setHotel(hotel);
            tours.add(tour);
        }
    }


}
