package command;

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
