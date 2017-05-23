package command.impl;

import command.ActionCommand;
import executor.impl.admin.impl.BuildAdminExecutor;
import executor.impl.admin.impl.ReadAdminExecutor;
import executor.impl.admin.impl.ValidateAdminExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest rq) {

        HttpSession session = rq.getSession();

        ReadAdminExecutor read = new ReadAdminExecutor(rq, session);
        ValidateAdminExecutor validate = new ValidateAdminExecutor(rq, session);
        BuildAdminExecutor build = new BuildAdminExecutor(rq, session);

        read.setNext(validate);
        validate.setNext(build);
        return read.execute();
    }
}
