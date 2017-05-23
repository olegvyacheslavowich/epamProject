package executor.impl.search.impl;

import constant.Attribute;
import constant.Country;
import constant.Hotel;
import constant.Page;
import exception.EmptyFieldException;
import exception.IncorrectDataException;
import exception.NoFlightsException;
import exception.NoToursException;
import executor.impl.search.IndexExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class BuildIndexExecutor extends IndexExecutor {

    private IndexExecutor next;

    public BuildIndexExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String departureCity, String country, String hotelStars,
                          String date, String adultNumber, String childrenNumber)
            throws EmptyFieldException, IncorrectDataException, NoToursException, NoFlightsException {

        String page = Page.TOURS.getPage();

        if (checkHotel(Integer.parseInt(hotelStars)) && date.isEmpty() && checkCountry(country)) {
            setToSession(Attribute.TOURS.getAttribute(), getTourDBService().readAll());

        } else if (!checkHotel(Integer.parseInt(hotelStars)) && !date.isEmpty() && !checkCountry(country)) {
            getFlightDBService().getFlight(departureCity, country, Date.valueOf(date));
            setToSession(Attribute.TOURS.getAttribute(),
                    getTourDBService().readToursByCountryAndDateAndHotelStars
                            (country, Date.valueOf(date), Integer.parseInt(hotelStars)));

        } else if (checkHotel(Integer.parseInt(hotelStars)) && date.isEmpty() && !checkCountry(country)) {
            setToSession(Attribute.TOURS.getAttribute(), getTourDBService().readToursByCountry(country));

        } else if (checkHotel(Integer.parseInt(hotelStars)) && !date.isEmpty() && checkCountry(country)) {
            setToSession(Attribute.TOURS.getAttribute(), getTourDBService().readToursByDate(Date.valueOf(date)));

        } else if (!checkHotel(Integer.parseInt(hotelStars)) && date.isEmpty() && !checkCountry(country)) {
            setToSession(Attribute.TOURS.getAttribute(), getTourDBService().readToursByCountryAndHotelStars(country, Integer.parseInt(hotelStars)));

        } else if (!checkHotel(Integer.parseInt(hotelStars)) && date.isEmpty() && checkCountry(country)) {
            setToSession(Attribute.TOURS.getAttribute(), getTourDBService().readToursByHotelStars(Integer.parseInt(hotelStars)));

        } else if (checkHotel(Integer.parseInt(hotelStars)) && date.isEmpty() && !checkCountry(country)) {
            getFlightDBService().getFlight(departureCity, country, Date.valueOf(date));
            setToSession(Attribute.TOURS.getAttribute(), getTourDBService().readToursByCountryDate(country, Date.valueOf(date)));
        }

        setToSession(Attribute.DEPARTURE_CITY.getAttribute(), departureCity);
        setToSession(Attribute.COUNTRY.getAttribute(), country);
        setToSession(Attribute.DATE.getAttribute(), date);
        setToSession(Attribute.HOTEL_STARS.getAttribute(), hotelStars);
        setToSession(Attribute.ADULT_NUMBER.getAttribute(), adultNumber);
        setToSession(Attribute.CHILDREN_NUMBER.getAttribute(), childrenNumber);

        if (next != null)

        {
            page = next.execute(departureCity, country, hotelStars, date, adultNumber, childrenNumber);
        }

        return page;
    }

    public void setNext(IndexExecutor next) {
        this.next = next;
    }

    /**
     * Метод проверят равно ли поле Класс отеля на странице index.jsp
     * значению '0' (любой класс отеля)
     *
     * @return true если равно, false если не равно
     */

    private boolean checkHotel(int hotelStars) {
        return hotelStars == Hotel.ANY_HOTEL.getHotel();
    }

    /**
     * Метод проверят равно ли поле Страна на странице index.jsp
     * значению 'Любая' (любой страна тура)
     *
     * @return true если равно, false если не равно
     */

    private boolean checkCountry(String country) {
        return country.equals(Country.ANY_COUNTRY.getCountry());
    }

}
