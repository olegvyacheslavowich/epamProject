package command.impl;

import command.ActionCommand;
import constant.Pages;
import daolayer.ClientDAO;
import exception.WrongAccountException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 20_ok on 20.03.2017.
 */
public class LoginCommand implements ActionCommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String WRONG = "incorrectLoginOrPassword";

    @Override
    public String execute(HttpServletRequest rq) {

        String login = rq.getParameter(LOGIN);
        String password = rq.getParameter(PASSWORD);
        String page;

        try {
            authorization(login, password);
            rq.setAttribute(login, login);
            rq.setAttribute(password, password);
            page = Pages.INDEX.getPage();
        } catch (WrongAccountException e) {
            rq.setAttribute(WRONG, "Неверный логин или пароль");
            page = Pages.LOGIN.getPage();
            e.printStackTrace();
        }

        return page;
    }

    private void authorization(String login, String password) throws WrongAccountException {

        ClientDAO clientDAO = new ClientDAO();
        if (clientDAO.getData(login, password) == null) {
            throw new WrongAccountException("Wrong login or password");
        }
    }


}
