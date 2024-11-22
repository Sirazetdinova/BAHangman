package backend.academy.util.inireader.exception;

public class OpenIniFileException extends RuntimeException {
    private static final String MESSAGE = "Ini file '%s' not found";

    public OpenIniFileException(String filePath) {
        super(MESSAGE.formatted(filePath));
    }
}
