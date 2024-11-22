package backend.academy.samples.sessionTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import backend.academy.session.HiddenWord;
import backend.academy.exception.NoSuchLetterException;

public class HiddenWordTest {
    @Test
    void testRevealCorrectLetter() {
        HiddenWord hiddenWord = new HiddenWord("hello");
        String mask = hiddenWord.revealLetter("h");
        assertEquals("H____", mask);
    }

    @Test
    void testRevealMultipleLetters() {
        HiddenWord hiddenWord = new HiddenWord("banana");
        hiddenWord.revealLetter("a");
        String mask = hiddenWord.revealLetter("n");
        assertEquals("_ANANA", mask);
    }

    @Test
    void testRevealNonExistingLetter() {
        HiddenWord hiddenWord = new HiddenWord("apple");
        assertThrows(NoSuchLetterException.class, () -> hiddenWord.revealLetter("z"));
    }

    @Test
    void testIsGuessed() {
        HiddenWord hiddenWord = new HiddenWord("dog");
        hiddenWord.revealLetter("d");
        hiddenWord.revealLetter("o");
        hiddenWord.revealLetter("g");
        assertTrue(hiddenWord.isGuessed());
    }
}
