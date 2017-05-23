package validation.impl;

import constant.Key;
import constant.Regex;
import exception.EmptyFieldException;
import exception.IncorrectDataException;
import service.ResourceManager;
import validation.Validation;

import javax.servlet.http.HttpSession;


public class RegistrationPageValidation extends Validation {

    private String fullName;
    private String paper;
    private String documentNum;
    private String phone;
    private String birthday;
    private String email;
    private String login;
    private String password;
    private String rpassword;

    public RegistrationPageValidation(HttpSession session, String fullName, String paper, String documentNum, String phone, String birthday, String email, String login, String password, String rpassword) {
        super(session);
        this.fullName = fullName;
        this.paper = paper;
        this.documentNum = documentNum;
        this.phone = phone;
        this.birthday = birthday;
        this.email = email;
        this.login = login;
        this.password = password;
        this.rpassword = rpassword;
    }

    @Override
    public void isValid() throws EmptyFieldException, IncorrectDataException {
        if (!fullName.matches(Regex.RUS_AND_ENG_LETTERS.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_FULL_NAME.getKey(), getCurrentLanguage()));

        }
        if (!paper.matches(Regex.RUS_AND_ENG_LETTERS.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_PAPER.getKey(), getCurrentLanguage()));

        }
        if (!documentNum.matches(Regex.NUMBER.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_PAPER_NUM.getKey(), getCurrentLanguage()));

        }
        if (!phone.matches(Regex.MOBILE_NUMBER.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_PHONE.getKey(), getCurrentLanguage()));

        }
        if (!birthday.matches(Regex.DATE.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_BIRTHDAY.getKey(), getCurrentLanguage()));

        }
        if (!email.matches(Regex.EMAIL.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_EMAIL.getKey(), getCurrentLanguage()));

        }
        if (!login.matches(Regex.LOGIN.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_LOGIN.getKey(), getCurrentLanguage()));

        }
        if (!password.matches(Regex.PASSWORD.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_PASSWORD.getKey(), getCurrentLanguage()));

        }
        if (!rpassword.matches(Regex.PASSWORD.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_PASSWORD.getKey(), getCurrentLanguage()));

        }

    }
}
