package command.impl;

import command.ActionCommand;
import executor.pageexecutor.impl.IndexPageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 25.04.2017.
 */
public class SearchCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest rq) {

        HttpSession session = rq.getSession();
        IndexPageExecutor executor = new IndexPageExecutor(rq, session);
        executor.read();
        executor.validate();
        executor.write();

        return executor.returnPage();
    }


}
