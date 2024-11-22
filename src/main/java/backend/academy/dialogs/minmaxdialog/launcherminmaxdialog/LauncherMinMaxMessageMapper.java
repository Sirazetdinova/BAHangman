package backend.academy.dialogs.minmaxdialog.launcherminmaxdialog;

import backend.academy.dialogs.common.messagemapper.AbstractMinMaxMessageMapper;
import backend.academy.dialogs.dialogcenter.DialogCenter;

public class LauncherMinMaxMessageMapper extends AbstractMinMaxMessageMapper {
    public LauncherMinMaxMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    protected String messageMoreCharactersInputException() {
        return dialogCenter.get(MessageKey.ALLOWED_ONLY_ONE_CHARACTER.section,
            MessageKey.ALLOWED_ONLY_ONE_CHARACTER.key);
    }

    @Override
    protected String messageNotDigitException() {
        return dialogCenter.get(MessageKey.ALLOWED_ONLY_DIGIT.section, MessageKey.ALLOWED_ONLY_DIGIT.key);
    }

    @Override
    protected String messageNumberOutOfRangeException() {
        return dialogCenter.get(MessageKey.ALLOWED_ONLY_MENU_NUMBER.section, MessageKey.ALLOWED_ONLY_MENU_NUMBER.key);
    }

    private enum MessageKey {
        ALLOWED_ONLY_ONE_CHARACTER("allowed_only_one_character"),
        ALLOWED_ONLY_DIGIT("allowed_only_digit"),
        ALLOWED_ONLY_MENU_NUMBER("allowed_only_menu_number");

        public final String section = "LauncherMinMaxMessageMapper";
        public final String key;

        MessageKey(String key) {
            this.key = key;
        }
    }
}
