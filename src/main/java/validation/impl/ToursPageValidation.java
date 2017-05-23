package validation.impl;

import constant.Key;
import constant.Regex;
import exception.*;
import service.ResourceManager;
import validation.Validation;

import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 22.05.2017.
 */
public class ToursPageValidation extends Validation {

    private String departureDate;

    public ToursPageValidation(HttpSession session, String departureDate) {
        super(session);
        this.departureDate = departureDate;
    }

    @Override
    public void isValid() throws EmptyFieldException, IncorrectDataException, NoFlightsException,
            NoCreditCardsException, NoToursException {
        if (!departureDate.matches(Regex.DATE.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_DATE.getKey(), getCurrentLanguage()));
        }
    }
}
