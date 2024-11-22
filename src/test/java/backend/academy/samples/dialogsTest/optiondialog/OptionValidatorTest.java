package backend.academy.samples.dialogsTest.optiondialog;

import backend.academy.dialogs.optiondialog.OptionValidator;
import backend.academy.dialogs.optiondialog.exception.InputDoesNotMatchWithOptionsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

public class OptionValidatorTest {
    @Test
    void validate_ShouldThrowInputDoesNotMatchWithOptionsException_WhenInputDoesNotMatchAnyOption() {
        OptionValidator validator = new OptionValidator(Arrays.asList("Option1", "Option2", "Option3"));

        assertThrows(InputDoesNotMatchWithOptionsException.class, () -> {
            validator.validate("InvalidOption"); // Не соответствует ни одной опции
        });
    }

    @Test
    void validate_ShouldNotThrowException_WhenInputMatchesOptionIgnoringCase() {
        OptionValidator validator = new OptionValidator(Arrays.asList("Option1", "Option2", "Option3"));

        validator.validate("option1"); // Совпадает с "Option1", игнорируя регистр
    }

    @Test
    void validate_ShouldNotThrowException_WhenInputMatchesExactOption() {
        OptionValidator validator = new OptionValidator(Arrays.asList("Option1", "Option2", "Option3"));

        validator.validate("Option2"); // Совпадает с "Option2"
    }
}
