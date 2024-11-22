package backend.academy.dialogs.difficultdialog;

import backend.academy.dialogs.common.messagemapper.AbstractOptionMessageMapper;
import backend.academy.dialogs.dialogcenter.DialogCenter;

public class DifficultMessageMapper extends AbstractOptionMessageMapper {
    public DifficultMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    protected String messageInputDoesNotMatchWithOptions() {
        return dialogCenter.get(MessageKey.INVALID_DIFFICULT.section, MessageKey.INVALID_DIFFICULT.key);
    }

    private enum MessageKey {
        INVALID_DIFFICULT("invalid_difficult");

        public final String section = "DifficultMessageMapper";
        public final String key;

        MessageKey(String key) {
            this.key = key;
        }
    }
}
