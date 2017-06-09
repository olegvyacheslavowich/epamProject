package service;

import constant.Key;
import daolayer.DaoFactory;
import daolayer.impl.FlightDao;
import entity.Flight;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;
import exception.ServiceException;

import java.sql.Date;
import java.util.List;


public class FlightService {


    public List<Flight> getFlight(String departureCity, String country, Date date) throws DataBaseConnectionException {

        List<Flight> flights;
        try (DaoFactory factory = new DaoFactory()) {
            FlightDao flightDao = factory.getFlightDao();
            flights = flightDao.readAllByCityCountryDate(departureCity, country, date);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }

        return flights;
    }

    /**
     * метод возвращает рейс по заданным параметрам
     *
     * @param departureCity город вылета
     * @param arrivalCity   город прилета
     * @param date          дата вылета
     * @return
     */
    public Flight getFlightByCitiesAndDate(String departureCity, String arrivalCity, Date date) throws DataBaseConnectionException {

        Flight flight;
        try (DaoFactory factory = new DaoFactory()) {
            FlightDao flightDao = factory.getFlightDao();
            flight = flightDao.readByCitiesAndDate(departureCity, arrivalCity, date);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return flight;
    }

    public Flight getFlightByCitiesDate(String departureCity, String arrivalCity, String date) throws DataBaseConnectionException, ServiceException {

        if (getFlightByCitiesAndDate
                (departureCity, arrivalCity, Date.valueOf(date)) != null) {
            return getFlightByCitiesAndDate
                    (departureCity, arrivalCity, Date.valueOf(date));
        } else {
            throw new ServiceException(Key.EX_NO_FLIGHT_FOR_DATE);
        }
    }


}
