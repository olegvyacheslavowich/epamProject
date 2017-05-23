package command.impl;

import com.sun.istack.internal.logging.Logger;
import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import entity.HotTour;
import exception.EmptyFieldException;
import executor.impl.hottour.impl.BuildHotTourExecutor;
import executor.impl.hottour.impl.ReadHotTourExecutor;
import executor.impl.hottour.impl.ValidateHotTourExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HotTourCommand implements ActionCommand {

    private Logger logger = Logger.getLogger(HotTourCommand.class);


    @Override
    public String execute(HttpServletRequest rq) {

        String page;
        HttpSession session = rq.getSession();

        ReadHotTourExecutor read = new ReadHotTourExecutor(rq, session);
        ValidateHotTourExecutor validate = new ValidateHotTourExecutor(rq, session);
        BuildHotTourExecutor build = new BuildHotTourExecutor(rq, session);

        read.setNext(validate);
        validate.setNext(build);

        try {
            page = read.execute(null);
        } catch (EmptyFieldException e) {
            rq.setAttribute(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            page = Page.INDEX.getPage();
            logger.info(e.getMessage());
        }

        return page;
    }
}
