package service.dbservice;

import daolayer.impl.CreditCardDao;
import entity.CreditCard;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 18.05.2017.
 */
public class CreditCardDBService extends DBService {

    private CreditCardDao dao = new CreditCardDao();

    public CreditCardDBService(HttpSession session) {
        super(session);
    }

    public int create(CreditCard creditCard) {
        return dao.create(creditCard);
    }


}
