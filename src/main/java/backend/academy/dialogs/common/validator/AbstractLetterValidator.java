package backend.academy.dialogs.common.validator;

import backend.academy.dialogs.common.exception.MoreCharactersInputException;
import backend.academy.dialogs.letterdialog.exception.NotLetterException;
import backend.academy.dialogs.letterdialog.exception.NotLetterInLanguageException;
import java.lang.Character.UnicodeBlock;

public abstract class AbstractLetterValidator implements Validator<String> {
    private final UnicodeBlock unicodeBlock;

    protected AbstractLetterValidator(UnicodeBlock unicodeBlock) {
        this.unicodeBlock = unicodeBlock;
    }

    @Override
    public void validate(String playerInput) {
        if (playerInput.length() != 1) {
            throw new MoreCharactersInputException();
        }
        char typedLetter = playerInput.charAt(0);
        if (!isLetter(typedLetter)) {
            throw new NotLetterException();
        }
        if (!isLetterInLanguage(typedLetter)) {
            throw new NotLetterInLanguageException();
        }
    }

    public boolean isLetterInLanguage(char letter) {
        return isLetter(letter) && UnicodeBlock.of(letter).equals(unicodeBlock);
    }

    public boolean isLetter(char letter) {
        return Character.isLetter(letter);
    }
}
