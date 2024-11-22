package backend.academy.launcher;

import backend.academy.constant.Language;
import backend.academy.dialogs.categorydialog.CategoryDialog;
import backend.academy.dialogs.common.dialog.Dialog;
import backend.academy.dialogs.dialogcenter.DialogCenter;
import backend.academy.dialogs.difficultdialog.DifficultDialog;
import backend.academy.dialogs.letterdialog.LetterDialog;
import backend.academy.dialogs.minmaxdialog.launcherminmaxdialog.LauncherMinMaxDialog;
import backend.academy.display.Display;
import backend.academy.exception.EmptyWordListException;
import backend.academy.exception.InvalidWordException;
import backend.academy.exception.OpenWordsFileException;
import backend.academy.exception.ReadWordsFileException;
import backend.academy.messagecenter.MessageCenter;
import backend.academy.repository.FileWordRepository;
import backend.academy.repository.WordRepository;
import backend.academy.session.HangmanSession;
import backend.academy.session.HangmanSession.Category;
import backend.academy.session.HangmanSession.Difficult;
import backend.academy.session.HiddenWord;
import java.util.List;
import java.util.Optional;

public class HangmanGameLauncher {
    private static final String START_NEW_GAME_COMMAND = "1";
    private static final String EXIT_COMMAND = "2";
    private static final String EASY_DIFFICULT_COMMAND = "1";
    private static final String CLASSIC_DIFFICULT_COMMAND = "2";
    private static final String NATURE_CATEGORY_COMMAND = "1";
    private static final String COUNTRIES_CATEGORY_COMMAND = "2";
    private static final String ANIMALS_CATEGORY_COMMAND = "3";
    private static final String RANDOM_CATEGORY_COMMAND = "4";
    private static final String INPUT_PROMPT = ">>> ";
    private static final String PROMPT_TEMPLATE = "%s\n>>>";
    private final Dialog dialog;
    private final Display infoDisplay;
    private final Display errorDisplay;
    private final MessageCenter messageCenter;
    private final DialogCenter dialogCenter;
    private final Language language;
    private WordRepository wordRepository;
    private boolean running;

    public HangmanGameLauncher(
        Display infoDisplay,
        Display errorDisplay,
        Language language,
        MessageCenter messageCenter,
        DialogCenter dialogCenter
    ) {
        this.dialog = new LauncherMinMaxDialog(infoDisplay::show, errorDisplay::show, dialogCenter, INPUT_PROMPT, 2, 1);
        this.infoDisplay = infoDisplay;
        this.errorDisplay = errorDisplay;
        this.messageCenter = messageCenter;
        this.dialogCenter = dialogCenter;
        this.language = language;
    }

    public HangmanGameLauncher(
        Display display,
        Language language,
        MessageCenter messageCenter,
        DialogCenter dialogCenter
    ) {
        this(display, display, language, messageCenter, dialogCenter);
    }

    public void start() {
        running = true;
        infoDisplay.show(messageCenter.get(MessageKey.WELCOME.section, MessageKey.WELCOME.key));

        while (running) {
            displayStartMessage();
            String playerInput = dialog.getInput();
            chooseAction(playerInput);
        }
    }

    private void displayStartMessage() {
        String startTemplate = messageCenter.get(MessageKey.START_TEMPLATE.section, MessageKey.START_TEMPLATE.key);
        infoDisplay.show(startTemplate.formatted(START_NEW_GAME_COMMAND, EXIT_COMMAND));
    }

    private void chooseAction(String playerInput) {
        switch (playerInput) {
            case START_NEW_GAME_COMMAND -> startNewGame();
            case EXIT_COMMAND -> exit();
            default -> errorDisplay.show(
                messageCenter.get(MessageKey.INVALID_COMMAND.section, MessageKey.INVALID_COMMAND.key));
        }
    }

    private void startNewGame() {
        Optional<Category> categoryOptional = getCategory();
        Category category = null;
        if (categoryOptional.isPresent()) {
            category = categoryOptional.get();
            this.wordRepository = new FileWordRepository("words", "%s", language, category);
        }

        Optional<HiddenWord> wordOptional = getHiddenWord();
        HiddenWord word = null;
        if (wordOptional.isPresent()) {
            word = wordOptional.get();
        }

        Optional<Difficult> difficultOptional = getDifficult();
        Difficult difficult = null;
        if (difficultOptional.isPresent()) {
            difficult = difficultOptional.get();
        }

        HangmanSession hangmanSession = createHangmanSession(word, difficult, category);
        hangmanSession.start();
    }

