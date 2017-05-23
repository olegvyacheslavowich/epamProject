package command.impl;

import command.ActionCommand;
import executor.impl.user.impl.BuildUserExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 17.05.2017.
 */
public class UserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest rq) {

        HttpSession session = rq.getSession();

        BuildUserExecutor build = new BuildUserExecutor(rq, session);

        return build.execute();
    }
}
