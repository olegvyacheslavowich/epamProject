package executor.impl.tours;

import exception.*;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class ToursExecutor extends PageExecutor {

    public ToursExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public abstract String execute(String tourId, String departureDate) throws NoFlightsException, EmptyFieldException, NoToursException, IncorrectDataException, NoCreditCardsException;
}
