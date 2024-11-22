package backend.academy.session;

import backend.academy.dialogs.common.dialog.Dialog;
import backend.academy.display.Display;
import backend.academy.exception.NoSuchLetterException;
import backend.academy.messagecenter.MessageCenter;
import backend.academy.picture.EasyHangmanPicture;
import backend.academy.picture.HangmanPicture;
import backend.academy.picture.PictureProvider;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class HangmanSession {
    private final HiddenWord hiddenWord;
    private final Difficult difficult;
    private final Category category;
    private final Dialog dialog;
    private final Display infoDisplay;
    private final Display errorDisplay;
    private final MessageCenter messageCenter;
    private final PictureProvider pictureProvider;
    private final Set<String> wrongLetters = new HashSet<>();
    private int leftAttempts;
    private boolean isSessionOn = true;

    public HangmanSession(
        Difficult difficult,
        HiddenWord hiddenWord,
        Category category,
        Dialog dialog,
        Display infoDisplay,
        Display errorDisplay,
        MessageCenter messageCenter
    ) {
        this.hiddenWord = hiddenWord;
        this.difficult = difficult;
        this.category = category;
        this.dialog = dialog;
        this.infoDisplay = infoDisplay;
        this.errorDisplay = errorDisplay;
        this.messageCenter = messageCenter;
        this.leftAttempts = difficult.maxAttempts;
        this.pictureProvider = difficult.pictureProvider;
        displayStartMessage();
    }

    public HangmanSession(
        Difficult difficult,
        HiddenWord hiddenWord,
        Dialog dialog,
        Display display,
        MessageCenter messageCenter,
        Category category
    ) {
        this(difficult, hiddenWord, category, dialog, display, display, messageCenter);
    }

    private void displayStartMessage() {
        infoDisplay.show(messageCenter.get(MessageKey.START.section, MessageKey.START.key));
        infoDisplay.show(hiddenWord.getMask());
        displayHangmanPicture();
    }

    public void start() {
        while (isSessionOn) {
            String letter = dialog.getInput();

            Optional<String> mask = getWordMaskWithRevealedLetter(letter);
            if (mask.isEmpty()) {
                continue;
            }
            infoDisplay.show(mask.get());
            if (hiddenWord.isGuessed()) {
                endSession(Result.WIN);
            }
        }
    }

    private Optional<String> getWordMaskWithRevealedLetter(String letter) {
        try {
            return Optional.of(hiddenWord.revealLetter(letter));
        } catch (RuntimeException e) {
            handleException(e);
            return Optional.empty();
        }
    }

    private void handleException(RuntimeException e) {
        String message = convertExceptionToText(e);
        errorDisplay.show(message);

        if (e instanceof NoSuchLetterException noSuchLetterException) {
            String wrongLetter = noSuchLetterException.getWrongLetter();
            handleWrongLetter(wrongLetter);
        }
    }

    private String convertExceptionToText(RuntimeException e) {
        try {
            throw e;
        } catch (NoSuchLetterException exc) {
            String wrongLetter = exc.getWrongLetter();
            String noSuchLetterTemplate =
                messageCenter.get(MessageKey.NO_SUCH_LETTER_TEMPLATE.section, MessageKey.NO_SUCH_LETTER_TEMPLATE.key);
            return noSuchLetterTemplate.formatted(wrongLetter);
        } catch (RuntimeException exc) {
            throw new IllegalArgumentException("Unable convert exception to text: " + exc);
        }
    }

    private void handleWrongLetter(String letter) {
        String upperCaseLetter = letter.toUpperCase();
        if (!wrongLetters.contains(upperCaseLetter)) {
            wrongLetters.add(upperCaseLetter);
        } else {
            errorDisplay.show(
                messageCenter.get(MessageKey.LETTER_ALREADY_ENTERED.section, MessageKey.LETTER_ALREADY_ENTERED.key));
            displayErrorMessage();
            return;
        }

        updateLeftAttempts();
    }

    private void updateLeftAttempts() {
        --leftAttempts;
        if (leftAttempts == 0) {
            endSession(Result.LOSE);
        } else {
            displayErrorMessage();
        }
        displayHangmanPicture();
    }

    private void displayErrorMessage() {
        displayLeftAttempts();
        displayWrongLetters();
        infoDisplay.show(hiddenWord.getMask());
    }

    private void displayHangmanPicture() {
        String picture = pictureProvider.get(difficult.maxAttempts - leftAttempts);
        infoDisplay.show(picture);
    }

    private void displayWrongLetters() {
        String errorsMessage = messageCenter.get(MessageKey.ERRORS.section, MessageKey.ERRORS.key);
        infoDisplay.show(errorsMessage + getStringOfWrongLetters());
    }

    private String getStringOfWrongLetters() {
        StringBuilder letters = new StringBuilder();
        for (String letter : wrongLetters) {
            letters.append(letter).append(" ");
        }
        return letters.toString();
    }

    private void displayLeftAttempts() {
        String leftAttemptsMessage = messageCenter.get(MessageKey.LEFT_ATTEMPTS.section, MessageKey.LEFT_ATTEMPTS.key);
        infoDisplay.show(leftAttemptsMessage + leftAttempts);
    }

    private void endSession(Result result) {
        if (isWin(result)) {
            displayWinMessage();
        } else if (isLose(result)) {
            displayLoseMessage();
        }
        isSessionOn = false;
    }

    private void displayWinMessage() {
        infoDisplay.show(messageCenter.get(MessageKey.WIN.section, MessageKey.WIN.key));
    }

    private void displayLoseMessage() {
        errorDisplay.show(messageCenter.get(MessageKey.ATTEMPTS_ARE_OVER.section, MessageKey.ATTEMPTS_ARE_OVER.key));
        displayWrongLetters();
        String revealedWord = hiddenWord.reveal();
        String hiddenWordMessage = messageCenter.get(MessageKey.HIDDEN_WORD.section, MessageKey.HIDDEN_WORD.key);
        infoDisplay.show(hiddenWordMessage + revealedWord);
    }

    private boolean isLose(Result result) {
        return result == Result.LOSE;
    }

    private boolean isWin(Result result) {
        return result == Result.WIN;
    }

    public enum Difficult {
        EASY(8, new EasyHangmanPicture()), CLASSIC(6, new HangmanPicture());

        public final int maxAttempts;
        public final PictureProvider pictureProvider;

        Difficult(int maxAttempts, PictureProvider pictureProvider) {
            this.maxAttempts = maxAttempts;
            this.pictureProvider = pictureProvider;
        }
    }

    public enum Category {
        NATURE("nature"),
        COUNTRIES("countries"),
        ANIMALS("animals"),
        RANDOM("random");

        private final String categoryName;

        Category(String categoryName) {
            this.categoryName = categoryName;
        }
        public String getCategoryName() {
            return categoryName;
        }
    }

    private enum Result {
        WIN, LOSE
    }

    private enum MessageKey {
        START("start"),
        ATTEMPTS_ARE_OVER("attempts_are_over"),
        WIN("win"),
        NO_SUCH_LETTER_TEMPLATE("no_such_letter_template"),
        LETTER_ALREADY_ENTERED("letter_already_entered"),
        ERRORS("errors"),
        LEFT_ATTEMPTS("left_attempts"),
        HIDDEN_WORD("hidden_word");

        public final String section = "HangmanSession";
        public final String key;

        MessageKey(String key) {
            this.key = key;
        }
    }
}
