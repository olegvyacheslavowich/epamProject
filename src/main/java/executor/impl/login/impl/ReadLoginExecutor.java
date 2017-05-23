package executor.impl.login.impl;

import constant.Key;
import executor.impl.login.LoginExecutor;
import constant.Parameter;
import exception.*;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ReadLoginExecutor extends LoginExecutor {

    private LoginExecutor next;

    public ReadLoginExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String login, String password) throws EmptyFieldException,
            NoCreditCardsException, NoFlightsException, NoToursException,
            IncorrectDataException, WrongAccountException {

        String page = null;

        if (!readFromRequest(Parameter.LOGIN.getParameter()).isEmpty()) {
            login = readFromRequest(Parameter.LOGIN.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_LOGIN.getKey(), getCurrentLanguage()));
        }
        if (!readFromRequest(Parameter.PASSWORD.getParameter()).isEmpty()) {
            password = readFromRequest(Parameter.PASSWORD.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_PASSWORD.getKey(), getCurrentLanguage()));
        }

        if (next != null) {
            page = next.execute(login, password);
        }

        return page;
    }

    public void setNext(LoginExecutor next) {
        this.next = next;
    }
}
