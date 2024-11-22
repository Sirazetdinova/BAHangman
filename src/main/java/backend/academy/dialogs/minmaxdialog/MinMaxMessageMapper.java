package backend.academy.dialogs.minmaxdialog;

import backend.academy.dialogs.common.messagemapper.AbstractMinMaxMessageMapper;
import backend.academy.dialogs.dialogcenter.DialogCenter;

public class MinMaxMessageMapper extends AbstractMinMaxMessageMapper {
    public MinMaxMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    protected String messageMoreCharactersInputException() {
        return dialogCenter.get(MessageKey.MORE_CHARACTERS.section, MessageKey.MORE_CHARACTERS.key);
    }

    @Override
    protected String messageNotDigitException() {
        return dialogCenter.get(MessageKey.NOT_DIGIT.section, MessageKey.NOT_DIGIT.key);
    }

    @Override
    protected String messageNumberOutOfRangeException() {
        return dialogCenter.get(MessageKey.NUMBER_OUT_OF_RANGE.section, MessageKey.NUMBER_OUT_OF_RANGE.key);
    }

    private enum MessageKey {
        MORE_CHARACTERS("more_characters"),
        NOT_DIGIT("not_digit"),
        NUMBER_OUT_OF_RANGE("number_out_of_range");

        public final String section = "MinMaxMessageMapper";
        public final String key;

        MessageKey(String key) {
            this.key = key;
        }
    }
}
