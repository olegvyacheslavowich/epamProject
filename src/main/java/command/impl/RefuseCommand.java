package command.impl;

import com.sun.istack.internal.logging.Logger;
import command.ActionCommand;
import constant.Attribute;
import constant.Page;
import exception.EmptyFieldException;
import exception.NoCreditCardsException;
import executor.impl.refuse.impl.BuildRefuseExecutor;
import executor.impl.refuse.impl.ReadRefuseExecutor;
import executor.impl.refuse.impl.ValidateRefuseExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 18.05.2017.
 */
public class RefuseCommand implements ActionCommand {

    private Logger logger = Logger.getLogger(RefuseCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page;
        HttpSession session = rq.getSession();
        ReadRefuseExecutor read = new ReadRefuseExecutor(rq, session);
        ValidateRefuseExecutor validate = new ValidateRefuseExecutor(rq, session);
        BuildRefuseExecutor build = new BuildRefuseExecutor(rq, session);

        read.setNext(validate);
        validate.setNext(build);
        try {
            page = read.execute(null);
        } catch (EmptyFieldException | NoCreditCardsException e) {
            rq.setAttribute(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            page = Page.USER.getPage();
            logger.info(e.getMessage());
        }

        return page;
    }
}
