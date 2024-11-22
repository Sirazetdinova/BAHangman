package backend.academy.messagecenter.exception;

public class UnableGetMessageCenterException extends RuntimeException {
    public UnableGetMessageCenterException(RuntimeException e) {
        super(e);
    }
}
