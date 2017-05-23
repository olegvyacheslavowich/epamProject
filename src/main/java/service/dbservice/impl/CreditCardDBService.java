package service.dbservice.impl;

import daolayer.impl.CreditCardDao;
import entity.CreditCard;
import service.dbservice.DBService;

import javax.servlet.http.HttpSession;

public class CreditCardDBService extends DBService {

    private CreditCardDao dao = new CreditCardDao();

    public CreditCardDBService(HttpSession session) {
        super(session);
    }

    public int create(CreditCard creditCard) {
        return dao.create(creditCard);
    }


}
