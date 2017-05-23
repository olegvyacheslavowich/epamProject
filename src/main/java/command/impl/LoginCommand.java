package command.impl;

import com.sun.istack.internal.logging.Logger;
import executor.impl.login.impl.BuildLoginExecutor;
import executor.impl.login.impl.ReadLoginExecutor;
import executor.impl.login.impl.ValidateLoginExecutor;
import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import exception.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 20.03.2017.
 */
public class LoginCommand implements ActionCommand {

    private Logger logger = Logger.getLogger(LoginCommand.class);


    @Override
    public String execute(HttpServletRequest rq) {

        String page;

        HttpSession session = rq.getSession();

        ReadLoginExecutor read = new ReadLoginExecutor(rq, session);
        ValidateLoginExecutor validate = new ValidateLoginExecutor(rq, session);
        BuildLoginExecutor build = new BuildLoginExecutor(rq, session);

        try {
            read.setNext(validate);
            validate.setNext(build);
            page = read.execute(null, null);
        } catch (EmptyFieldException | IncorrectDataException | NoCreditCardsException
                | NoFlightsException | NoToursException | WrongAccountException e) {
            rq.setAttribute(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            page = Page.LOGIN.getPage();
            logger.info(e.getMessage());
        }
        return page;
    }


}
