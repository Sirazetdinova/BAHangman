package backend.academy.samples.launcherTest;

import backend.academy.launcher.HangmanGameLauncher;
import backend.academy.constant.Language;
import backend.academy.dialogs.common.dialog.Dialog;
import backend.academy.dialogs.dialogcenter.DialogCenter;
import backend.academy.display.Display;
import backend.academy.messagecenter.MessageCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class HangmanGameLauncherTest {
    private Display infoDisplayMock;
    private MessageCenter messageCenterMock;
    private Dialog dialogMock;  // объявляем dialogMock
    private HangmanGameLauncher launcher;

    @BeforeEach
    public void setUp() throws Exception {
        infoDisplayMock = mock(Display.class);
        Display errorDisplayMock = mock(Display.class);
        messageCenterMock = mock(MessageCenter.class);
        DialogCenter dialogCenterMock = mock(DialogCenter.class);
        dialogMock = mock(Dialog.class);  // инициализируем dialogMock

        // Используем рефлексию для установки диалога в приватное поле
        launcher = new HangmanGameLauncher(
            infoDisplayMock,
            errorDisplayMock,
            Language.EN,
            messageCenterMock,
            dialogCenterMock
        );

        // Используем рефлексию для установки mock Dialog
        Field dialogField = HangmanGameLauncher.class.getDeclaredField("dialog");
        dialogField.setAccessible(true);
        dialogField.set(launcher, dialogMock);
    }

    @Test
    public void testStart_GameLoopRuns() throws Exception {
        // Arrange: замокируем возвращаемое сообщение и последовательность команд
        when(messageCenterMock.get("Launcher", "welcome")).thenReturn("Welcome to Hangman!");
        when(messageCenterMock.get("Launcher", "start_template"))
            .thenReturn("Press %s to start a new game or %s to exit.");

        // Мокируем последовательность ввода: сначала "1" для старта игры, затем "2" для выхода
        when(dialogMock.getInput())
            .thenReturn(getPrivateField("START_NEW_GAME_COMMAND"))  // Команда старта игры
            .thenReturn(getPrivateField("EXIT_COMMAND"));  // Команда выхода из игры

        // Act: используем рефлексию для вызова приватного метода start()
        Method startMethod = HangmanGameLauncher.class.getDeclaredMethod("start");
        startMethod.setAccessible(true);
        startMethod.invoke(launcher);

        // Assert
        verify(infoDisplayMock).show("Welcome to Hangman!");
        verify(infoDisplayMock).show("Press 1 to start a new game or 2 to exit.");

        // Проверяем приватное поле "running", чтобы убедиться, что цикл завершен
        Field runningField = HangmanGameLauncher.class.getDeclaredField("running");
        runningField.setAccessible(true);
        boolean running = (boolean) runningField.get(launcher);
        assertThat(running).isFalse();  // running должно стать false после команды выхода
    }

    // Вспомогательный метод для получения приватных полей через рефлексию
    private String getPrivateField(String fieldName) throws Exception {
        Field field = HangmanGameLauncher.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (String) field.get(null);  // так как поле статическое, передаем null
    }
}
