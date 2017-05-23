package executor.impl.hottour.impl;

import exception.EmptyFieldException;
import executor.impl.hottour.HotTourExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ValidateHotTourExecutor extends HotTourExecutor {

    private HotTourExecutor next;

    public ValidateHotTourExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String tourId) throws EmptyFieldException {

        String page = null;

        if (next != null) {
            page = next.execute(tourId);
        }


        return page;
    }

    public void setNext(HotTourExecutor next) {
        this.next = next;
    }
}
