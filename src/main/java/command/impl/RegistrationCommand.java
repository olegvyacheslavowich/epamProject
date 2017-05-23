package command.impl;

import com.sun.istack.internal.logging.Logger;
import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import exception.DifferentPasswordsException;
import exception.EmptyFieldException;
import exception.ExistingAccountException;
import exception.IncorrectDataException;
import executor.impl.registration.impl.BuildRegistrationExecutor;
import executor.impl.registration.impl.ReadRegistrationExecutor;
import executor.impl.registration.impl.ValidateRegistrationExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements ActionCommand {

    private Logger logger = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page;

        HttpSession session = rq.getSession();

        ReadRegistrationExecutor read = new ReadRegistrationExecutor(rq, session);
        ValidateRegistrationExecutor validate = new ValidateRegistrationExecutor(rq, session);
        BuildRegistrationExecutor build = new BuildRegistrationExecutor(rq, session);


        try {
            read.setNext(validate);
            validate.setNext(build);
            page = read.execute(null, null, null, null, null, null, null, null, null);
        } catch (EmptyFieldException | IncorrectDataException | ExistingAccountException | DifferentPasswordsException e) {
            page = Page.REGISTRATION.getPage();
            rq.setAttribute(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            logger.info(e.getMessage());
        }

        return page;
    }
}
