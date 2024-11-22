package backend.academy.util.inireader.exception;

public class IniValueNotFoundException extends RuntimeException {
    private static final String MESSAGE = "The value was not found by key '%s' in section '%s'.";

    private final String section;
    private final String key;

    public IniValueNotFoundException(String section, String key) {
        super(MESSAGE.formatted(key, section));
        this.section = section;
        this.key = key;
    }

    public String getSection() {
        return section;
    }

    public String getKey() {
        return key;
    }
}
