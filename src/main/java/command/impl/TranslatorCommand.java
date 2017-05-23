package command.impl;

import command.ActionCommand;
import constant.Language;
import constant.Parameter;
import executor.impl.translator.TranslatorExecutor;
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

    @Override
    public String execute(HttpServletRequest rq) {

        HttpSession session = rq.getSession();

        TranslatorExecutor executor = new TranslatorExecutor(rq, session);

        return executor.returnPage();
    }
}
