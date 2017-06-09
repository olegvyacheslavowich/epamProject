package exception;

/**
 * Created by 20_ok on 6/7/2017.
 */
public class ServiceException extends Exception{

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }


}
