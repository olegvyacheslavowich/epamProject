package executor.impl.search.impl;

import constant.Parameter;
import exception.EmptyFieldException;
import exception.IncorrectDataException;
import exception.NoFlightsException;
import exception.NoToursException;
import executor.impl.search.IndexExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class ReadIndexExecutor extends IndexExecutor {

    private IndexExecutor next;

    public ReadIndexExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String departureCity, String country, String hotelStars,
                          String date, String adultNumber, String childrenNumber)
            throws NoToursException, NoFlightsException, EmptyFieldException, IncorrectDataException {

        String page = null;

        departureCity = readFromRequest(Parameter.DEPARTURE_CITY.getParameter());
        country = readFromRequest(Parameter.COUNTRY.getParameter());
        hotelStars = readFromRequest(Parameter.HOTEL_STARS.getParameter());
        date = readFromRequest(Parameter.DATE.getParameter());
        adultNumber = readFromRequest(Parameter.ADULT_NUMBER.getParameter());
        childrenNumber = readFromRequest(Parameter.CHILDREN_NUMBER.getParameter());


        if (next != null) {
            page = next.execute(departureCity, country, hotelStars, date, adultNumber, childrenNumber);
        }

        return page;
    }

    public void setNext(IndexExecutor next) {
        this.next = next;
    }
}
