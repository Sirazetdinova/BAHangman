package backend.academy.dialogs.letterdialog.exception;

public class NotLetterInLanguageException extends RuntimeException {
    private static final String MESSAGE = "Not a letter in language";

    public NotLetterInLanguageException() {
        super(MESSAGE);
    }
}
