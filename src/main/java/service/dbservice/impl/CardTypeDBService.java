package service.dbservice.impl;

import daolayer.impl.CardTypeDao;
import entity.CardType;
import entity.CreditCard;
import service.dbservice.DBService;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 18.05.2017.
 */
public class CardTypeDBService extends DBService {

    CardTypeDao dao = new CardTypeDao();

    public CardTypeDBService(HttpSession session) {
        super(session);
    }

    public CardType readByType(CardType type) {
        return dao.readByType(type);
    }
}
