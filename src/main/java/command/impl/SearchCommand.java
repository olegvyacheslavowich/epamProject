package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import constant.Parameter;
import exception.DataBaseConnectionException;
import exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Language;
import util.ResourceManager;
import service.FlightService;
import service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Created by 20_ok on 25.04.2017.
 */
public class SearchCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(SearchCommand.class);

    private static final String ANY_COUNTRY = "Любая";
    private static final int ANY_HOTEL = 0;


    @Override
    public String execute(HttpServletRequest rq) {

        String page = Page.INDEX;
        HttpSession session = rq.getSession();
        FlightService flightService = new FlightService();
        TourService tourService = new TourService();

        String departureCity = rq.getParameter(Parameter.DEPARTURE_CITY);
        String country = rq.getParameter(Parameter.COUNTRY);
        String hotelStars = rq.getParameter(Parameter.HOTEL_STARS);
        String date = rq.getParameter(Parameter.DATE);
        String adultNumber = rq.getParameter(Parameter.ADULT_NUMBER);
        String childrenNumber = rq.getParameter(Parameter.CHILDREN_NUMBER);

        try {
            if (checkHotel(Integer.parseInt(hotelStars)) && date.isEmpty() && checkCountry(country)) {
                session.setAttribute(Attribute.TOURS, tourService.readAll());

            } else if (!checkHotel(Integer.parseInt(hotelStars)) && !date.isEmpty() && !checkCountry(country)) {
                flightService.getFlight(departureCity, country, Date.valueOf(date));
                session.setAttribute(Attribute.TOURS, tourService.readToursByCountryAndDateAndHotelStars
                        (country, Date.valueOf(date), Integer.parseInt(hotelStars)));

            } else if (checkHotel(Integer.parseInt(hotelStars)) && date.isEmpty() && !checkCountry(country)) {
                session.setAttribute(Attribute.TOURS, tourService.readToursByCountry(country));

            } else if (checkHotel(Integer.parseInt(hotelStars)) && !date.isEmpty() && checkCountry(country)) {
                session.setAttribute(Attribute.TOURS, tourService.readToursByDate(Date.valueOf(date)));

            } else if (!checkHotel(Integer.parseInt(hotelStars)) && date.isEmpty() && !checkCountry(country)) {
                session.setAttribute(Attribute.TOURS, tourService.readToursByCountryAndHotelStars(country, Integer.parseInt(hotelStars)));

            } else if (!checkHotel(Integer.parseInt(hotelStars)) && date.isEmpty() && checkCountry(country)) {
                session.setAttribute(Attribute.TOURS, tourService.readToursByHotelStars(Integer.parseInt(hotelStars)));

            } else if (checkHotel(Integer.parseInt(hotelStars)) && date.isEmpty() && !checkCountry(country)) {
                flightService.getFlight(departureCity, country, Date.valueOf(date));
                session.setAttribute(Attribute.TOURS, tourService.readToursByCountryDate(country, Date.valueOf(date)));
            }
            logger.info("Tour was chose");
            page = Page.TOURS;
        } catch (ServiceException e) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            logger.info("Index page error :" + ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            return page;
        } catch (DataBaseConnectionException e) {
            logger.info("Index page error :", e);
            return page;
        }

        session.setAttribute(Attribute.DEPARTURE_CITY, departureCity);
        session.setAttribute(Attribute.COUNTRY, country);
        session.setAttribute(Attribute.DATE, date);
        session.setAttribute(Attribute.HOTEL_STARS, hotelStars);
        session.setAttribute(Attribute.ADULT_NUMBER, adultNumber);
        session.setAttribute(Attribute.CHILDREN_NUMBER, childrenNumber);

        return page;
    }

    private boolean checkHotel(int hotelStars) {
        return hotelStars == ANY_HOTEL;
    }

    /**
     * Метод проверят равно ли поле Страна на странице index.jsp
     * значению 'Любая' (любой страна тура)
     *
     * @return true если равно, false если не равно
     */

    private boolean checkCountry(String country) {
        return country.equals(ANY_COUNTRY);
    }

}
