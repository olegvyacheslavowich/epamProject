package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Key;
import constant.Page;
import constant.Parameter;
import entity.Account;
import entity.CreditCard;
import entity.Money;
import entity.User;
import exception.DataBaseConnectionException;
import exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Language;
import util.ResourceManager;
import service.CreditCardService;
import service.MoneyService;
import service.OrderService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CardCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(CardCommand.class);

    @Override
    public String execute(HttpServletRequest rq) {

        String page = Page.CARD;
        HttpSession session = rq.getSession();
        MoneyService moneyService = new MoneyService();
        OrderService orderService = new OrderService();
        CreditCardService creditCardService = new CreditCardService();

        String ownerNameParam = rq.getParameter(Parameter.OWNER_NAME);
        String numberParam = rq.getParameter(Parameter.NUMBER);
        String cvvParam = rq.getParameter(Parameter.CVV);
        String dateParam = rq.getParameter(Parameter.DATE);
        String typeParam = rq.getParameter(Parameter.TYPE);

        if (Validator.isEmpty(ownerNameParam)) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_OWNER_NAME, Language.getCurrentLanguage(session)));
            logger.info("Card page error :" + ResourceManager.getResource(Key.EX_ENTER_OWNER_NAME, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(numberParam)) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_CARD_NUMBER, Language.getCurrentLanguage(session)));
            logger.info("Card page error :" + ResourceManager.getResource(Key.EX_ENTER_CARD_NUMBER, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(cvvParam)) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_CVV, Language.getCurrentLanguage(session)));
            logger.info("Card page error :" + ResourceManager.getResource(Key.EX_ENTER_CVV, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(dateParam)) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_DATE, Language.getCurrentLanguage(session)));
            logger.info("Card page error :" + ResourceManager.getResource(Key.EX_ENTER_DATE, Language.getCurrentLanguage(session)));
            return page;
        } else if (Validator.isEmpty(typeParam)) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_CARD_TYPE, Language.getCurrentLanguage(session)));
            logger.info("Card page error :" + ResourceManager.getResource(Key.EX_ENTER_CARD_TYPE, Language.getCurrentLanguage(session)));
            return page;
        }

        User user = (User) session.getAttribute(Attribute.USER);
        Account account = user.getAccount();
        CreditCard creditCard;
        try {
            creditCard = creditCardService.createNewCard(typeParam, ownerNameParam, numberParam, cvvParam, dateParam);
            logger.info("New credit card was created");
            moneyService.create(new Money(account, creditCard));
            logger.info("Money was created");
            rq.setAttribute(Attribute.MONEYS, moneyService.readByLogin(account));
            rq.setAttribute(Attribute.ORDERS, orderService.getAllByAccount(account));
            page = Page.USER;
        } catch (ValidationException e) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            logger.info("Card page error :" + ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            return page;
        } catch (DataBaseConnectionException e) {
            logger.info("Card page error :", e);
            return page;
        }

        return page;
    }
}