package command;

import command.impl.EmptyCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by 20_ok on 20.03.2017.
 */
public class ActionFactory {

    public ActionCommand getCommand(HttpServletRequest rq) {

        ActionCommand command = new EmptyCommand();
        String paramValue = rq.getParameter(ActionCommand.COMMAND);

        if (!paramValue.isEmpty()) {
            try {
                CommandEnum commandEnum = CommandEnum.valueOf(paramValue.toUpperCase());
                command = commandEnum.getCommand();
            } catch (IllegalArgumentException e) {
                command = new EmptyCommand();
            }
        }

        return command;
    }
}
