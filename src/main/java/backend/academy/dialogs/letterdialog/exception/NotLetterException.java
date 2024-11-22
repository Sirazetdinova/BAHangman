package backend.academy.dialogs.letterdialog.exception;

public class NotLetterException extends RuntimeException {
    private static final String MESSAGE = "This character is not letter";

    public NotLetterException() {
        super(MESSAGE);
    }
}
