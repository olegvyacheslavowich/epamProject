package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 12.04.2017.
 */
public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest rq) {
        return logout(rq);
    }

    private String logout(HttpServletRequest rq){

        HttpSession session = rq.getSession();
        session.invalidate();

        return Page.INDEX.getPage();
    }
}
