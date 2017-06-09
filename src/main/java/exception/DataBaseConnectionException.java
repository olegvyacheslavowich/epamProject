package exception;

/**
 * Created by 20_ok on 6/9/2017.
 */
public class DataBaseConnectionException extends Exception {

    public DataBaseConnectionException(String message) {
        super(message);
    }

    public DataBaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseConnectionException(Throwable cause) {
        super(cause);
    }
}
