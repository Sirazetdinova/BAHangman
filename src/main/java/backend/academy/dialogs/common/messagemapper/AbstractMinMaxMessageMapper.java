package backend.academy.dialogs.common.messagemapper;

import backend.academy.dialogs.common.exception.MoreCharactersInputException;
import backend.academy.dialogs.dialogcenter.DialogCenter;
import backend.academy.dialogs.minmaxdialog.exception.NotDigitException;
import backend.academy.dialogs.minmaxdialog.exception.NumberOutOfRangeException;

public abstract class AbstractMinMaxMessageMapper extends AbstractMessageMapper implements MessageMapper {
    public AbstractMinMaxMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    public String apply(RuntimeException e) {
        try {
            throw e;
        } catch (MoreCharactersInputException exc) {
            return messageMoreCharactersInputException();
        } catch (NotDigitException exc) {
            return messageNotDigitException();
        } catch (NumberOutOfRangeException exc) {
            return messageNumberOutOfRangeException();
        } catch (RuntimeException exc) {
            throw new IllegalArgumentException("Illegal exception: " + exc);
        }
    }

    protected abstract String messageMoreCharactersInputException();

    protected abstract String messageNotDigitException();

    protected abstract String messageNumberOutOfRangeException();
}
