package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Key;
import constant.Page;
import constant.Parameter;
import entity.*;
import exception.DataBaseConnectionException;
import exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Language;
import util.ResourceManager;
import service.MoneyService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class FlightCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(FlightCommand.class);


    @Override
    public String execute(HttpServletRequest rq) {

        String page = Page.FLIGHT;
        HttpSession session = rq.getSession();
        MoneyService moneyService = new MoneyService();

        ArrayList<String> fullNames = new ArrayList<>();
        ArrayList<String> birthdays = new ArrayList<>();
        ArrayList<String> papers = new ArrayList<>();
        ArrayList<String> documentNums = new ArrayList<>();
        ArrayList<String> phones = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();

        int adultNumber = Integer.parseInt((String) session.getAttribute(Attribute.ADULT_NUMBER));

        for (int i = 1; i <= adultNumber; i++) {
            if (Validator.isEmpty(rq.getParameter(Parameter.FULL_NAME + i))) {
                rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_FULL_NAME, Language.getCurrentLanguage(session)));
                logger.info("Flight page error :" + ResourceManager.getResource(Key.EX_ENTER_FULL_NAME, Language.getCurrentLanguage(session)));
                return page;
            } else if (Validator.isEmpty(rq.getParameter(Parameter.BIRTHDAY + i))) {
                rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_BIRTHDAY, Language.getCurrentLanguage(session)));
                logger.info("Flight page error :" + ResourceManager.getResource(Key.EX_ENTER_BIRTHDAY, Language.getCurrentLanguage(session)));
                return page;
            } else if (Validator.isEmpty(rq.getParameter(Parameter.PAPER + i))) {
                rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_PAPER, Language.getCurrentLanguage(session)));
                logger.info("Flight page error :" + ResourceManager.getResource(Key.EX_ENTER_PAPER, Language.getCurrentLanguage(session)));
                return page;
            } else if (Validator.isEmpty(rq.getParameter(Parameter.DOCUMENT_NUM + i))) {
                rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_PAPER_NUM, Language.getCurrentLanguage(session)));
                logger.info("Flight page error :" + ResourceManager.getResource(Key.EX_ENTER_PAPER_NUM, Language.getCurrentLanguage(session)));
                return page;
            } else if (Validator.isEmpty(rq.getParameter(Parameter.PHONE + i))) {
                rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_PHONE, Language.getCurrentLanguage(session)));
                logger.info("Flight page error :" + ResourceManager.getResource(Key.EX_ENTER_PHONE, Language.getCurrentLanguage(session)));
                return page;
            } else if (Validator.isEmpty(rq.getParameter(Parameter.EMAIL + i))) {
                rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(Key.EX_ENTER_EMAIL, Language.getCurrentLanguage(session)));
                logger.info("Flight page error :" + ResourceManager.getResource(Key.EX_ENTER_EMAIL, Language.getCurrentLanguage(session)));
                return page;
            }
            fullNames.add(rq.getParameter(Parameter.FULL_NAME + i));
            birthdays.add(rq.getParameter(Parameter.BIRTHDAY + i));
            papers.add(rq.getParameter(Parameter.PAPER + i));
            documentNums.add(rq.getParameter(Parameter.DOCUMENT_NUM + i));
            phones.add(rq.getParameter(Parameter.PHONE + i));
            emails.add(rq.getParameter(Parameter.EMAIL + i));
        }

        Tour tour = (Tour) session.getAttribute(Attribute.TOUR);
        User user = (User) session.getAttribute(Attribute.USER);
        Flight flightTo = (Flight) session.getAttribute(Attribute.FLIGHT_TO);
        Flight flightFrom = (Flight) session.getAttribute(Attribute.FLIGHT_FROM);
        int childNumber = Integer.parseInt((String) session.getAttribute(Attribute.CHILDREN_NUMBER));

        Account account = user.getAccount();
        int price = countPrice(tour.getPrice(), adultNumber);
        try {
            session.setAttribute(Attribute.VOUCHER, new Voucher(tour, flightTo, flightFrom, adultNumber + childNumber, price));
            logger.info("Voucher was created");
            session.setAttribute(Attribute.CLIENTS, buildClients(fullNames, birthdays, papers, documentNums, phones, emails, adultNumber));
            logger.info("Clients was created");
        } catch (ServiceException e) {
            rq.setAttribute(Attribute.EXCEPTION, ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            logger.info("Card page error :" + ResourceManager.getResource(e.getMessage(), Language.getCurrentLanguage(session)));
            return page;
        }
        try {
            session.setAttribute(Attribute.MONEYS, moneyService.readByLogin(account));
            logger.info("Money was created");
            page = Page.PAYMENT;
        } catch (DataBaseConnectionException e) {
            logger.info("Flight page error :", e);
            return page;
        }

        return page;
    }


    private int countPrice(int price, int clientNumber) {
        return price * clientNumber;
    }

    private List<Client> buildClients(List<String> fullNames,
                                      List<String> birthdays,
                                      List<String> papers,
                                      List<String> documentNums,
                                      List<String> phones,
                                      List<String> emails,
                                      int adultNumber) throws ServiceException {

        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < adultNumber; i++) {

            if (!Validator.isRusEngString(fullNames.get(i))) {
                throw new ServiceException(Key.EX_INCORRECT_FULL_NAME);
            } else if (!Validator.isDate(birthdays.get(i))) {
                throw new ServiceException(Key.EX_INCORRECT_DATE);
            } else if (!Validator.isRusEngString(papers.get(i))) {
                throw new ServiceException(Key.EX_INCORRECT_PAPER);
            } else if (!Validator.isNumber(documentNums.get(i))) {
                throw new ServiceException(Key.EX_INCORRECT_PAPER_NUM);
            } else if (!Validator.isMobileNumber(phones.get(i))) {
                throw new ServiceException(Key.EX_INCORRECT_PHONE);
            } else if (!Validator.isEmail(emails.get(i))) {
                throw new ServiceException(Key.EX_INCORRECT_EMAIL);
            }
            Client client = new Client();
            client.setFullName(fullNames.get(i));
            client.setBirthday(Date.valueOf(birthdays.get(i)));
            client.setPaper(papers.get(i));
            client.setDocumentNum(Integer.parseInt(documentNums.get(i)));
            client.setPhone(phones.get(i));
            client.setEmail(emails.get(i));
            clients.add(client);
        }
        return clients;
    }


}
