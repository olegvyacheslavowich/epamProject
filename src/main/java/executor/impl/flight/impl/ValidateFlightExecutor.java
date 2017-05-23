package executor.impl.flight.impl;

import exception.*;
import executor.impl.flight.FlightExecutor;
import validation.Validation;
import validation.impl.FlightPageValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ValidateFlightExecutor extends FlightExecutor {

    private FlightExecutor next;

    public ValidateFlightExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(List<String> fullNames, List<String> birthdays, List<String> papers, List<String> documentNums, List<String> phones, List<String> emails, int adultNumber) throws EmptyFieldException, NoCreditCardsException, NoFlightsException, NoToursException, IncorrectDataException {

        String page = null;

        Validation validation = new FlightPageValidation(getSession(),
                fullNames,
                birthdays,
                documentNums,
                phones,
                emails);

        validation.isValid();

        if (next != null) {
            page = next.execute(fullNames, birthdays, papers, documentNums, phones, emails, adultNumber);
        }

        return page;
    }

    public void setNext(FlightExecutor next) {
        this.next = next;
    }
}
