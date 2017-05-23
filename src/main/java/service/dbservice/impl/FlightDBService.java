package service.dbservice.impl;

import constant.Key;
import daolayer.impl.FlightDao;
import entity.Flight;
import exception.NoFlightsException;
import service.ResourceManager;
import service.dbservice.DBService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;


public class FlightDBService extends DBService {

    private FlightDao dao = new FlightDao();

    public FlightDBService(HttpSession session) {
        super(session);
    }

    /**
     * Метод проверяет существуют ли рейсы из заданного города
     * в заданную страну вообще, в опеределенную дату
     * <p>
     * Данный метод был создан для того, чтобы не разочаровывать клиента,
     * который нашел подходящий тур, но еще не знает о том, что именно в выбранную страну
     * в выбранное время самолет не летит
     *
     * @return рейсы
     * @throws NoFlightsException нет рейсов по заданным параметрам
     */
    public List<Flight> getFlight(String departureCity, String country, Date date) throws NoFlightsException {

        List<Flight> flights = dao.readAllByCityCountryDate(departureCity, country, date);
        if (flights.isEmpty()) {
            throw new NoFlightsException(ResourceManager.getResource(Key.EX_NO_FLIGHT_FOR_PARAMETERS.getKey(), getCurrentLanguage()));
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
    public Flight getFlightByCitiesAndDate(String departureCity, String arrivalCity, Date date) {
        return dao.readByCitiesAndDate(departureCity, arrivalCity, date);
    }
}
