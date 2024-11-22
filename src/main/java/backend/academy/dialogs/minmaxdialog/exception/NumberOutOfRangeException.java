package backend.academy.dialogs.minmaxdialog.exception;

public class NumberOutOfRangeException extends NumberValidationException {
    private static final String MESSAGE = "The number '%d' is out of range.";

    public NumberOutOfRangeException(int invalidNumber) {
        super(String.format(MESSAGE, invalidNumber), invalidNumber);
    }
}
