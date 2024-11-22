package backend.academy.exception;

public class NoSuchLetterException extends RuntimeException {
    private static final String MESSAGE = "The letter %s is not in the hidden word";

    private final String wrongLetter;

    public NoSuchLetterException(String wrongLetter) {
        super(String.format(MESSAGE, wrongLetter));
        this.wrongLetter = wrongLetter;
    }

    public String getWrongLetter() {
        return wrongLetter;
    }
}
