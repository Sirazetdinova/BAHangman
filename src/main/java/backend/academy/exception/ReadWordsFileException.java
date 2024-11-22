package backend.academy.exception;

public class ReadWordsFileException extends RuntimeException {
    private static final String MESSAGE = "An error occurred when reading a file with a list of words";

    public ReadWordsFileException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
