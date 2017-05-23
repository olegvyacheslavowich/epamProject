package command.impl;

import com.sun.istack.internal.logging.Logger;
import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import exception.*;
import executor.impl.card.impl.BuildCardExecutor;
import executor.impl.card.impl.ReadCardExecutor;
import executor.impl.card.impl.ValidateCardExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 18.05.2017.
 */
public class CardCommand implements ActionCommand {

    private Logger logger = Logger.getLogger(CardCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page;

        HttpSession session = rq.getSession();
        ReadCardExecutor read = new ReadCardExecutor(rq, session);
        ValidateCardExecutor validate = new ValidateCardExecutor(rq, session);
        BuildCardExecutor build = new BuildCardExecutor(rq, session);

        read.setNext(validate);
        validate.setNext(build);
        try {
            page = read.execute(null, null, null, null, null);
        } catch (EmptyFieldException | NoCreditCardsException | NoFlightsException | NoToursException | IncorrectDataException e) {
            rq.setAttribute(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            page = Page.CARD.getPage();
            logger.info(e.getMessage());
        }

        return page;
    }
}
