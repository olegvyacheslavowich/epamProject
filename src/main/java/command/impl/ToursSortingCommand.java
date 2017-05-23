package command.impl;

import command.ActionCommand;
import executor.impl.tourssorting.ToursSortingExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ToursSortingCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest rq) {

        HttpSession session = rq.getSession();
        ToursSortingExecutor executor = new ToursSortingExecutor(rq, session);
        executor.read();
        executor.write();
        return executor.returnPage();
    }


}
