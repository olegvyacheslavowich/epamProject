package command.impl;

import com.sun.istack.internal.logging.Logger;
import command.ActionCommand;
import constant.Attribute;
import constant.Num;
import constant.Page;
import exception.*;
import executor.impl.flight.impl.BuildFlightExecutor;
import executor.impl.flight.impl.ReadFlightExecutor;
import executor.impl.flight.impl.ValidateFlightExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;


public class FlightCommand implements ActionCommand {

    private Logger logger = Logger.getLogger(FlightCommand.class);


    @Override
    public String execute(HttpServletRequest rq) {

        String page;

        HttpSession session = rq.getSession();
        ReadFlightExecutor read = new ReadFlightExecutor(rq, session);
        ValidateFlightExecutor validate = new ValidateFlightExecutor(rq, session);
        BuildFlightExecutor build = new BuildFlightExecutor(rq, session);

        read.setNext(validate);
        validate.setNext(build);
        try {
            page = read.execute(null, null, null, null, null, null, Num.NULL.getNum());
        } catch (EmptyFieldException | NoCreditCardsException | NoToursException | NoFlightsException | IncorrectDataException e) {
            rq.setAttribute(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            page = Page.FLIGHT.getPage();
            logger.info(e.getMessage());
        }
        return page;
    }


}
