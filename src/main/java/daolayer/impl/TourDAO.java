package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.City;
import entity.Country;
import entity.Hotel;
import entity.Tour;
import service.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TourDAO extends DAO<Tour, Integer> {

    private Connection connection = getConnection();

    private static final Query READ_ALL = new Query("SELECT\n" +
            "  COUNTRIES.NAME,\n" +
            "  CITIES.NAME,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS,\n" +
            "  TOUR.PRICE,\n" +
            "  TOUR_ID\n" +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "    COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID");

    private static final Query READ = new Query("" +
            "SELECT\n" +
            "  COUNTRIES.NAME,\n" +
            "  CITIES.NAME,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS,\n" +
            "  TOUR.PRICE,\n" +
            "  TOUR_ID\n" +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "    COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "WHERE TOUR_ID = ?");

    private static final Query READ_ALL_BY_COUNTRY_AND_HOTEL = new Query("SELECT\n" +
            "  COUNTRIES.NAME,\n" +
            "  CITIES.NAME,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS," +
            "  TOUR.PRICE,\n" +
            "  TOUR_ID " +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "    COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "WHERE COUNTRIES.NAME = ? AND HOTEL.STARS_NUMBER = ?\n" +
            "\n");

    private static final Query READ_ALL_BY_COUNTRY = new Query("" +
            "SELECT\n" +
            "  COUNTRIES.NAME,\n" +
            "  CITIES.NAME,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS,\n" +
            "  TOUR.PRICE,\n" +
            "  TOUR_ID\n" +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "    COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "WHERE COUNTRIES.NAME = ?");

    private static final Query READ_ALL_BY_DATE = new Query("" +
            "SELECT\n" +
            "  COUNTRIES.NAME,\n" +
            "  CITIES.NAME,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS,\n" +
            "  TOUR.PRICE,\n" +
            "  TOUR_ID\n" +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "                          COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "  INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "  INNER JOIN PLANE ON CITIES.CITY_ID = PLANE.ARRIVAL_CITY_ID\n" +
            "  INNER JOIN FLIGHT ON PLANE.NAME = FLIGHT.PLANE_ID\n" +
            "WHERE FLIGHT.DATE = ?");

    private static final Query READ_ALL_BY_HOTEL_STARS = new Query("SELECT\n" +
            "  COUNTRIES.NAME,\n" +
            "  CITIES.NAME,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS,\n" +
            "  TOUR.PRICE,\n" +
            "  TOUR_ID\n" +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "    COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "WHERE HOTEL.STARS_NUMBER = ?");

    private static final Query READ_ALL_BY_COUNTRY_DATE = new Query("" +
            "SELECT\n" +
            "  COUNTRIES.NAME,\n" +
            "  CITIES.NAME,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS,\n" +
            "  TOUR.PRICE,\n" +
            "  TOUR_ID\n" +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "                          COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "  INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "  INNER JOIN PLANE ON CITIES.CITY_ID = PLANE.ARRIVAL_CITY_ID\n" +
            "  INNER JOIN FLIGHT ON PLANE.NAME = FLIGHT.PLANE_ID\n" +
            "WHERE FLIGHT.DATE = ? AND COUNTRIES.NAME = ?");

    private static final Query READ_ALL_BY_COUNTRY_DATE_HOTEL = new Query("" +
            "SELECT\n" +
            "  COUNTRIES.NAME,\n" +
            "  CITIES.NAME,\n" +
            "  HOTEL.NAME,\n" +
            "  HOTEL.DESCRIPTION,\n" +
            "  HOTEL.STARS_NUMBER,\n" +
            "  TOUR.DESCRIPTION,\n" +
            "  TOUR.DAYS,\n" +
            "  TOUR.PRICE,\n" +
            "  TOUR_ID\n" +
            "FROM TOUR\n" +
            "  INNER JOIN CITIES ON TOUR.CITY_ID = CITIES.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON CITIES.CITY_ID = TOUR.CITY_ID AND\n" +
            "                          COUNTRIES.COUNTRY_ID = CITIES.COUNTRY_ID\n" +
            "  INNER JOIN HOTEL ON TOUR.HOTEL_ID = HOTEL.HOTEL_ID\n" +
            "  INNER JOIN PLANE ON CITIES.CITY_ID = PLANE.ARRIVAL_CITY_ID\n" +
            "  INNER JOIN FLIGHT ON PLANE.NAME = FLIGHT.PLANE_ID\n" +
            "WHERE COUNTRIES.NAME = ? AND FLIGHT.DATE = ?  AND HOTEL.STARS_NUMBER = ? ");

    @Override
    public Integer create(Tour entity) {
        return null;
    }

    @Override
    public Tour read(Integer id) {

        Tour tour = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ.getQuery())) {
            ps.setInt(Num.FIRST.getNum(), id);
            rs = ps.executeQuery();
            while (rs.next()) {
                tour = new Tour();
                Country country = new Country();
                City city = new City();
                Hotel hotel = new Hotel();

                country.setName(rs.getString(Num.FIRST.getNum()));
                city.setName(rs.getString(Num.SECOND.getNum()));
                hotel.setName(rs.getString(Num.THIRD.getNum()));
                hotel.setDescription(rs.getString(Num.FOURTH.getNum()));
                hotel.setStarsNumber(rs.getInt(Num.FIFTH.getNum()));
                tour.setDescription(rs.getString(Num.SIXTH.getNum()));
                tour.setDays(rs.getInt(Num.SEVENTH.getNum()));
                tour.setPrice(rs.getInt(Num.EIGHTH.getNum()));
                tour.setId(rs.getInt(Num.NINTH.getNum()));

                city.setCountry(country);
                tour.setCity(city);
                tour.setHotel(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return tour;
    }

    @Override
    public boolean update(Tour entity) {
        return false;
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
            rs = ps.executeQuery(READ_ALL.getQuery());
            setTours(tours, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return tours;
    }

    public List<Tour> readAllByCountryAndDateAndHotelStars(String countryName, Date date, int stars) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_COUNTRY_DATE_HOTEL.getQuery())) {
            ps.setString(Num.FIRST.getNum(), countryName);
            ps.setDate(Num.SECOND.getNum(), date);
            ps.setInt(Num.THIRD.getNum(), stars);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return tours;
    }

    public List<Tour> readAllByCountryAndHotelStars(String countryName, int stars) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_COUNTRY_AND_HOTEL.getQuery())) {
            ps.setString(Num.FIRST.getNum(), countryName);
            ps.setInt(Num.SECOND.getNum(), stars);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return tours;
    }

    public List<Tour> readAllByCountry(String countryName) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_COUNTRY.getQuery())) {
            ps.setString(Num.FIRST.getNum(), countryName);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return tours;
    }

    public List<Tour> readAllByDate(Date date) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_DATE.getQuery())) {
            ps.setDate(Num.FIRST.getNum(), date);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return tours;
    }

    public List<Tour> readAllByHotelStars(int hotelStars) {

        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_HOTEL_STARS.getQuery())) {
            ps.setInt(Num.FIRST.getNum(), hotelStars);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return tours;
    }

    public List<Tour> readAllByCountryDate(String country, Date date) {
        List<Tour> tours = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_COUNTRY_DATE.getQuery())) {
            ps.setDate(Num.FIRST.getNum(), date);
            ps.setString(Num.SECOND.getNum(), country);
            rs = ps.executeQuery();
            setTours(tours, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return tours;
    }

    private void setTours(List<Tour> tours, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Tour tour = new Tour();
            Country country = new Country();
            City city = new City();
            Hotel hotel = new Hotel();

            country.setName(rs.getString(Num.FIRST.getNum()));
            city.setName(rs.getString(Num.SECOND.getNum()));
            hotel.setName(rs.getString(Num.THIRD.getNum()));
            hotel.setDescription(rs.getString(Num.FOURTH.getNum()));
            hotel.setStarsNumber(rs.getInt(Num.FIFTH.getNum()));
            tour.setDescription(rs.getString(Num.SIXTH.getNum()));
            tour.setDays(rs.getInt(Num.SEVENTH.getNum()));
            tour.setPrice(rs.getInt(Num.EIGHTH.getNum()));
            tour.setId(rs.getInt(Num.NINTH.getNum()));

            city.setCountry(country);
            tour.setCity(city);
            tour.setHotel(hotel);
            tours.add(tour);
        }
    }


}
