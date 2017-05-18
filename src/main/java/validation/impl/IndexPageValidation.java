package validation.impl;

import constant.Regex;
import exception.EmptyFieldException;
import exception.IncorrectDataException;
import validation.Validation;

import java.sql.Date;

/**
 * Created by 20_ok on 11.05.2017.
 */
public class IndexPageValidation implements Validation {

    private String departureCity;

    public IndexPageValidation(String departureCity) {
        this.departureCity = departureCity;
    }

    @Override
    public void isValid() throws EmptyFieldException, IncorrectDataException {

        if (departureCity.isEmpty()) {
            throw new EmptyFieldException("Введите город вылета");
        } else if (!departureCity.matches(Regex.RUS_LETTERS.getRegex())) {
            throw new IncorrectDataException("Введены некорректные данные");
        }
    }
}
