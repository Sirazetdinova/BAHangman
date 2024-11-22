package backend.academy.dialogs.letterdialog.ru;

import backend.academy.dialogs.common.validator.AbstractLetterValidator;
import java.lang.Character.UnicodeBlock;

public class RuLetterValidator extends AbstractLetterValidator {
    public RuLetterValidator() {
        super(UnicodeBlock.CYRILLIC);
    }
}
