package backend.academy.exception;

public class EmptyWordListException extends RuntimeException {
    private static final String MESSAGE = "Word list is empty or not contains strings with words";

    public EmptyWordListException() {
        super(MESSAGE);
    }
}
