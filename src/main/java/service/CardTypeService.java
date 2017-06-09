package service;

import daolayer.DaoFactory;
import daolayer.impl.CardTypeDao;
import entity.CardType;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;

public class CardTypeService {

    public CardType readByType(CardType type) throws DataBaseConnectionException {

        CardType cardType;
        try (DaoFactory daoFactory = new DaoFactory()) {
            CardTypeDao cardTypeDao = daoFactory.getCardTypeDao();
            cardType = cardTypeDao.readByType(type);
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }
        return cardType;
    }
}
