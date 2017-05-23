package service.dbservice.impl;

import daolayer.impl.CardTypeDao;
import entity.CardType;
import service.dbservice.DBService;

import javax.servlet.http.HttpSession;

public class CardTypeDBService extends DBService {

    CardTypeDao dao = new CardTypeDao();

    public CardTypeDBService(HttpSession session) {
        super(session);
    }

    public CardType readByType(CardType type) {
        return dao.readByType(type);
    }
}
