package executor.impl.hottour.impl;

import constant.Key;
import constant.Parameter;
import exception.EmptyFieldException;
import executor.impl.hottour.HotTourExecutor;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class ReadHotTourExecutor extends HotTourExecutor {

    private HotTourExecutor next;

    public ReadHotTourExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String tourId) throws EmptyFieldException {

        String page = null;

        if (readFromRequest(Parameter.TOUR_ID.getParameter()) != null) {
            if (!readFromRequest(Parameter.TOUR_ID.getParameter()).isEmpty()) {
                tourId = readFromRequest(Parameter.TOUR_ID.getParameter());
            }
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_SELECT_TOUR.getKey(), getCurrentLanguage()));
        }

        if (next != null) {
            page = next.execute(tourId);
        }

        return page;
    }

    public void setNext(HotTourExecutor next) {
        this.next = next;
    }
}
