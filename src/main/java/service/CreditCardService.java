package service;

import constant.Key;
import daolayer.DaoFactory;
import daolayer.impl.CreditCardDao;
import entity.CardType;
import entity.CreditCard;
import exception.ConnectionPoolException;
import exception.DataBaseConnectionException;
import exception.ValidationException;
import validation.Validator;

import java.sql.Date;

public class CreditCardService {

    public CreditCard createNewCard(String typeParam, String ownerNameParam, String numberParam, String cvvParam, String dateParam) throws DataBaseConnectionException, ValidationException {

        if (!Validator.isEngString(typeParam)) {
            throw new ValidationException(Key.EX_INCORRECT_CARD_TYPE);
        } else if (!Validator.isEngString(ownerNameParam)) {
            throw new ValidationException(Key.EX_INCORRECT_OWNER_NAME);
        } else if (!Validator.isNumber(numberParam)) {
            throw new ValidationException(Key.EX_INCORRECT_CARD_NUMBER);
        } else if (!Validator.isCvvNumber(cvvParam)) {
            throw new ValidationException(Key.EX_INCORRECT_CVV);
        } else if (!Validator.isDate(dateParam)) {
            throw new ValidationException(Key.EX_INCORRECT_DATE);
        }
        int number = Integer.parseInt(numberParam);
        int cvv = Integer.parseInt(cvvParam);
        Date date = Date.valueOf(dateParam);

        CardTypeService cardTypeService = new CardTypeService();
        CreditCardDao creditCardDao;
        try (DaoFactory daoFactory = new DaoFactory()) {
            creditCardDao = daoFactory.getCreditCardDao();
        } catch (ConnectionPoolException e) {
            throw new DataBaseConnectionException(e);
        }

        CardType cardType = new CardType(typeParam);
        cardType = cardTypeService.readByType(cardType);
        CreditCard creditCard = new CreditCard(cardType, ownerNameParam, number, cvv, date);
        creditCard.setId(creditCardDao.create(creditCard));

        return creditCard;
    }


}
