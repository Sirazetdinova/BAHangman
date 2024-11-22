package backend.academy.samples.dialogsTest.validator;

import backend.academy.dialogs.letterdialog.ru.RuLetterValidator;
import backend.academy.dialogs.letterdialog.exception.NotLetterInLanguageException;
import backend.academy.dialogs.common.exception.MoreCharactersInputException;
import backend.academy.dialogs.letterdialog.exception.NotLetterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractLetterValidatorTest {
    @Test
    void validate_ShouldThrowNotLetterInLanguageException_WhenInputIsNotInLanguage() {
        RuLetterValidator validator = new RuLetterValidator();

        assertThrows(NotLetterInLanguageException.class, () -> {
            validator.validate("G");
        });
    }

    @Test
    void validate_ShouldNotThrowException_WhenInputIsInLanguage() {
        RuLetterValidator validator = new RuLetterValidator();

        validator.validate("я");
    }

    @Test
    void validate_ShouldThrowMoreCharactersInputException_WhenInputMoreThanOneCharacter() {
        RuLetterValidator validator = new RuLetterValidator();

        assertThrows(MoreCharactersInputException.class, () -> {
            validator.validate("аб");
        });
    }

    @Test
    void validate_ShouldThrowNotLetterException_WhenInputIsNotALetter() {
        RuLetterValidator validator = new RuLetterValidator();

        assertThrows(NotLetterException.class, () -> {
            validator.validate("1");
        });
    }
}
