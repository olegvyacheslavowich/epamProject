package executor.impl.hottour;

import exception.EmptyFieldException;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public abstract class HotTourExecutor extends PageExecutor {

    public HotTourExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public abstract String execute(String tourId) throws EmptyFieldException;
}

