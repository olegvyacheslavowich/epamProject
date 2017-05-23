package validation.impl;

import constant.Key;
import constant.Regex;
import exception.*;
import service.ResourceManager;
import validation.Validation;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 16.05.2017.
 */
public class LoginPageValidation extends Validation {

    private String login;
    private String password;

    public LoginPageValidation(HttpSession session, String login, String password) {
        super(session);
        this.login = login;
        this.password = password;
    }

    @Override
    public void isValid() throws EmptyFieldException, IncorrectDataException, NoFlightsException, NoCreditCardsException, NoToursException {
        if (!login.matches(Regex.LOGIN.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_LOGIN.getKey(), getCurrentLanguage()));
        }
        if (!password.matches(Regex.PASSWORD.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_PASSWORD.getKey(), getCurrentLanguage()));
        }
    }
}
