package command;

import constant.Parameter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 20_ok on 20.03.2017.
 */

/**
 *
 */
public class ActionFactory {

    public ActionCommand getCommand(HttpServletRequest rq) {

        ActionCommand command = CommandEnum.TRANSLATOR.getCommand();
        String commandValue = rq.getParameter(Parameter.COMMAND.getParameter());

        if (commandValue != null) {
            CommandEnum commandEnum = CommandEnum.valueOf(commandValue.toUpperCase());
            command = commandEnum.getCommand();
        }
        return command;
    }
}
