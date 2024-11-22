package backend.academy.dialogs.categorydialog;

import backend.academy.dialogs.common.messagemapper.AbstractOptionMessageMapper;
import backend.academy.dialogs.dialogcenter.DialogCenter;

public class CategoryMessageMapper extends AbstractOptionMessageMapper {
    public CategoryMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    protected String messageInputDoesNotMatchWithOptions() {
        return dialogCenter.get(MessageKey.INVALID_CATEGORY.section, MessageKey.INVALID_CATEGORY.key);
    }

    private enum MessageKey {
        INVALID_CATEGORY("invalid_category");

        public final String section = "CategoryMessageMapper";
        public final String key;

        MessageKey(String key) {
            this.key = key;
        }
    }
}
