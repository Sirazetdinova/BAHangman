package backend.academy.dialogs.letterdialog.en;

import backend.academy.dialogs.common.validator.AbstractLetterValidator;
import java.lang.Character.UnicodeBlock;

public class EnLetterValidator extends AbstractLetterValidator {
    public EnLetterValidator() {
        super(UnicodeBlock.BASIC_LATIN);
    }
}
