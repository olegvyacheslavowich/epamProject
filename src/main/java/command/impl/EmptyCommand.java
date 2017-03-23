package command.impl;

import command.ActionCommand;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Created by 20_ok on 20.03.2017.
 */
public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest rq) {

        String page = rq.getParameter(ActionCommand.COMMAND);
        String lang = null;
        try {
            lang = new String(rq.getParameter("lang").getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException ignored) {
        }
       /* HttpSession session = rq.getSession();
        session.setAttribute("lang", lang);*/

        System.out.println(lang);
        rq.setAttribute("submit_login", ResourceManager.getResource("submit_login", lang));
        rq.setAttribute("submit_registration", ResourceManager.getResource("submit_registration", lang));

        return page;
    }
}
