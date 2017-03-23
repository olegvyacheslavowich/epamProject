package command;

import command.impl.LoginCommand;
import command.impl.RegistrationCommand;

/**
 * Created by 20_ok on 20.03.2017.
 */
public enum CommandEnum {

    REGISTRATION {
        {
            command = new RegistrationCommand();
        }
    },

    LOGIN {
        {
            command = new LoginCommand();
        }
    };


    ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
}
