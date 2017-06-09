package command;

import com.sun.istack.internal.logging.Logger;
import dbconnection.ConnectionPool;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 20_ok on 20.03.2017.
 */

/**
 * Интерейс потомок для всех классов Command.
 */
public interface ActionCommand {

    /**
     * Метод возвращает страницу в зависимости от запроса, присланного пользователем
     *
     * @param rq запрос
     * @return страница для перехода
     */
    String execute(HttpServletRequest rq);
}
