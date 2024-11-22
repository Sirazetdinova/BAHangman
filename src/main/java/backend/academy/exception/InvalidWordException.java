package backend.academy.exception;

public class InvalidWordException extends RuntimeException {
    private static final String MESSAGE =
        "'%s' is not a word because it contains invalid characters or characters from a different language";

    private final String invalidWord;

    public InvalidWordException(String invalidWord) {
        super(String.format(MESSAGE, invalidWord));
        this.invalidWord = invalidWord;
    }

    public String getInvalidWord() {
        return invalidWord;
    }
}
