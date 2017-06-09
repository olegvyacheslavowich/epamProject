package daolayer.impl;

import constant.Number;
import daolayer.Dao;
import entity.City;
import entity.Country;
import entity.Flight;
import entity.Plane;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FlightDao extends Dao<Flight, Integer> {

    private static final String READ_BY_CITY_COUNTRY_DATE =
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
                    "  COUNTRIES.NAME = ? AND FLIGHT.DATE = ?";


    private static final String READ_BY_CITIES_DATE =
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
                    "  ARRIVAL_CITY.NAME = ? AND FLIGHT.DATE = ?";

    @Override
    public Integer create(Flight entity) {
        return null;
    }

    @Override
    public Flight read(Integer id) {
        Flight flight = null;
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_CITIES_DATE)) {
            ps.setInt(Number.FIRST, id);
            rs = ps.executeQuery();
            flight = getFlight(rs);
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
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
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_CITIES_DATE)) {
            ps.setString(Number.FIRST, departureCityName);
            ps.setString(Number.SECOND, arrivalCityName);
            ps.setDate(Number.THIRD, date);
            rs = ps.executeQuery();
            flight = getFlight(rs);

        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return flight;
    }

    public List<Flight> readAllByCityCountryDate(String departureCityName, String countryName, Date date) {

        List<Flight> flights = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_CITY_COUNTRY_DATE)) {
            ps.setString(Number.FIRST, departureCityName);
            ps.setString(Number.SECOND, countryName);
            ps.setDate(Number.THIRD, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                Plane plane = new Plane();
                City departureCity = new City();
                City arrivalCity = new City();
                Country country = new Country();

                flight.setId(rs.getInt(Number.FIRST));
                plane.setName(rs.getString(Number.SECOND));
                departureCity.setName(rs.getString(Number.THIRD));
                flight.setDate(rs.getDate(Number.FOURTH));
                plane.setDepartureTime(rs.getTime(Number.FIFTH));
                country.setName(rs.getString(Number.SIXTH));
                arrivalCity.setName(rs.getString(Number.SEVENTH));
                plane.setTravelTime(rs.getTime(Number.EIGHTH));

                arrivalCity.setCountry(country);
                plane.setDepartureCity(departureCity);
                plane.setArrivalCity(arrivalCity);
                flight.setPlane(plane);
                flights.add(flight);
            }

            return flights;
        } catch (SQLException e) {
            logger.info(e.getMessage());

        } finally {
            close(rs);
        }
        return flights;
    }

    private Flight getFlight(ResultSet rs) throws SQLException {
        Flight flight = null;
        while (rs.next()) {
            flight = new Flight();
            Plane plane = new Plane();
            City departureCity = new City();
            City arrivalCity = new City();

            flight.setId(rs.getInt(Number.FIRST));
            plane.setName(rs.getString(Number.SECOND));
            departureCity.setName(rs.getString(Number.THIRD));
            flight.setDate(rs.getDate(Number.FOURTH));
            plane.setDepartureTime(rs.getTime(Number.FIFTH));
            arrivalCity.setName(rs.getString(Number.SIXTH));
            plane.setTravelTime(rs.getTime(Number.SEVENTH));

            plane.setDepartureCity(departureCity);
            plane.setArrivalCity(arrivalCity);
            flight.setPlane(plane);
        }
        return flight;
    }
}
