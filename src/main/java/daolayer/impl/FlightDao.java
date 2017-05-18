package daolayer.impl;

import constant.Num;
import daolayer.DAO;
import entity.City;
import entity.Country;
import entity.Flight;
import entity.Plane;
import service.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FlightDao extends DAO<Flight, Integer> {

    private Connection connection = getConnection();

    private static final Query READ_BY_CITY_COUNTRY_DATE = new Query("" +
            "SELECT\n" +
            "  FLIGHT.FLIGHT_ID,\n" +
            "  PLANE.NAME,\n" +
            "  RUS_LETTERS.NAME,\n" +
            "  FLIGHT.DATE,\n" +
            "  PLANE.DEPARTURE_TIME,\n" +
            "  COUNTRIES.NAME,\n" +
            "  ARRIVAL_CITY.NAME,\n" +
            "  PLANE.TRAVEL_TIME\n" +
            "FROM FLIGHT\n" +
            "  INNER JOIN PLANE ON FLIGHT.PLANE_ID = PLANE.NAME\n" +
            "  INNER JOIN CITIES AS RUS_LETTERS\n" +
            "    ON PLANE.DEPARTURE_CITY_ID = RUS_LETTERS.CITY_ID\n" +
            "  INNER JOIN CITIES AS ARRIVAL_CITY\n" +
            "    ON PLANE.ARRIVAL_CITY_ID = ARRIVAL_CITY.CITY_ID\n" +
            "  INNER JOIN COUNTRIES ON ARRIVAL_CITY.COUNTRY_ID = COUNTRIES.COUNTRY_ID\n" +
            "WHERE RUS_LETTERS.NAME = ? AND\n" +
            "  COUNTRIES.NAME = ? AND FLIGHT.DATE = ?");

    private static final Query READ_BY_CITIES = new Query("" +
            "SELECT\n" +
            "  DEPARTURE_DATE,\n" +
            "  PLANE.NAME,\n" +
            "  RUS_LETTERS.NAME,\n" +
            "  DEPARTURE_TIME,\n" +
            "  ARRIVAL_CITY.NAME,\n" +
            "  ARRIVAL_TIME " +
            "FROM PLANE, FLIGHT\n" +
            "  INNER JOIN CITIES AS RUS_LETTERS\n" +
            "    ON PLANE.DEPARTURE_CITY_ID = RUS_LETTERS.CITY_ID\n" +
            "  INNER JOIN CITIES AS ARRIVAL_CITY\n" +
            "    ON PLANE.ARRIVAL_CITY_ID = ARRIVAL_CITY.CITY_ID\n" +
            "WHERE PLANE.NAME = FLIGHT.PLANE_ID\n" +
            "      AND RUS_LETTERS.NAME = ?\n" +
            "      AND ARRIVAL_CITY.NAME = ?");

    private static final Query READ_BY_CITIES_DATE = new Query("" +
            "SELECT\n" +
            "  FLIGHT.FLIGHT_ID,\n" +
            "  PLANE.NAME,\n" +
            "  RUS_LETTERS.NAME,\n" +
            "  FLIGHT.DATE,\n" +
            "  PLANE.DEPARTURE_TIME,\n" +
            "  ARRIVAL_CITY.NAME,\n" +
            "  PLANE.TRAVEL_TIME\n" +
            "FROM FLIGHT\n" +
            "  INNER JOIN PLANE ON FLIGHT.PLANE_ID = PLANE.NAME\n" +
            "  INNER JOIN CITIES AS RUS_LETTERS\n" +
            "    ON PLANE.DEPARTURE_CITY_ID = RUS_LETTERS.CITY_ID\n" +
            "  INNER JOIN CITIES AS ARRIVAL_CITY\n" +
            "    ON PLANE.ARRIVAL_CITY_ID = ARRIVAL_CITY.CITY_ID\n" +
            "WHERE RUS_LETTERS.NAME = ? AND\n" +
            "  ARRIVAL_CITY.NAME = ? AND FLIGHT.DATE = ?");

    private static final Query READ = new Query("SELECT\n" +
            "  FLIGHT.FLIGHT_ID,\n" +
            "  PLANE.NAME,\n" +
            "  RUS_LETTERS.NAME,\n" +
            "  FLIGHT.DATE,\n" +
            "  PLANE.DEPARTURE_TIME,\n" +
            "  ARRIVAL_CITY.NAME,\n" +
            "  PLANE.TRAVEL_TIME\n" +
            "FROM FLIGHT\n" +
            "  INNER JOIN PLANE ON FLIGHT.PLANE_ID = PLANE.NAME\n" +
            "  INNER JOIN CITIES AS RUS_LETTERS\n" +
            "    ON PLANE.DEPARTURE_CITY_ID = RUS_LETTERS.CITY_ID\n" +
            "  INNER JOIN CITIES AS ARRIVAL_CITY\n" +
            "    ON PLANE.ARRIVAL_CITY_ID = ARRIVAL_CITY.CITY_ID\n" +
            "WHERE FLIGHT_ID = ?");

    @Override
    public Integer create(Flight entity) {
        return null;
    }

    @Override
    public Flight read(Integer id) {
        Flight flight = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_CITIES_DATE.getQuery())) {
            ps.setInt(Num.FIRST.getNum(), id);
            rs = ps.executeQuery();
            flight = getFlight(flight, rs);

            return flight;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return flight;
    }



    @Override
    public boolean update(Flight entity) {
        return false;
    }

    @Override
    public boolean delete(Flight entity) {
        return false;
    }

    @Override
    public List<Flight> readAll() {
        return null;
    }

    public Flight readByCitiesAndDate(String departureCityName, String arrivalCityName, Date date) {

        Flight flight = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_CITIES_DATE.getQuery())) {
            ps.setString(Num.FIRST.getNum(), departureCityName);
            ps.setString(Num.SECOND.getNum(), arrivalCityName);
            ps.setDate(Num.THIRD.getNum(), date);
            rs = ps.executeQuery();
            flight = getFlight(flight, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return flight;
    }

    public List<Flight> readAllByCityCountryDate(String departureCityName, String countryName, Date date) {

        List<Flight> flights = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_CITY_COUNTRY_DATE.getQuery())) {
            ps.setString(Num.FIRST.getNum(), departureCityName);
            ps.setString(Num.SECOND.getNum(), countryName);
            ps.setDate(Num.THIRD.getNum(), date);
            rs = ps.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                Plane plane = new Plane();
                City departureCity = new City();
                City arrivalCity = new City();
                Country country = new Country();

                flight.setId(rs.getInt(Num.FIRST.getNum()));
                plane.setName(rs.getString(Num.SECOND.getNum()));
                departureCity.setName(rs.getString(Num.THIRD.getNum()));
                flight.setDate(rs.getDate(Num.FOURTH.getNum()));
                plane.setDepartureTime(rs.getTime(Num.FIFTH.getNum()));
                country.setName(rs.getString(Num.SIXTH.getNum()));
                arrivalCity.setName(rs.getString(Num.SEVENTH.getNum()));
                plane.setTravelTime(rs.getTime(Num.EIGHTH.getNum()));

                arrivalCity.setCountry(country);
                plane.setDepartureCity(departureCity);
                plane.setArrivalCity(arrivalCity);
                flight.setPlane(plane);
                flights.add(flight);
            }

            return flights;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, rs);
        }
        return flights;
    }

    private Flight getFlight(Flight flight, ResultSet rs) throws SQLException {
        while (rs.next()) {
            flight = new Flight();
            Plane plane = new Plane();
            City departureCity = new City();
            City arrivalCity = new City();

            flight.setId(rs.getInt(Num.FIRST.getNum()));
            plane.setName(rs.getString(Num.SECOND.getNum()));
            departureCity.setName(rs.getString(Num.THIRD.getNum()));
            flight.setDate(rs.getDate(Num.FOURTH.getNum()));
            plane.setDepartureTime(rs.getTime(Num.FIFTH.getNum()));
            arrivalCity.setName(rs.getString(Num.SIXTH.getNum()));
            plane.setTravelTime(rs.getTime(Num.SEVENTH.getNum()));

            plane.setDepartureCity(departureCity);
            plane.setArrivalCity(arrivalCity);
            flight.setPlane(plane);
        }
        return flight;
    }
}
