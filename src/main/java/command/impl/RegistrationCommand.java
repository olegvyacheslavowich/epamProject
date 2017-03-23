package command.impl;

import command.ActionCommand;
import constant.Pages;
import daolayer.ClientDAO;
import entity.Client;
import exception.DifferentPasswordsException;
import exception.ExistingAccountException;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by 20_ok on 20.03.2017.
 */
public class RegistrationCommand implements ActionCommand {

    private static final String LOGIN = "exciting_account";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PATRONYMIC = "patronymic";
    private static final String YEAR = "year";
    private static final String CITY = "city";
    private static final String MOBILE = "mobile";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "rpassword";
    private static final String EXCEPTION = "exception";

    @Override
    public String execute(HttpServletRequest rq) {

        String page;
        Client client = getClient(rq);
        ClientDAO clientDAO = new ClientDAO();

        try {
            checkAccount(client.getLogin());
            comparePass(client.getPassword(), rq.getParameter(REPEAT_PASSWORD));
            clientDAO.create(client);
            page = Pages.INDEX.getPage();

        } catch (ExistingAccountException e) {
            e.printStackTrace();
            rq.setAttribute(EXCEPTION, "Логин уже существует");
            page = Pages.LOGIN.getPage();
        } catch (DifferentPasswordsException e) {
            rq.setAttribute(EXCEPTION, "Пароли не совпадают");
            page = Pages.LOGIN.getPage();
            e.printStackTrace();
        }

        return page;
    }

    private Client getClient(HttpServletRequest rq) {

        Client client = new Client();
        client.setLogin(rq.getParameter(LOGIN));
        client.setName(rq.getParameter(NAME));
        client.setSurname(rq.getParameter(SURNAME));
        client.setPatronymic(rq.getParameter(PATRONYMIC));
        client.setYear(rq.getParameter(YEAR));
        client.setCity(rq.getParameter(CITY));
        client.setMobile(rq.getParameter(MOBILE));
        client.setEmail(rq.getParameter(EMAIL));
        client.setPassword(rq.getParameter(PASSWORD));

        return client;

    }

    private void checkAccount(String login) throws ExistingAccountException {

        ClientDAO clientDAO = new ClientDAO();

        if (clientDAO.checkField(login, ClientDAO.CHECK_ACCOUNT) == -1) {
            throw new ExistingAccountException("Account существет");
        }

    }

    private void comparePass(String pass1, String pass2) throws DifferentPasswordsException {

        if (!Objects.equals(pass1, pass2)) {
            throw new DifferentPasswordsException("Different passwords");
        }
    }
}
