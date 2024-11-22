package backend.academy.dialogs.common.messagemapper;

import backend.academy.dialogs.dialogcenter.DialogCenter;
import backend.academy.dialogs.optiondialog.exception.InputDoesNotMatchWithOptionsException;

public abstract class AbstractOptionMessageMapper extends AbstractMessageMapper implements MessageMapper {
    public AbstractOptionMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    public String apply(RuntimeException e) {
        try {
            throw e;
        } catch (InputDoesNotMatchWithOptionsException exc) {
            return messageInputDoesNotMatchWithOptions();
        } catch (RuntimeException exc) {
            throw new IllegalArgumentException("Illegal exception: " + exc);
        }
    }

    protected abstract String messageInputDoesNotMatchWithOptions();
}
