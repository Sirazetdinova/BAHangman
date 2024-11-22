package backend.academy.dialogs.minmaxdialog.exception;

public class NotDigitException extends RuntimeException {
    private static final String MESSAGE = "Allowed only digits in input";

    public NotDigitException() {
        super(MESSAGE);
    }

    public NotDigitException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
