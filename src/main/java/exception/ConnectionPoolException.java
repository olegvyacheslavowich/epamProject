package exception;

/**
 * Created by 20_ok on 6/6/2017.
 */
public class ConnectionPoolException extends Exception{

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
