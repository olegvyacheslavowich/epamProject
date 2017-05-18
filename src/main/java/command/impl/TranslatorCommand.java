package command.impl;

import command.ActionCommand;
import constant.Language;
import constant.Parameter;
import service.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by 20_ok on 20.03.2017.
 */
public class TranslatorCommand implements ActionCommand {

    private static final String ENCODING_ISO = "ISO-8859-1";
    private static final String ENCODING_UTF_8 = "UTF-8";
    private static final String RUSSIAN_LANGUAGE = " ";
    private static final String ENGLISH_LANGUAGE = "  ";

    @Override
    public String execute(HttpServletRequest rq) {

        String page = rq.getParameter(Parameter.TRANSLATOR.getParameter());
        try {
            String lang = new String(rq.getParameter(Parameter.LANGUAGE.getParameter()).getBytes(ENCODING_ISO), ENCODING_UTF_8);
            switch (lang) {
                case RUSSIAN_LANGUAGE:
                    rq.getSession().setAttribute(Parameter.LOCALE.getParameter(), Language.RU_RU.getLanguage());
                    break;
                case ENGLISH_LANGUAGE:
                    rq.getSession().setAttribute(Parameter.LOCALE.getParameter(), Language.EN_US.getLanguage());
                    break;
            }
        } catch (UnsupportedEncodingException ignored) {
        }

        return page;
    }
}
