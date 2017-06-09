package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Key;
import constant.Page;
import constant.Parameter;
import entity.Flight;
import entity.Tour;
import exception.DataBaseConnectionException;
import exception.ServiceException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Language;
import util.ResourceManager;
import service.FlightService;
import service.TourService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ToursCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(ToursCommand.class);

    private static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";


    @Override
    public String execute(HttpServletRequest rq) {

        String page = Page.TOURS;
        HttpSession session = rq.getSession();
        TourService tourService = new TourService();
        FlightService flightService = new FlightService();

        String departureDate = (String) session.getAttribute(Attribute.DATE);
        if (Validator.isEmpty(rq.getParameter(Parameter.TOUR_ID))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_SELECT_TOUR, Language.getCurrentLanguage(session)));
            logger.info("Tours page error :" + ResourceManager.getResource(Key.EX_SELECT_TOUR, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(departureDate)) {
            if (!rq.getParameter(Parameter.DEPARTURE_DATE).isEmpty()) {
                departureDate = (String) rq.getAttribute(Parameter.DEPARTURE_DATE);
            } else {
                rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_DATE, Language.getCurrentLanguage(session)));
                logger.info("Tours page error :" + ResourceManager.getResource(Key.EX_ENTER_DATE, Language.getCurrentLanguage(session)));
                return page;
            }
        }
        int tourId = Integer.parseInt(rq.getParameter(Parameter.TOUR_ID));

        String departureCity = (String) session.getAttribute(Attribute.DEPARTURE_CITY);
        try {
            Tour tour = tourService.read(tourId);
            String arrivalCity = tour.getCity().getName();
            Date arrivalDate = countArrivalDate(Date.valueOf(departureDate), tour.getDays());
            Flight flightTo = flightService.getFlightByCitiesDate(departureCity, arrivalCity, departureDate);
            Flight flightFrom = flightService.getFlightByCitiesDate(arrivalCity, departureCity, String.valueOf(arrivalDate));
            session.setAttribute(Attribute.TOUR, tour);
            session.setAttribute(Attribute.ARRIVAL_CITY, arrivalCity);
            session.setAttribute(Attribute.FLIGHT_TO, flightTo);
            session.setAttribute(Attribute.FLIGHT_FROM, flightFrom);
            page = Page.FLIGHT;
            logger.info("Tour was chose");
        } catch (ServiceException e) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            logger.info("Tours page error :" + ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            return page;
        } catch (DataBaseConnectionException e) {
            logger.info("Tours page error :", e);
            return page;
        }
        return page;

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
}


