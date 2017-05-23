package command.impl;

import com.sun.istack.internal.logging.Logger;
import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import exception.EmptyFieldException;
import exception.IncorrectDataException;
import exception.NoFlightsException;
import exception.NoToursException;
import executor.impl.search.impl.BuildIndexExecutor;
import executor.impl.search.impl.ReadIndexExecutor;
import executor.impl.search.impl.ValidateIndexExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;

/**
 * Created by 20_ok on 25.04.2017.
 */
public class SearchCommand implements ActionCommand {

    private Logger logger = Logger.getLogger(SearchCommand.class);


    @Override
    public String execute(HttpServletRequest rq) {

        String page;
        HttpSession session = rq.getSession();
        ReadIndexExecutor read = new ReadIndexExecutor(rq, session);
        ValidateIndexExecutor validate = new ValidateIndexExecutor(rq, session);
        BuildIndexExecutor build = new BuildIndexExecutor(rq, session);

        read.setNext(validate);
        validate.setNext(build);
        try {
            page = read.execute(null, null, null, null, null, null);
        } catch (NoToursException | NoFlightsException | EmptyFieldException | IncorrectDataException e) {
            page = Page.INDEX.getPage();
            rq.setAttribute(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            logger.info(e.getMessage());
        }

        return page;
    }


}
