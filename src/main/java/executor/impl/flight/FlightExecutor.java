package executor.impl.flight;

import exception.*;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 20_ok on 22.05.2017.
 */
public abstract class FlightExecutor extends PageExecutor {

    public FlightExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public abstract String execute(List<String> fullNames, List<String> birthdays,
                                   List<String> papers, List<String> documentNums,
                                   List<String> phones, List<String> emails, int adultNumber) throws EmptyFieldException, NoCreditCardsException, NoFlightsException, NoToursException, IncorrectDataException;

}
