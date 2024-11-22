package backend.academy.dialogs.common.messagemapper;

import backend.academy.dialogs.dialogcenter.DialogCenter;

public abstract class AbstractMessageMapper {
    protected final DialogCenter dialogCenter;

    public AbstractMessageMapper(DialogCenter dialogCenter) {
        this.dialogCenter = dialogCenter;
    }
}
