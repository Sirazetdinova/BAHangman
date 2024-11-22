package backend.academy.samples.repositoryTest;

import backend.academy.repository.FileWordRepository;
import backend.academy.session.HangmanSession;
import backend.academy.constant.Language;
import backend.academy.exception.OpenWordsFileException;
import backend.academy.exception.ReadWordsFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileWordRepositoryTest {
    private FileWordRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FileWordRepository("words", "testWords", Language.EN, HangmanSession.Category.RANDOM);
    }

    @Test
    void testGetReturnsValidWord() {
        String word = repository.get();
        assertNotNull(word);
        assertFalse(word.isEmpty());
    }

    @Test
    void testLoadWordsHandlesEmptyFile() {
        repository = new FileWordRepository("words", "empty", Language.EN, HangmanSession.Category.RANDOM);
        assertThrows(ReadWordsFileException.class, repository::get);
    }

    @Test
    void testLoadWordsHandlesNonExistentFile() {
        repository = new FileWordRepository("words", "nonexistent", Language.EN, HangmanSession.Category.RANDOM);
        assertThrows(OpenWordsFileException.class, repository::get);
    }
}
