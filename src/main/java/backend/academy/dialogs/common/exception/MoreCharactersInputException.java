package backend.academy.dialogs.common.exception;

public class MoreCharactersInputException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Ошибка ввода!";

    public MoreCharactersInputException() {
        super(ERROR_MESSAGE);
    }
}
