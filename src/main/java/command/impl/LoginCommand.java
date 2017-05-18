package command.impl;

import command.ActionCommand;
import executor.pageexecutor.impl.LoginPageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 20.03.2017.
 */
public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest rq) {

        HttpSession session = rq.getSession();
        LoginPageExecutor executor = new LoginPageExecutor(rq, session);
        executor.read();
        executor.validate();
        executor.build();
        executor.write();

        return executor.returnPage();
    }


}
