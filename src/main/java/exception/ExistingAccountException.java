package exception;

/**
 * Created by 20_ok on 22.03.2017.
 */
public class ExistingAccountException extends Exception {


    public ExistingAccountException() {
    }

    public ExistingAccountException(String message) {
        super(message);
    }
}
