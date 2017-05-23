package command;

import command.impl.*;

/**
 * Created by 20_ok on 20.03.2017.
 */

/**
 * Класс с  формируемыми страницами
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
    },

    LOGOUT {
        {
            command = new LogoutCommand();
        }
    },

    TRANSLATOR {
        {
            command = new TranslatorCommand();
        }
    },

    SEARCH {
        {
            command = new SearchCommand();
        }
    },

    TOURS {
        {
            command = new ToursCommand();
        }
    },

    PLAINS {
        {
            command = new FlightCommand();
        }
    },

    FLIGHT {
        {
            command = new FlightCommand();
        }
    },

    TOURSSORTING {
        {
            command = new ToursSortingCommand();
        }
    },

    PAYMENT {
        {
            command = new PaymentCommand();
        }
    },

    USER {
        {
            command = new UserCommand();
        }
    },
    REFUSE {
        {
            command = new RefuseCommand();
        }
    },

    CARD {
        {
            command = new CardCommand();
        }
    },

    ADMIN {
        {
            command = new AdminCommand();
        }
    },

    HOTTOUR {
        {
            command = new HotTourCommand();
        }
    };


    ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
    }
