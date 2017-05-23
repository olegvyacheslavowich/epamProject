package executor.impl.translator;

import constant.Language;
import constant.Parameter;
import executor.PageExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

public class TranslatorExecutor extends PageExecutor {

    private static final String RUSSIAN_LANGUAGE = " ";
    private static final String ENGLISH_LANGUAGE = "  ";

    public TranslatorExecutor(HttpServletRequest rq, HttpSession session) {
        super(rq, session);
    }

    public String returnPage() {
        String page = readFromRequest(Parameter.TRANSLATOR.getParameter());
        String lang;
        if (readFromRequest(Parameter.LANGUAGE.getParameter()) != null) {
            lang = readFromRequest(Parameter.LANGUAGE.getParameter());
        } else {
            lang = RUSSIAN_LANGUAGE;
        }
        switch (lang) {
            case RUSSIAN_LANGUAGE:
                setToSession(Parameter.LOCALE.getParameter(), Language.RU_RU.getLanguage());
                break;
            case ENGLISH_LANGUAGE:
                setToSession(Parameter.LOCALE.getParameter(), Language.EN_US.getLanguage());
                break;
        }

        return page;

    }
}
