package executor.impl.tours.impl;

import constant.Attribute;
import constant.Key;
import constant.Parameter;
import exception.*;
import executor.impl.tours.ToursExecutor;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ReadToursExecutor extends ToursExecutor {

    private ToursExecutor next;

    public ReadToursExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String tourId, String departureDate) throws NoFlightsException, EmptyFieldException, NoToursException, IncorrectDataException, NoCreditCardsException {

        String page = null;

        if (readFromRequest(Parameter.TOUR_ID.getParameter()) != null) {
            tourId = readFromRequest(Parameter.TOUR_ID.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_SELECT_TOUR.getKey(), getCurrentLanguage()));
        }

        departureDate = (String) readFromSession(Attribute.DATE.getAttribute());
        if (departureDate.isEmpty()) {
            if (!readFromRequest(Parameter.DEPARTURE_DATE.getParameter()).isEmpty()) {
                departureDate = readFromRequest(Parameter.DEPARTURE_DATE.getParameter());
            } else {
                throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_DATE.getKey(), getCurrentLanguage()));
            }
        }

        if (next != null) {
            page = next.execute(tourId, departureDate);
        }

        return page;
    }

    public void setNext(ToursExecutor next) {
        this.next = next;
    }
}
