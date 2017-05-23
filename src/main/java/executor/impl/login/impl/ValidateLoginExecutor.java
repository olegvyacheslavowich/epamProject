package executor.impl.login.impl;

import executor.impl.login.LoginExecutor;
import exception.*;
import validation.Validation;
import validation.impl.LoginPageValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ValidateLoginExecutor extends LoginExecutor {

    private LoginExecutor next;

    public ValidateLoginExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String login, String password) throws EmptyFieldException, NoCreditCardsException, NoFlightsException, NoToursException, IncorrectDataException, WrongAccountException {

        String page = null;
        Validation validation = new LoginPageValidation(getSession(), login, password);
        validation.isValid();

        if (next != null) {
            page = next.execute(login, password);
        }

        return page;
    }

    public void setNext(LoginExecutor next) {
        this.next = next;
    }
}
