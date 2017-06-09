package command.impl;

import command.ActionCommand;
import constant.Attribute;
import constant.Language;
import constant.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 20_ok on 20.03.2017.
 */
public class TranslatorCommand implements ActionCommand {

    private static final Logger logger = LoggerFactory.getLogger(TranslatorCommand.class);

    private static final String RUSSIAN_LANGUAGE = " ";
    private static final String ENGLISH_LANGUAGE = "  ";

    @Override
    public String execute(HttpServletRequest rq) {

        HttpSession session = rq.getSession();

        String page = rq.getParameter(Parameter.TRANSLATOR);
        String lang;
        if (!Validator.isEmpty(rq.getParameter(Parameter.LANGUAGE))) {
            lang = rq.getParameter(Parameter.LANGUAGE);
        } else {
            lang = RUSSIAN_LANGUAGE;
        }
        switch (lang) {
            case RUSSIAN_LANGUAGE:
                session.setAttribute(Attribute.LOCALE, Language.RU_RU);
                break;
            case ENGLISH_LANGUAGE:
                session.setAttribute(Attribute.LOCALE, Language.EN_US);
                break;
        }

        return page;
    }
}
