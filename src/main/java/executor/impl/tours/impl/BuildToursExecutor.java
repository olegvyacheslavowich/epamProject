package executor.impl.tours.impl;

import constant.Attribute;
import constant.Key;
import constant.Page;
import entity.Flight;
import entity.Tour;
import exception.*;
import executor.impl.tours.ToursExecutor;
import org.joda.time.DateTime;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class BuildToursExecutor extends ToursExecutor {

    private static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    private ToursExecutor next;

    public BuildToursExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String tourId, String departureDate) throws NoToursException, NoFlightsException, EmptyFieldException, IncorrectDataException, NoCreditCardsException {

        String page = Page.FLIGHT.getPage();

        String departureCity = (String) readFromSession(Attribute.DEPARTURE_CITY.getAttribute());
        Tour tour = getTourDBService().read(Integer.parseInt(tourId));
        String arrivalCity = tour.getCity().getName();
        Date arrivalDate = countArrivalDate(Date.valueOf(departureDate), tour.getDays());
        Flight flightTo = getFlightByCitiesDate(departureCity, arrivalCity, departureDate);
        Flight flightFrom = getFlightByCitiesDate(arrivalCity, departureCity, String.valueOf(arrivalDate));


        setToSession(Attribute.TOUR.getAttribute(), tour);
        setToSession(Attribute.ARRIVAL_CITY.getAttribute(), arrivalCity);

        setToSession(Attribute.FLIGHT_TO.getAttribute(), flightTo);
        setToSession(Attribute.FLIGHT_FROM.getAttribute(), flightFrom);

        if (next != null) {
            page = next.execute(tourId, departureDate);
        }

        return page;
    }

    public void setNext(ToursExecutor next) {
        this.next = next;
    }


    /**
     * метод считает дату, в которую лететь из тура
     * прибавляя к дате вылета в тура, количество дней отдыха в туре
     *
     * @param date дата вылета в тура
     * @param days количество дней отдыха в туре
     * @return дата вылета из тура
     */
    private Date countArrivalDate(Date date, int days) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        DateTime dateTime = new DateTime(date).plusDays(days);
        return Date.valueOf(dateFormat.format(dateTime.toDate()));
    }

    private Flight getFlightByCitiesDate(String departureCity, String arrivalCity, String date) throws NoFlightsException {

        if (getFlightDBService().getFlightByCitiesAndDate
                (departureCity, arrivalCity, Date.valueOf(date)) != null) {
            return getFlightDBService().getFlightByCitiesAndDate
                    (departureCity, arrivalCity, Date.valueOf(date));
        } else {
            throw new NoFlightsException(ResourceManager.getResource(Key.EX_NO_FLIGHT_FOR_DATE.getKey(), getCurrentLanguage()));
        }
    }
}
