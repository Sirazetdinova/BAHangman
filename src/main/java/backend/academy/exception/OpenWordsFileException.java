package backend.academy.exception;

public class OpenWordsFileException extends RuntimeException {
    private static final String MESSAGE = "An error occurred when opening a file with a list of words";

    public OpenWordsFileException() {
        super(MESSAGE);
    }
}
