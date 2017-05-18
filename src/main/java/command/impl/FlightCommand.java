package command.impl;

import command.ActionCommand;
import executor.pageexecutor.impl.FlightPageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 03.05.2017.
 */
public class FlightCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest rq) {

        HttpSession session = rq.getSession();
        FlightPageExecutor executor = new FlightPageExecutor(rq, session);
        executor.read();
        executor.validate();
        executor.build();
        executor.write();
        return executor.returnPage();
    }


}
