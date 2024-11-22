package backend.academy.dialogs.letterdialog;

import backend.academy.dialogs.common.exception.MoreCharactersInputException;
import backend.academy.dialogs.common.messagemapper.AbstractMessageMapper;
import backend.academy.dialogs.common.messagemapper.MessageMapper;
import backend.academy.dialogs.dialogcenter.DialogCenter;
import backend.academy.dialogs.letterdialog.exception.NotLetterException;
import backend.academy.dialogs.letterdialog.exception.NotLetterInLanguageException;

public class LetterMessageMapper extends AbstractMessageMapper implements MessageMapper {
    public LetterMessageMapper(DialogCenter dialogCenter) {
        super(dialogCenter);
    }

    @Override
    public String apply(RuntimeException e) {
        try {
            throw e;
        } catch (NotLetterException exc) {
            return dialogCenter.get(MessageKey.ALLOWED_ONLY_LETTERS.section, MessageKey.ALLOWED_ONLY_LETTERS.key);
        } catch (NotLetterInLanguageException exc) {
            return dialogCenter.get(MessageKey.LETTER_NOT_IN_LANGUAGE.section, MessageKey.LETTER_NOT_IN_LANGUAGE.key);
        } catch (MoreCharactersInputException exc) {
            return dialogCenter.get(MessageKey.MORE_LETTERS.section, MessageKey.MORE_LETTERS.key);
        } catch (RuntimeException exc) {
            throw new IllegalArgumentException("Illegal exception: " + exc);
        }
    }

    private enum MessageKey {
        ALLOWED_ONLY_LETTERS("allowed_only_letters"),
        LETTER_NOT_IN_LANGUAGE("letter_not_in_language"),
        MORE_LETTERS("more_letters");

        public final String section = "LetterMessageMapper";
        public final String key;

        MessageKey(String key) {
            this.key = key;
        }
    }
}
