package validation.impl;

import constant.Key;
import constant.Regex;
import exception.EmptyFieldException;
import exception.IncorrectDataException;
import exception.NoCreditCardsException;
import service.ResourceManager;
import validation.Validation;

import javax.servlet.http.HttpSession;
import java.util.List;


public class FlightPageValidation extends Validation {

    private List<String> fullNames;
    private List<String> birthdays;
    private List<String> documentNums;
    private List<String> phones;
    private List<String> emails;

    public FlightPageValidation(HttpSession session, List<String> fullNames, List<String> birthdays, List<String> documentNums, List<String> phones, List<String> emails) {
        super(session);
        this.fullNames = fullNames;
        this.birthdays = birthdays;
        this.documentNums = documentNums;
        this.phones = phones;
        this.emails = emails;
    }

    @Override
    public void isValid() throws EmptyFieldException, IncorrectDataException, NoCreditCardsException {

        for (String fullName : fullNames) {
            if (!fullName.matches(Regex.RUS_AND_ENG_LETTERS.getRegex())) {
                throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_FULL_NAME.getKey(), getCurrentLanguage()));
            }
        }
        for (String date : birthdays) {
            if (!String.valueOf(date).matches(Regex.DATE.getRegex())) {
                throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_BIRTHDAY.getKey(), getCurrentLanguage()));
            }
        }

        for (String documentNum : documentNums) {
            if (!String.valueOf(documentNum).matches(Regex.NUMBER.getRegex())) {
                throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_PAPER_NUM.getKey(), getCurrentLanguage()));

            }
        }

        for (String phone : phones) {
            if (!phone.matches(Regex.MOBILE_NUMBER.getRegex())) {
                throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_PHONE.getKey(), getCurrentLanguage()));

            }
        }
        for (String phone : emails) {
            if (!phone.matches(Regex.EMAIL.getRegex())) {
                throw new IncorrectDataException(ResourceManager.getResource(Key.EX_INCORRECT_EMAIL.getKey(), getCurrentLanguage()));

            }

        }
    }
}