    private Optional<HiddenWord> getHiddenWord() {
        try {
            String text = wordRepository.get();
            HiddenWord hiddenWord = new HiddenWord(text);
            return Optional.of(hiddenWord);
        } catch (OpenWordsFileException e) {
            handleWordRepositoryException(
                messageCenter.get(MessageKey.OPEN_FILE_ERROR.section, MessageKey.OPEN_FILE_ERROR.key)
            );
        } catch (ReadWordsFileException e) {
            handleWordRepositoryException(
                messageCenter.get(MessageKey.READ_FILE_ERROR.section, MessageKey.READ_FILE_ERROR.key)
            );
        } catch (EmptyWordListException e) {
            handleWordRepositoryException(
                messageCenter.get(MessageKey.EMPTY_WORD_LIST.section, MessageKey.EMPTY_WORD_LIST.key)
            );
        } catch (InvalidWordException e) {
            String invalidWordMessage = messageCenter.get(
                MessageKey.INVALID_WORD_TEMPLATE.section,
                MessageKey.INVALID_WORD_TEMPLATE.key
            ).formatted(e.getInvalidWord());
            handleWordRepositoryException(invalidWordMessage);
        }
        return Optional.empty();
    }

    private void handleWordRepositoryException(String exceptionMessage) {
        errorDisplay.show(exceptionMessage);
        errorDisplay.show(
            messageCenter.get(MessageKey.CANT_CONTINUE.section, MessageKey.CANT_CONTINUE.key)
        );
        exit();
    }

    private Optional<Difficult> getDifficult() {
        String difficultInput = inputDifficult();
        return switch (difficultInput) {
            case EASY_DIFFICULT_COMMAND -> Optional.of(Difficult.EASY);
            case CLASSIC_DIFFICULT_COMMAND -> Optional.of(Difficult.CLASSIC);
            default -> Optional.empty();
        };
    }

    private String inputDifficult() {
        String difficultDialogMessage = getDifficultMessage();
        String[] difficultCommands = List.of(EASY_DIFFICULT_COMMAND, CLASSIC_DIFFICULT_COMMAND).toArray(String[]::new);
        Dialog difficultDialog =
            new DifficultDialog(infoDisplay::show, errorDisplay::show, dialogCenter, difficultDialogMessage,
                difficultCommands);
        return difficultDialog.getInput();
    }

    private String getDifficultMessage() {
        String difficultTemplate =
            messageCenter.get(MessageKey.DIFFICULT_TEMPLATE.section, MessageKey.DIFFICULT_TEMPLATE.key);
        return String.format(PROMPT_TEMPLATE, difficultTemplate.formatted(
            EASY_DIFFICULT_COMMAND, Difficult.EASY.maxAttempts,
            CLASSIC_DIFFICULT_COMMAND, Difficult.CLASSIC.maxAttempts));
    }

    private Optional<Category> getCategory() {
        String categoryInput = inputCategory();
        return switch (categoryInput) {
            case NATURE_CATEGORY_COMMAND -> Optional.of(Category.NATURE);
            case COUNTRIES_CATEGORY_COMMAND -> Optional.of(Category.COUNTRIES);
            case ANIMALS_CATEGORY_COMMAND -> Optional.of(Category.ANIMALS);
            case RANDOM_CATEGORY_COMMAND -> Optional.of(Category.RANDOM);
            default -> Optional.empty();
        };
    }

    private String inputCategory() {
        String categoryDialogMessage = getCategoryMessage();
        String[] categoryCommands =
            List.of(NATURE_CATEGORY_COMMAND, COUNTRIES_CATEGORY_COMMAND, ANIMALS_CATEGORY_COMMAND,
                RANDOM_CATEGORY_COMMAND).toArray(String[]::new);
        Dialog categoryDialog =
            new CategoryDialog(infoDisplay::show, errorDisplay::show, dialogCenter, categoryDialogMessage,
                categoryCommands);
        return categoryDialog.getInput();

    }

    private String getCategoryMessage() {
        String categoryTemplate =
            messageCenter.get(MessageKey.CATEGORY_TEMPLATE.section, MessageKey.CATEGORY_TEMPLATE.key);
        return String.format(PROMPT_TEMPLATE, categoryTemplate.formatted(
            NATURE_CATEGORY_COMMAND,
            COUNTRIES_CATEGORY_COMMAND,
            ANIMALS_CATEGORY_COMMAND,
            RANDOM_CATEGORY_COMMAND));
    }

    private HangmanSession createHangmanSession(HiddenWord word, Difficult difficult, Category category) {
        Dialog sessionDialog =
            new LetterDialog(infoDisplay::show, errorDisplay::show, dialogCenter, language, INPUT_PROMPT);
        return new HangmanSession(difficult, word, category, sessionDialog, infoDisplay, errorDisplay, messageCenter);
    }

    private void exit() {
        infoDisplay.show(messageCenter.get(MessageKey.EXIT.section, MessageKey.EXIT.key));
        running = false;
    }

    private enum MessageKey {
        START_TEMPLATE("start_template"),
        DIFFICULT_TEMPLATE("difficult_template"),
        CATEGORY_TEMPLATE("category_template"),
        WELCOME("welcome"),
        EXIT("exit"),
        CANT_CONTINUE("cant_continue"),
        OPEN_FILE_ERROR("open_file_error"),
        READ_FILE_ERROR("read_file_error"),
        EMPTY_WORD_LIST("empty_word_list"),
        INVALID_COMMAND("invalid_command"),
        INVALID_WORD_TEMPLATE("invalid_word_template");

        public final String section = "Launcher";
        public final String key;

        MessageKey(String key) {
            this.key = key;
        }
    }
}
