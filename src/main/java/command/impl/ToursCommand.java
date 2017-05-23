package command.impl;

import com.sun.istack.internal.logging.Logger;
import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import exception.*;
import executor.impl.tours.impl.BuildToursExecutor;
import executor.impl.tours.impl.ReadToursExecutor;
import executor.impl.tours.impl.ValidateToursExecutor;
import validation.impl.ToursPageValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToursCommand implements ActionCommand {

    private Logger logger = Logger.getLogger(ToursCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page;
        HttpSession session = rq.getSession();

        ReadToursExecutor read = new ReadToursExecutor(rq, session);
        ValidateToursExecutor validate = new ValidateToursExecutor(rq, session);
        BuildToursExecutor build = new BuildToursExecutor(rq, session);

        read.setNext(validate);
        validate.setNext(build);

        try {
            page = read.execute(null, null);
        } catch (NoFlightsException | EmptyFieldException | IncorrectDataException |
                NoCreditCardsException | NoToursException e) {
            page = Page.TOURS.getPage();
            rq.setAttribute(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            logger.info(e.getMessage());
        }

        return page;

    }


}
