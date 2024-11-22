package backend.academy.samples.validatorTest;

import backend.academy.constant.Language;
import backend.academy.exception.EmptyWordListException;
import backend.academy.exception.InvalidWordException;
import backend.academy.validator.WordRepositoryValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordRepositoryValidatorTest {
    private final WordRepositoryValidator russianValidator = new WordRepositoryValidator(Language.RU);
    private final WordRepositoryValidator englishValidator = new WordRepositoryValidator(Language.EN);

    @Test
    void shouldNotThrowException_whenValidRussianWordsProvided() {
        russianValidator.validate(Arrays.asList("привет", "мир"));
    }

    @Test
    void shouldNotThrowException_whenValidEnglishWordsProvided() {
        englishValidator.validate(Arrays.asList("hello", "world"));
    }

    @Test
    void shouldThrowEmptyWordListException_whenEmptyListProvided() {
        assertThatThrownBy(() -> russianValidator.validate(List.of()))
            .isInstanceOf(EmptyWordListException.class);
    }

    @Test
    void shouldThrowInvalidWordException_whenWordContainsInvalidCharacter() {
        assertThatThrownBy(() -> englishValidator.validate(List.of("hello123")))
            .isInstanceOf(InvalidWordException.class)
            .hasMessageContaining("hello123");
    }

    @Test
    void shouldThrowInvalidWordException_whenWordContainsCyrillicCharacters() {
        assertThatThrownBy(() -> russianValidator.validate(List.of("привет123")))
            .isInstanceOf(InvalidWordException.class)
            .hasMessageContaining("привет123");
    }

    @Test
    void shouldThrowInvalidWordException_whenWordContainsDifferentLanguageCharacters() {
        assertThatThrownBy(() -> russianValidator.validate(List.of("hello")))
            .isInstanceOf(InvalidWordException.class)
            .hasMessageContaining("hello");
    }
}
