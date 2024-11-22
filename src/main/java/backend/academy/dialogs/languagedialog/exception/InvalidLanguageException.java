package backend.academy.dialogs.languagedialog.exception;

public class InvalidLanguageException extends RuntimeException {
    private static final String MESSAGE = "'%s' is not a valid language.";

    private final String invalidLanguage;

    public InvalidLanguageException(String invalidLanguage) {
        super(MESSAGE.formatted(invalidLanguage));
        this.invalidLanguage = invalidLanguage;
    }

    public String getInvalidLanguage() {
        return invalidLanguage;
    }
}
