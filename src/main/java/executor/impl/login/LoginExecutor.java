package executor.impl.login;

import exception.*;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class LoginExecutor extends PageExecutor {


    public LoginExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public abstract String execute(String login, String password) throws
            EmptyFieldException, NoCreditCardsException, NoFlightsException, NoToursException,
            IncorrectDataException, WrongAccountException;

}
