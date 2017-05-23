package servlet;

import command.ActionCommand;
import command.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by 20_ok on 18.03.2017.
 */
public class Controller extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestHandler(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestHandler(req, resp);

    }

    private void requestHandler(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {

        ActionFactory factory = new ActionFactory();
        ActionCommand command = factory.getCommand(rq);

        String page = command.execute(rq);

        rq.getRequestDispatcher(page).forward(rq, rp);
    }
}
