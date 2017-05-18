package executor.pageexecutor.impl;

import constant.*;
import exception.EmptyFieldException;
import exception.IncorrectDataException;
import exception.NoFlightsException;
import exception.NoToursException;
import executor.pageexecutor.PageExecutor;
import validation.impl.IndexPageValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class IndexPageExecutor extends PageExecutor {


    /**
     * страница, на которую отправимся в записимости от результатов множественных проверок
     */
    private String page;
    /**
     * город вылета самолета
     */
    private String departureCity;
    /**
     * страна тура
     */
    private String country;
    /**
     * класс отеля (3 звезды, 4 звезды и др.)
     */
    private int hotelStars;
    /**
     * дата вылета в тур
     */
    private Date date;
    /**
     * число совершенолетних отдыхающих
     */
    private int adultNumber;
    /**
     * число детей до 2 лет (до 2 лет билет на самолет не нужен)
     */
    private int childrenNumber;

    public IndexPageExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public void read() {

        departureCity = readFromRequest(Parameter.DEPARTURE_CITY.getParameter());
        country = readFromRequest(Parameter.COUNTRY.getParameter());
        hotelStars = Integer.parseInt(readFromRequest(Parameter.HOTEL_STARS.getParameter()));
        String date = readFromRequest(Parameter.DATE.getParameter());
        if (!date.isEmpty()) {
            this.date = Date.valueOf(date);
        } else {
            this.date = null;
        }
        adultNumber = Integer.parseInt(readFromRequest(Parameter.ADULT_NUMBER.getParameter()));
        childrenNumber = Integer.parseInt(readFromRequest(Parameter.CHILDREN_NUMBER.getParameter()));
    }

    @Override
    public void validate() {

        IndexPageValidation validation = new IndexPageValidation(departureCity);

        try {
            validation.isValid();
            page = Page.TOURS.getPage();
        } catch (EmptyFieldException | IncorrectDataException e) {
            page = Page.INDEX.getPage();
            setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
        }
    }

    @Override
    public void build() {

    }

    @Override
    public void write() {

        setToSession(Attribute.DEPARTURE_CITY.getAttribute(), departureCity);
        setToSession(Attribute.COUNTRY.getAttribute(), country);
        setToSession(Attribute.DATE.getAttribute(), date);
        setToSession(Attribute.HOTEL_STARS.getAttribute(), hotelStars);
        setToSession(Attribute.ADULT_NUMBER.getAttribute(), adultNumber);
        setToSession(Attribute.CHILDREN_NUMBER.getAttribute(), childrenNumber);
    }


    /**
     * метод возваращает страницу, которая будет отображаться
     * <p>
     * в зависимости от введенных параметров пользователем на странице index.jsp
     * (дата вылета, страна тура, класс отеля (количество звезд)) будет осуществлен переход
     * на другую страницу или вывод сообщения о ненайденных турах.
     * <p>
     * если тур по заданным параметром не будет найден, то отобразиться соответствующее сообщение
     * на странице index.jsp
     * <p>
     * найденный(ые) тур(ы) записывается(ются) в сессию и request для дальнейшего использования на странице tours.jsp
     *
     * @return страница, на которую будет осуществлен переход
     */

    @Override
    public String returnPage() {

        if (checkHotel(hotelStars) && isNull(date) && checkCountry(country)) {
            setToBoth(Attribute.TOURS.getAttribute(), getTourDBService().getTours());
        } else if (!checkHotel(hotelStars) && !isNull(date) && !checkCountry(country)) {
            try {
                getFlightDBService().getFlight(departureCity, country, date);
                setToBoth(Attribute.TOURS.getAttribute(), getTourDBService().getToursByCountryAndDateAndHotelStars(country, date, hotelStars));
            } catch (NoFlightsException | NoToursException e) {
                page = Page.INDEX.getPage();
                setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            }
        } else if (checkHotel(hotelStars) && isNull(date) && !checkCountry(country)) {
            try {
                setToBoth(Attribute.TOURS.getAttribute(), getTourDBService().getToursByCountry(country));
            } catch (NoToursException e) {
                page = Page.INDEX.getPage();
                setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            }
        } else if (checkHotel(hotelStars) && !isNull(date) && checkCountry(country)) {
            try {
                setToBoth(Attribute.TOURS.getAttribute(), getTourDBService().getToursByDate(date));
            } catch (NoToursException e) {
                page = Page.INDEX.getPage();
                setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            }
        } else if (!checkHotel(hotelStars) && isNull(date) && !checkCountry(country)) {
            try {
                setToBoth(Attribute.TOURS.getAttribute(), getTourDBService().getToursByCountryAndHotelStars(country, hotelStars));
            } catch (NoToursException e) {
                page = Page.INDEX.getPage();
                setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            }
        } else if (!checkHotel(hotelStars) && isNull(date) && checkCountry(country)) {
            try {
                setToBoth(Attribute.TOURS.getAttribute(), getTourDBService().getToursByHotelStars(hotelStars));
            } catch (NoToursException e) {
                page = Page.INDEX.getPage();
                setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            }
        } else if (checkHotel(hotelStars) && !isNull(date) && !checkCountry(country)) {
            try {
                getFlightDBService().getFlight(departureCity, country, date);
                setToBoth(Attribute.TOURS.getAttribute(), getTourDBService().getToursByCountryDate(country, date));
            } catch (NoFlightsException | NoToursException e) {
                page = Page.INDEX.getPage();
                setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            }
        }

        return page;
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
