package executor.impl.card.impl;

import constant.Attribute;
import constant.Page;
import entity.*;
import exception.*;
import executor.impl.card.CardExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

public class BuildCardExecutor extends CardExecutor {

    private CardExecutor next;

    public BuildCardExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    @Override
    public String execute(String ownerName, String number, String cvv, String date, String typeName) throws EmptyFieldException, NoCreditCardsException, NoFlightsException, NoToursException, IncorrectDataException {

        String page = Page.USER.getPage();

        User user = (User) readFromSession(Attribute.USER.getAttribute());
        Account account = user.getAccount();
        CardType cardType = new CardType();
        cardType.setName(typeName);
        cardType = getCardTypeDBService().readByType(cardType);
        CreditCard creditCard = new CreditCard(cardType, ownerName,
                Long.parseLong(number), Integer.parseInt(cvv), Date.valueOf(date));
        creditCard.setId(getCreditCardDBService().create(creditCard));
        Money money = new Money(account, creditCard);
        getMoneyDBService().create(money);
        List<Money> moneys = getMoneyDBService().readByLogin(user.getAccount());
        List<Order> orders = getOrderDBService().getAllByAccount(account);


        setToRequest(Attribute.MONEYS.getAttribute(), moneys);
        setToRequest(Attribute.ORDERS.getAttribute(), orders);

        if (next != null) {
            page = next.execute(ownerName, number, cvv, date, typeName);
        }

        return page;
    }

    public void setNext(CardExecutor next) {
        this.next = next;
    }
}
