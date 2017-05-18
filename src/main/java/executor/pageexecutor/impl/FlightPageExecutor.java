package executor.pageexecutor.impl;

import constant.Attribute;
import constant.Num;
import constant.Page;
import constant.Parameter;
import daolayer.impl.AccountDAO;
import daolayer.impl.MoneyDao;
import entity.*;
import exception.*;
import executor.pageexecutor.PageExecutor;
import validation.Validation;
import validation.impl.FlightPageValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class FlightPageExecutor extends PageExecutor {

    private String page = Page.PAYMENT.getPage();
    private Voucher voucher;
    private int adultNumber;
    private int childNumber;
    private Tour tour;
    private User user;
    private Flight flightTo;
    private Flight flightFrom;
    private List<Client> clients;
    private List<String> fullNames = new ArrayList<>();
    private List<Date> birthdays = new ArrayList<>();
    private List<String> papers = new ArrayList<>();
    private List<Integer> documentNums = new ArrayList<>();
    private List<String> phones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
    private List<Money> moneys;


    public FlightPageExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public void read() {

        tour = (Tour) readFromSession(Attribute.TOUR.getAttribute());
        flightTo = (Flight) readFromSession(Attribute.FLIGHT_TO.getAttribute());
        flightFrom = (Flight) readFromSession(Attribute.FLIGHT_FROM.getAttribute());
        adultNumber = (int) readFromSession(Attribute.ADULT_NUMBER.getAttribute());
        childNumber = (int) readFromSession(Attribute.CHILDREN_NUMBER.getAttribute());
        user = (User) readFromSession(Attribute.USER.getAttribute());
        for (int i = 1; i <= adultNumber; i++) {
            fullNames.add(readFromRequest(Parameter.FULL_NAME.getParameter() + i));
            birthdays.add(readBirthday(i));
            papers.add(readFromRequest(Parameter.PAPER.getParameter() + i));
            documentNums.add(readTourId(i));
            phones.add(readFromRequest(Parameter.PHONE.getParameter() + i));
            emails.add(readFromRequest(Parameter.EMAIL.getParameter() + i));
        }


    }

    @Override
    public void validate() {

        Validation validation = new FlightPageValidation(
                fullNames,
                birthdays,
                documentNums,
                phones,
                emails);
        try {
            validation.isValid();
        } catch (EmptyFieldException | IncorrectDataException
                | NoFlightsException | NoCreditCardsException | NoToursException e) {
            setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            page = Page.FLIGHT.getPage();
        }

    }

    @Override
    public void build() {
        Account account = user.getAccount();
        int price = countPrice(tour.getPrice(), adultNumber);
        voucher = new Voucher(tour, flightTo, flightFrom, adultNumber + childNumber, price);
        clients = buildClients(fullNames, birthdays, papers, documentNums, phones, emails);
        try {
            moneys = getMoneyDBService().readByLogin(account);
        } catch (NoCreditCardsException e) {
            setToRequest(Attribute.EXCEPTION.getAttribute(), e.getMessage());
            page = Page.FLIGHT.getPage();
        }

    }

    @Override
    public void write() {
        setToSession(Attribute.VOUCHER.getAttribute(), voucher);
        setToBoth(Attribute.CLIENTS.getAttribute(), clients);
        setToRequest(Attribute.MONEYS.getAttribute(), moneys);
    }

    @Override
    public String returnPage() {
        return page;
    }


    private int countPrice(int price, int clientNumber) {
        return price * clientNumber;

    }

    private Date readBirthday(int i) {
        Date birthday;
        String birthdayStr = readFromRequest(Parameter.BIRTHDAY.getParameter() + i);
        if (!birthdayStr.isEmpty()) {
            birthday = Date.valueOf(birthdayStr);
        } else {
            birthday = null;
        }
        return birthday;
    }

    private int readTourId(int i) {
        int tourId = Num.MINUS_ONE.getNum();
        String tourIdStr = readFromRequest(Parameter.DOCUMENT_NUM.getParameter() + i);
        if (!isNull(tourIdStr)) {
            tourId = Integer.parseInt(tourIdStr);
        }
        return tourId;
    }

    private List<Client> buildClients(List<String> fullNames,
                                      List<Date> birthdays,
                                      List<String> papers,
                                      List<Integer> documentNums,
                                      List<String> phones,
                                      List<String> emails) {

        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < adultNumber; i++) {
            Client client = new Client();
            client.setFullName(fullNames.get(i));
            client.setBirthday(birthdays.get(i));
            client.setPaper(papers.get(i));
            client.setDocumentNum(documentNums.get(i));
            client.setPhone(phones.get(i));
            client.setEmail(emails.get(i));
            clients.add(client);
        }
        return clients;
    }


}