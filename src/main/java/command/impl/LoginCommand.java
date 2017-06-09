package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Key;
import constant.Page;
import constant.Parameter;
import entity.Account;
import entity.Admin;
import entity.User;
import exception.DataBaseConnectionException;
import exception.ServiceException;
import exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Language;
import util.ResourceManager;
import service.AccountService;
import service.AdminService;
import service.UserService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page = Page.LOGIN;
        HttpSession session = rq.getSession();
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        AdminService adminService = new AdminService();

        if (Validator.isEmpty(rq.getParameter(Parameter.LOGIN))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_LOGIN, Language.getCurrentLanguage(session)));
            logger.info("Login page error :" + ResourceManager.getResource(Key.EX_ENTER_LOGIN, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(rq.getParameter(Parameter.PASSWORD))) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_PASSWORD, Language.getCurrentLanguage(session)));
            logger.info("Login page error :" + ResourceManager.getResource(Key.EX_ENTER_PASSWORD, Language.getCurrentLanguage(session)));
            return page;
        }

        String password = rq.getParameter(Parameter.PASSWORD);
        String login = rq.getParameter(Parameter.LOGIN);

        try {
            Account account = accountService.checkAccount(login, password);
            logger.info("Account was checked");
            User user = userService.readUserByAccount(account);
            logger.info("User " + user.getFullName() + " was came in ");
            Admin admin = new Admin(account);
            admin = adminService.readByLogin(admin);
            session.setAttribute(Attribute.USER, user);
            session.setAttribute(Attribute.ADMIN, admin);
            session.removeAttribute(Attribute.TOURS);
            page = Page.INDEX;
        } catch (ServiceException e) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            logger.info("Login page error :" + ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            return page;
        } catch (ValidationException e) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            logger.info("Login page error :" + ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            return page;
        } catch (DataBaseConnectionException e) {
            logger.info("Login page error :", e);
            return page;
        }

        return page;
    }


}
