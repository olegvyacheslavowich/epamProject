package executor.impl.registration.impl;

import constant.Key;
import constant.Parameter;
import exception.DifferentPasswordsException;
import exception.EmptyFieldException;
import exception.ExistingAccountException;
import exception.IncorrectDataException;
import executor.impl.registration.RegistrationExecutor;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 21.05.2017.
 */
public class ReadRegistrationExecutor extends RegistrationExecutor {

    private RegistrationExecutor next;

    public ReadRegistrationExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String fullName, String paper, String documentNum,
                          String phone, String birthday, String email,
                          String login, String password, String rpassword) throws EmptyFieldException, IncorrectDataException, ExistingAccountException, DifferentPasswordsException {
        String page = null;

        if (!readFromRequest(Parameter.FULL_NAME.getParameter()).isEmpty()) {
            fullName = readFromRequest(Parameter.FULL_NAME.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_FULL_NAME.getKey(), getCurrentLanguage()));
        }
        if (!readFromRequest(Parameter.PAPER.getParameter()).isEmpty()) {
            paper = readFromRequest(Parameter.PAPER.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_PAPER.getKey(), getCurrentLanguage()));

        }
        if (!readFromRequest(Parameter.DOCUMENT_NUM.getParameter()).isEmpty()) {
            documentNum = readFromRequest(Parameter.DOCUMENT_NUM.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_PAPER_NUM.getKey(), getCurrentLanguage()));

        }
        if (!readFromRequest(Parameter.PHONE.getParameter()).isEmpty()) {
            phone = readFromRequest(Parameter.PHONE.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_PHONE.getKey(), getCurrentLanguage()));

        }
        if (!readFromRequest(Parameter.YEAR.getParameter()).isEmpty()) {
            birthday = readFromRequest(Parameter.YEAR.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_BIRTHDAY.getKey(), getCurrentLanguage()));

        }
        if (!readFromRequest(Parameter.EMAIL.getParameter()).isEmpty()) {
            email = readFromRequest(Parameter.EMAIL.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_EMAIL.getKey(), getCurrentLanguage()));

        }
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
        if (!readFromRequest(Parameter.REPEAT_PASSWORD.getParameter()).isEmpty()) {
            rpassword = readFromRequest(Parameter.REPEAT_PASSWORD.getParameter());
        } else {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_REPEAT_PASSWORD.getKey(), getCurrentLanguage()));
        }

        if (next != null) {
            page = next.execute(fullName, paper, documentNum,
                    phone, birthday, email, login, password, rpassword);
        }

        return page;

    }

    public void setNext(RegistrationExecutor next) {
        this.next = next;
    }

}
