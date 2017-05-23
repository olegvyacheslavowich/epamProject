package executor.impl.flight.impl;

import constant.Attribute;
import constant.Key;
import constant.Parameter;
import exception.*;
import executor.impl.flight.FlightExecutor;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ReadFlightExecutor extends FlightExecutor {


    private FlightExecutor next;


    public ReadFlightExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(List<String> fullNames, List<String> birthdays,
                          List<String> papers, List<String> documentNums,
                          List<String> phones, List<String> emails, int adultNumber) throws EmptyFieldException, NoCreditCardsException, NoFlightsException, NoToursException, IncorrectDataException {

        String page = null;

        fullNames = new ArrayList<>();
        birthdays = new ArrayList<>();
        papers = new ArrayList<>();
        documentNums = new ArrayList<>();
        phones = new ArrayList<>();
        emails = new ArrayList<>();

        adultNumber = Integer.parseInt((String) readFromSession(Attribute.ADULT_NUMBER.getAttribute()));

        for (int i = 1; i <= adultNumber; i++) {
            if (!readFromRequest(Parameter.FULL_NAME.getParameter() + i).isEmpty()) {
                fullNames.add(readFromRequest(Parameter.FULL_NAME.getParameter() + i));
            } else {
                throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_FULL_NAME.getKey(), getCurrentLanguage()));
            }
            if (!readFromRequest(Parameter.BIRTHDAY.getParameter() + i).isEmpty()) {
                birthdays.add(readFromRequest(Parameter.BIRTHDAY.getParameter() + i));
            } else {
                throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_BIRTHDAY.getKey(), getCurrentLanguage()));
            }
            if (!readFromRequest(Parameter.PAPER.getParameter() + i).isEmpty()) {
                papers.add(readFromRequest(Parameter.PAPER.getParameter() + i));
            } else {
                throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_PAPER.getKey(), getCurrentLanguage()));
            }
            if (!readFromRequest(Parameter.DOCUMENT_NUM.getParameter() + i).isEmpty()) {
                documentNums.add(readFromRequest(Parameter.DOCUMENT_NUM.getParameter() + i));
            } else {
                throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_PAPER_NUM.getKey(), getCurrentLanguage()));
            }
            if (!readFromRequest(Parameter.PHONE.getParameter() + i).isEmpty()) {
                phones.add(readFromRequest(Parameter.PHONE.getParameter() + i));
            } else {
                throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_PHONE.getKey(), getCurrentLanguage()));
            }
            if (!readFromRequest(Parameter.EMAIL.getParameter() + i).isEmpty()) {
                emails.add(readFromRequest(Parameter.EMAIL.getParameter() + i));
            } else {
                throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_EMAIL.getKey(), getCurrentLanguage()));
            }
        }

        if (next != null) {
            page = next.execute(fullNames, birthdays, papers, documentNums, phones, emails, adultNumber);
        }

        return page;
    }


    public void setNext(FlightExecutor next) {
        this.next = next;
    }
}
