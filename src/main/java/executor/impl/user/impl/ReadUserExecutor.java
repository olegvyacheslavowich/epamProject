package executor.impl.user.impl;

import executor.impl.user.UserExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class ReadUserExecutor extends UserExecutor {

    private UserExecutor next;

    public ReadUserExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute() {

        String page = null;
        if (next != null) {
            next.execute();
        }

        return page;
    }

    public void setNext(UserExecutor next) {
        this.next = next;
    }
}
