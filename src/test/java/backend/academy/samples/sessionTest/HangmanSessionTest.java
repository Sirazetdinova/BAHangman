package backend.academy.samples.sessionTest;

import backend.academy.dialogs.common.dialog.Dialog;
import backend.academy.display.Display;
import backend.academy.messagecenter.MessageCenter;
import backend.academy.session.HangmanSession;
import backend.academy.session.HiddenWord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

public class HangmanSessionTest {
    private HangmanSession session;
    private HiddenWord hiddenWord;
    private Dialog dialog;
    private Display infoDisplay;
    private Display errorDisplay;
    private MessageCenter messageCenter;

    @BeforeEach
    void setUp() {
        hiddenWord = new HiddenWord("test");
        dialog = mock(Dialog.class);
        infoDisplay = mock(Display.class);
        errorDisplay = mock(Display.class);
        messageCenter = mock(MessageCenter.class);

        // Настройка мока messageCenter
        when(messageCenter.get("HangmanSession", "start")).thenReturn("Game has started");
        when(messageCenter.get("HangmanSession", "no_such_letter_template")).thenReturn("Letter %s is not in the word");
        when(messageCenter.get("HangmanSession", "win")).thenReturn("You win!");
        when(messageCenter.get("HangmanSession", "attempts_are_over")).thenReturn("Attempts are over!");
        when(messageCenter.get("HangmanSession", "hidden_word")).thenReturn("The hidden word was: ");
        when(messageCenter.get("HangmanSession", "left_attempts")).thenReturn("Attempts left: ");
        when(messageCenter.get("HangmanSession", "errors")).thenReturn("Wrong letters: ");
        when(messageCenter.get("HangmanSession", "letter_already_entered")).thenReturn("You have already entered that letter.");

        session = new HangmanSession(HangmanSession.Difficult.EASY, hiddenWord, HangmanSession.Category.NATURE, dialog, infoDisplay, errorDisplay, messageCenter);
    }

    @Test
    void testStartDisplaysStartMessage() {
        session.start();

        verify(infoDisplay).show("Game has started");
        verify(infoDisplay).show(hiddenWord.getMask());
    }

    @Test
    void testStartIncorrectGuess() {
        when(dialog.getInput()).thenReturn("x").thenReturn("y").thenReturn("z");

        session.start();

        verify(errorDisplay, times(3)).show(anyString()); // проверяем, что сообщение об ошибке отображается
    }

    @Test
    void testRevealLetterThrowsNoSuchLetterException() {
        when(dialog.getInput()).thenReturn("x");

        session.start();

        verify(errorDisplay).show(anyString()); // проверяем, что сообщение об ошибке отображается для неверной буквы
    }

    @Test
    void testEndSessionWhenLose() {
        when(dialog.getInput()).thenReturn("x").thenReturn("y").thenReturn("z").thenReturn("a").thenReturn("b").thenReturn("c").thenReturn("d").thenReturn("e");

        session.start();

        verify(errorDisplay).show("Attempts are over!"); // проверяем, что lose message отображается
        verify(infoDisplay).show("The hidden word was: test"); // проверяем, что скрытое слово отображается
    }
}
