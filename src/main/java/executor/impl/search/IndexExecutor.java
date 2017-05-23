package executor.impl.search;

import exception.EmptyFieldException;
import exception.IncorrectDataException;
import exception.NoFlightsException;
import exception.NoToursException;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public abstract class IndexExecutor extends PageExecutor {


    public IndexExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public abstract String execute(String departureCity, String country, String hotelStars,
                                   String date, String adultNumber, String childrenNumber)
            throws EmptyFieldException, IncorrectDataException, NoToursException, NoFlightsException;
}
