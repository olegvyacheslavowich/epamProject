package executor.impl.flight.impl;

import constant.Attribute;
import constant.Page;
import entity.*;
import exception.*;
import executor.impl.flight.FlightExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class BuildFlightExecutor extends FlightExecutor {


    private FlightExecutor next;

    public BuildFlightExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(List<String> fullNames, List<String> birthdays,
                          List<String> papers, List<String> documentNums,
                          List<String> phones, List<String> emails, int adultNumber)
            throws EmptyFieldException, NoCreditCardsException, NoFlightsException,
            NoToursException, IncorrectDataException {

        String page = Page.PAYMENT.getPage();

        Tour tour = (Tour) readFromSession(Attribute.TOUR.getAttribute());
        User user = (User) readFromSession(Attribute.USER.getAttribute());
        Flight flightTo = (Flight) readFromSession(Attribute.FLIGHT_TO.getAttribute());
        Flight flightFrom = (Flight) readFromSession(Attribute.FLIGHT_FROM.getAttribute());
        int childNumber = Integer.parseInt((String) readFromSession(Attribute.CHILDREN_NUMBER.getAttribute()));

        Account account = user.getAccount();
        int price = countPrice(tour.getPrice(), adultNumber);
        Voucher voucher = new Voucher(tour, flightTo, flightFrom, adultNumber + childNumber, price);
        List<Client> clients = buildClients(fullNames, birthdays, papers, documentNums, phones, emails, adultNumber);
        List<Money> moneys = getMoneyDBService().readByLogin(account);

        setToSession(Attribute.VOUCHER.getAttribute(), voucher);
        setToSession(Attribute.CLIENTS.getAttribute(), clients);
        setToSession(Attribute.MONEYS.getAttribute(), moneys);

        if (next != null) {
            page = next.execute(fullNames, birthdays, papers, documentNums, phones, emails, adultNumber);
        }

        return page;
    }

    public void setNext(FlightExecutor next) {
        this.next = next;
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
                                      int adultNumber) {

        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < adultNumber; i++) {
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
