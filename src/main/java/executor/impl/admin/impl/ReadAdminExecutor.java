package executor.impl.admin.impl;

import executor.impl.admin.AdminExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 21.05.2017.
 */
public class ReadAdminExecutor extends AdminExecutor {

    private AdminExecutor next;


    public ReadAdminExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute() {

        String page = null;
        if (next != null) {
            page = next.execute();
        }
        return page;
    }

    public void setNext(AdminExecutor next) {
        this.next = next;
    }
}
