package executor;

import constant.Attribute;
import constant.Num;
import entity.HotTour;
import entity.Tour;
import service.dbservice.impl.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    private AdminDBService adminDBService;
    private HotTourDBService hotTourDBService;

    public PageExecutor(HttpServletRequest rq, HttpSession session) {
        this.rq = rq;
        this.session = session;
        accountDBService = new AccountDBService(session);
        userDBService = new UserDBService(session);
        moneyDBService = new MoneyDBService();
        orderDBService = new OrderDBService();
        flightDBService = new FlightDBService(session);
        tourDBService = new TourDBService();
        clientDBService = new ClientDBService();
        voucherDBService = new VoucherDBService(session);
        creditCardDBService = new CreditCardDBService(session);
        cardTypeDBService = new CardTypeDBService(session);
        adminDBService = new AdminDBService(session);
        hotTourDBService = new HotTourDBService(session);
    }

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

    protected void removeFromSession(String attribute) {
        session.removeAttribute(attribute);
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

    protected boolean isNull(Object object) {
        return object == null;
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

    protected AdminDBService getAdminDBService() {
        return adminDBService;
    }

    protected HotTourDBService getHotTourDBService() {
        return hotTourDBService;
    }

    protected void searchHotTours(List<Tour> tours, List<HotTour> hotTours) {
        for (Tour tour : tours) {
            for (HotTour hotTour : hotTours) {
                if (tour.getId() == hotTour.getTour().getId()) {
                    tour.setId(Num.MINUS_ONE.getNum());
                }
            }
        }
    }

    protected String getCurrentLanguage() {
        return (String) session.getAttribute(Attribute.LOCALE.getAttribute());
    }

    public HttpSession getSession() {
        return session;
    }
}
