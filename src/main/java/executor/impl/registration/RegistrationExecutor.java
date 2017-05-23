package executor.impl.registration;

import exception.*;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 21.05.2017.
 */
public abstract class RegistrationExecutor extends PageExecutor {

    public RegistrationExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public abstract String execute(String fullName, String paper, String documentNum,
                                   String phone, String birthday, String email, String login,
                                   String password, String rpassword) throws EmptyFieldException, IncorrectDataException, ExistingAccountException, DifferentPasswordsException;

}
