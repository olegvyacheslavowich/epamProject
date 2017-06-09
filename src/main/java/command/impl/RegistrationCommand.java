package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Key;
import constant.Page;
import constant.Parameter;
import entity.Account;
import entity.Admin;
import exception.DataBaseConnectionException;
import exception.ServiceException;
import exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Language;
import util.ResourceManager;
import service.AdminService;
import service.RegistrationService;
import service.UserService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page = Page.REGISTRATION;
        HttpSession session = rq.getSession();
        RegistrationService registrationService = new RegistrationService();
        UserService userService = new UserService();
        AdminService adminService = new AdminService();

        if (Validator.isEmpty(rq.getParameter(Parameter.FULL_NAME))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_FULL_NAME, Language.getCurrentLanguage(session)));
            logger.info("Registration page error :" + ResourceManager.getResource(Key.EX_ENTER_FULL_NAME, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(rq.getParameter(Parameter.PAPER))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_PAPER, Language.getCurrentLanguage(session)));
            logger.info("Registration page error :" + ResourceManager.getResource(Key.EX_ENTER_PAPER, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(rq.getParameter(Parameter.DOCUMENT_NUM))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_PAPER_NUM, Language.getCurrentLanguage(session)));
            logger.info("Registration page error :" + ResourceManager.getResource(Key.EX_ENTER_PAPER_NUM, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(rq.getParameter(Parameter.PHONE))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_PHONE, Language.getCurrentLanguage(session)));
            logger.info("Registration page error :" + ResourceManager.getResource(Key.EX_ENTER_PHONE, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(rq.getParameter(Parameter.YEAR))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_BIRTHDAY, Language.getCurrentLanguage(session)));
            logger.info("Registration page error :" + ResourceManager.getResource(Key.EX_ENTER_BIRTHDAY, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(rq.getParameter(Parameter.EMAIL))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_EMAIL, Language.getCurrentLanguage(session)));
            logger.info("Registration page error :" + ResourceManager.getResource(Key.EX_ENTER_EMAIL, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(rq.getParameter(Parameter.LOGIN))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_LOGIN, Language.getCurrentLanguage(session)));
            logger.info("Registration page error :" + ResourceManager.getResource(Key.EX_ENTER_LOGIN, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(rq.getParameter(Parameter.PASSWORD))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_PASSWORD, Language.getCurrentLanguage(session)));
            logger.info("Registration page error :" + ResourceManager.getResource(Key.EX_ENTER_PASSWORD, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(rq.getParameter(Parameter.REPEAT_PASSWORD))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_PASSWORD, Language.getCurrentLanguage(session)));
            logger.info("Registration page error :" + ResourceManager.getResource(Key.EX_ENTER_PASSWORD, Language.getCurrentLanguage(session)));
            return page;
        }

        String fullName = rq.getParameter(Parameter.FULL_NAME);
        String paper = rq.getParameter(Parameter.PAPER);
        String documentNum = rq.getParameter(Parameter.DOCUMENT_NUM);
        String phone = rq.getParameter(Parameter.PHONE);
        String birthday = rq.getParameter(Parameter.BIRTHDAY);
        String email = rq.getParameter(Parameter.EMAIL);
        String login = rq.getParameter(Parameter.LOGIN);
        String password = rq.getParameter(Parameter.PASSWORD);
        String rpassword = rq.getParameter(Parameter.REPEAT_PASSWORD);

        try {
            Account account =
                    registrationService.register(fullName, paper, documentNum, phone, birthday, email, login, password, rpassword);
            logger.info("New user was register with login:" + account.getLogin());
            Admin admin = new Admin(account);
            session.setAttribute(Attribute.USER, userService.readUserByAccount(account));
            session.setAttribute(Attribute.ADMIN, adminService.readByLogin(admin));
            page = Page.CARD;
        } catch (ServiceException | ValidationException e) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            logger.info("Registration page error :" + ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            return page;
        } catch (DataBaseConnectionException e) {
            logger.info("Registration page error " + e.getMessage());
        }

        return page;
    }
}
