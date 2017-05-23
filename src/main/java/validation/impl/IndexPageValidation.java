package validation.impl;

import constant.Key;
import constant.Regex;
import exception.EmptyFieldException;
import exception.IncorrectDataException;
import service.ResourceManager;
import validation.Validation;

import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Created by 20_ok on 11.05.2017.
 */
public class IndexPageValidation extends Validation {

    private String departureCity;
    private String date;


    public IndexPageValidation(HttpSession session, String departureCity, String date) {
        super(session);
        this.departureCity = departureCity;
        this.date = date;
    }

    @Override
    public void isValid() throws EmptyFieldException, IncorrectDataException {

        if (departureCity.isEmpty()) {
            throw new EmptyFieldException(ResourceManager.getResource(Key.EX_ENTER_CITY.getKey(), getCurrentLanguage()));
        } else if (!departureCity.matches(Regex.RUS_LETTERS.getRegex())) {
            throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_CITY.getKey(), getCurrentLanguage()));
        }
        if (!date.isEmpty()) {
            if (!date.matches(Regex.DATE.getRegex())) {
                throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_DATE.getKey(), getCurrentLanguage()));
            }
        }
    }
}
