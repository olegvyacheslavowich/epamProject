package executor.impl.tours.impl;

import exception.*;
import executor.impl.tours.ToursExecutor;
import validation.impl.ToursPageValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class ValidateToursExecutor extends ToursExecutor {

    private ToursExecutor next;

    public ValidateToursExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String tourId, String departureDate) throws NoFlightsException, EmptyFieldException, NoToursException,
            IncorrectDataException, NoCreditCardsException {

        String page = null;

        ToursPageValidation validation = new ToursPageValidation(getSession(), departureDate);

        validation.isValid();

        if (next != null) {
            page = next.execute(tourId, departureDate);
        }

        return page;
    }

    public void setNext(ToursExecutor next) {
        this.next = next;
    }
}
