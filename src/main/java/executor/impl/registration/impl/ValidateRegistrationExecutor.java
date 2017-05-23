package executor.impl.registration.impl;

import exception.DifferentPasswordsException;
import exception.EmptyFieldException;
import exception.ExistingAccountException;
import exception.IncorrectDataException;
import executor.impl.registration.RegistrationExecutor;
import validation.impl.RegistrationPageValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ValidateRegistrationExecutor extends RegistrationExecutor {

    private RegistrationExecutor next;

    public ValidateRegistrationExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String fullName, String paper,
                          String documentNum, String phone, String birthday,
                          String email, String login, String password, String rpassword) throws EmptyFieldException,
            IncorrectDataException, ExistingAccountException, DifferentPasswordsException {

        String page = null;

        RegistrationPageValidation validation = new RegistrationPageValidation
                (getSession(), fullName, paper, documentNum, phone, birthday, email, login, password, rpassword);
        validation.isValid();


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
