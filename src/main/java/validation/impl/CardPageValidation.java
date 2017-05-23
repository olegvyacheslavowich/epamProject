package validation.impl;

import constant.Key;
import constant.Regex;
import exception.*;
import service.ResourceManager;
import validation.Validation;

import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class CardPageValidation extends Validation {

    private String ownerName;
    private String number;
    private String cvv;
    private String date;
    private String typeName;

    public CardPageValidation(HttpSession session, String ownerName, String number, String cvv, String date, String typeName) {
        super(session);
        this.ownerName = ownerName;
        this.number = number;
        this.cvv = cvv;
        this.date = date;
        this.typeName = typeName;
    }

    @Override
    public void isValid() throws EmptyFieldException, IncorrectDataException, NoFlightsException, NoCreditCardsException, NoToursException {
        if (!ownerName.matches(Regex.ENG_LETTERS.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_OWNER_NAME.getKey(), getCurrentLanguage()));
        }
        if (!number.matches(Regex.CARD_NUMBER.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_CARD_NUMBER.getKey(), getCurrentLanguage()));

        }
        if (!cvv.matches(Regex.CVV_NUMBER.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_CVV.getKey(), getCurrentLanguage()));

        }
        if (!date.matches(Regex.DATE.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_DATE.getKey(), getCurrentLanguage()));

        }
        if (!typeName.matches(Regex.ENG_LETTERS.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_CARD_TYPE.getKey(), getCurrentLanguage()));

        }
    }
}
