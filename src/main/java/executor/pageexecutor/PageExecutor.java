package executor.pageexecutor;

import service.dbservice.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Created by 20_ok on 11.05.2017.
 */
public abstract class PageExecutor {

    private HttpServletRequest rq;
    private HttpSession session;
    private TourDBService tourDBService;
    private FlightDBService flightDBService;
    private OrderDBService orderDBService;
    private MoneyDBService moneyDBService;
    private UserDBService userDBService;
    private AccountDBService accountDBService;
    private ClientDBService clientDBService;
    private VoucherDBService voucherDBService;
    private CreditCardDBService creditCardDBService;
    private CardTypeDBService cardTypeDBService;

    public PageExecutor(HttpServletRequest rq, HttpSession session) {
        this.rq = rq;
        this.session = session;
        accountDBService = new AccountDBService(session);
        userDBService = new UserDBService(session);
        moneyDBService = new MoneyDBService();
        orderDBService = new OrderDBService();
        flightDBService = new FlightDBService();
        tourDBService = new TourDBService();
        clientDBService = new ClientDBService();
        voucherDBService = new VoucherDBService(session);
        creditCardDBService = new CreditCardDBService(session);
        cardTypeDBService = new CardTypeDBService(session);
    }

    /**
     * метод считывает со страницы все параметры и атрибуты
     */
    public abstract void read();

    /**
     * метод производит валидацию принятых данных
     */
    public abstract void validate();

    /**
     * метод собирает сущности из принятых данных
     */
    public abstract void build();

    /**
     * метод пишет в сессию необходимые данные
     */
    public abstract void write();


    public abstract String returnPage();

    /**
     * метод читает из сессии текущий язык локализации
     *
     * @return язык ru или en
     */
    protected void setToRequest(String attribute, Object object) {
        rq.setAttribute(attribute, object);
    }

    protected void setToSession(String attribute, Object object) {
        session.setAttribute(attribute, object);
    }

    protected void setToBoth(String attribute, Object object) {
        rq.setAttribute(attribute, object);
        session.setAttribute(attribute, object);
    }

    protected String readFromRequest(String parameter) {
        return rq.getParameter(parameter);
    }

    protected Object readFromSession(String attribute) {
        return session.getAttribute(attribute);
    }

    /**
     * Метод проверят заполнено ли поле Дата на странице index.jsp
     *
     * @return true если запонено, false если не заполнено
     */

    protected boolean isNull(Date date) {
        return date == null;
    }

    protected boolean isNull(String string) {
        return string == null;
    }

    protected boolean isNull(int i) {
        return i == 0;
    }

    protected TourDBService getTourDBService() {
        return tourDBService;
    }

    protected FlightDBService getFlightDBService() {
        return flightDBService;
    }

    protected OrderDBService getOrderDBService() {
        return orderDBService;
    }

    protected MoneyDBService getMoneyDBService() {
        return moneyDBService;
    }

    protected AccountDBService getAccountDBService() {
        return accountDBService;
    }

    protected UserDBService getUserDBService() {
        return userDBService;
    }

    protected ClientDBService getClientDBService() {
        return clientDBService;
    }

    protected VoucherDBService getVoucherDBService() {
        return voucherDBService;
    }

    protected CreditCardDBService getCreditCardDBService() {
        return creditCardDBService;
    }

    protected CardTypeDBService getCardTypeDBService() {
        return cardTypeDBService;
    }
}
