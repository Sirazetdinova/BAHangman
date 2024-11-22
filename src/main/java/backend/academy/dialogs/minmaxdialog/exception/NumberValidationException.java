package backend.academy.dialogs.minmaxdialog.exception;

public class NumberValidationException extends RuntimeException {
    private final int invalidNumber;

    public NumberValidationException(String message, int invalidNumber) {
        super(message);
        this.invalidNumber = invalidNumber;
    }

    public int getInvalidNumber() {
        return invalidNumber;
    }
}
