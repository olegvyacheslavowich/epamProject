package command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 20_ok on 20.03.2017.
 */
public interface ActionCommand {

    String COMMAND = "command";

    String execute(HttpServletRequest rq);
}
