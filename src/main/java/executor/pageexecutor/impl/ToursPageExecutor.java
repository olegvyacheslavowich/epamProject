package executor.pageexecutor.impl;

import constant.Attribute;
import constant.Num;
import constant.Page;
import constant.Parameter;
import daolayer.impl.FlightDao;
import daolayer.impl.TourDAO;
import entity.Flight;
import entity.Tour;
import entity.User;
import exception.NoFlightsException;
import exception.NoToursException;
import org.joda.time.DateTime;
import executor.pageexecutor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class ToursPageExecutor extends PageExecutor {

    private String page = Page.FLIGHT.getPage();
    private User user;

    private static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * выбранный, на странице tours.jsp, тур
     */
    private Tour tour;

    /**
     * город обратно ()
     */
    private String arrivalCity;

    /**
     * город туда
     */
    private String departureCity;

    /**
     * дата туда
     */
    private Date departureDate;

    /**
     * рейс на самолет туда
     */
    private Flight flightTo;

    /**
     * рейс на самолет обратно
     */
    private Flight flightFrom;

    private int tourId;


    public ToursPageExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public void read() {
        user = (User) readFromSession(Attribute.USER.getAttribute());
        departureDate = readDepartureDate();
        tourId = readTourId();
        departureCity = (String) readFromSession(Attribute.DEPARTURE_CITY.getAttribute());

    }

    @Override
    public void build() {
        try {
            tour = buildTour();
            arrivalCity = tour.getCity().getName();
            Date arrivalDate = countArrivalDate(departureDate, tour.getDays());
            flightTo = getFlightByCitiesAndDate(departureCity, arrivalCity, departureDate);
            flightFrom = getFlightByCitiesAndDate(arrivalCity, departureCity, arrivalDate);
        } catch (NoFlightsException | NoToursException e) {
            page = Page.TOURS.getPage();
            setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
        }

    }

    @Override
    public void validate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write() {
        setToSession(Attribute.TOUR.getAttribute(), tour);
        setToSession(Attribute.ARRIVAL_CITY.getAttribute(), arrivalCity);
    }


    @Override
    public String returnPage() {

        setToBoth(Attribute.FLIGHT_TO.getAttribute(), flightTo);
        setToBoth(Attribute.FLIGHT_FROM.getAttribute(), flightFrom);

        return page;
    }

    /**
     * Метод собирает объект Tour из параметов на странице tours.jsp
     *
     * @return объект класса Tour
     */

    private Tour buildTour() throws NoToursException {

        TourDAO dao = new TourDAO();
        Tour tour = dao.read(tourId);
        if (tour == null) {
            throw new NoToursException("Не выбран тур");
        }
        return tour;
    }

    /**
     * метод возвращает рейс по заданным параметрам
     *
     * @param departureCity город вылета
     * @param arrivalCity   город прилета
     * @param date          дата вылета
     * @return
     */
    private Flight getFlightByCitiesAndDate(String departureCity, String arrivalCity, Date date) throws NoFlightsException {

        FlightDao dao = new FlightDao();
        Flight flight = dao.readByCitiesAndDate(departureCity, arrivalCity, date);
        if (flight == null) {
            throw new NoFlightsException("Нет рейсов на выбранную дату");
        }
        return flight;
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

    private Date readDepartureDate() {
        Date departureDate = (Date) readFromSession(Attribute.DATE.getAttribute());
        if (isNull(departureDate)) {
            String departureDateStr = readFromRequest(Parameter.DEPARTURE_DATE.getParameter());
            if (!departureDateStr.isEmpty()) {
                departureDate = Date.valueOf(departureDateStr);
            } else {
                departureDate = null;
            }
        }
        return departureDate;
    }

    private int readTourId() {
        int tourId = Num.MINUS_ONE.getNum();
        String tourIdStr = readFromRequest(Parameter.TOUR_ID.getParameter());
        if (!isNull(tourIdStr)) {
            tourId = Integer.parseInt(tourIdStr);
        }
        return tourId;
    }
}


