package backend.academy.messagecenter.exception;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Throwable cause) {
        super(cause);
    }
}
