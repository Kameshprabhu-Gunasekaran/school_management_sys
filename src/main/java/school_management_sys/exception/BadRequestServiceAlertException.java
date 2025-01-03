package school_management_sys.exception;

public class BadRequestServiceAlertException extends RuntimeException {

    public BadRequestServiceAlertException(final String message) {
        super(message);
    }
}
