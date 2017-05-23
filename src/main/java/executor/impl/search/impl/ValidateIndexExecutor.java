package executor.impl.search.impl;

import exception.EmptyFieldException;
import exception.IncorrectDataException;
import exception.NoFlightsException;
import exception.NoToursException;
import executor.impl.search.IndexExecutor;
import validation.impl.IndexPageValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class ValidateIndexExecutor extends IndexExecutor {

    private IndexExecutor next;

    public ValidateIndexExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String departureCity, String country, String hotelStars,
                          String date, String adultNumber, String childrenNumber) throws
            EmptyFieldException, IncorrectDataException, NoFlightsException, NoToursException {

        String page = null;

        IndexPageValidation validation = new IndexPageValidation(getSession(), departureCity, date);
        validation.isValid();

        if (next != null) {
            page = next.execute(departureCity, country, hotelStars, date, adultNumber, childrenNumber);
        }

        return page;
    }

    public void setNext(IndexExecutor next) {
        this.next = next;
    }
}
