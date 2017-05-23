package executor.impl.admin;

import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 21.05.2017.
 */
public abstract class AdminExecutor extends PageExecutor {

    public AdminExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public abstract String execute();
}
