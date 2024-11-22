package backend.academy.samples.dialogsTest.common;

import backend.academy.dialogs.common.Printer;
import backend.academy.dialogs.common.messagemapper.MessageMapper;
import backend.academy.dialogs.common.validator.Validator;
import backend.academy.dialogs.common.dialog.AbstractDialog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AbstractDialogTest {
    private Printer infoPrinter;
    private Printer errorPrinter;
    private MessageMapper messageMapper;
    private Validator<String> validator;
    private AbstractDialog dialog;

    @BeforeEach
    void setUp() {
        infoPrinter = mock(Printer.class);
        errorPrinter = mock(Printer.class);
        messageMapper = mock(MessageMapper.class);
        validator = mock(Validator.class);

        dialog = new AbstractDialog(infoPrinter, errorPrinter, "Test Dialog", messageMapper, validator) {
        };
    }

    @Test
    void shouldReturnInputWhenValidInputProvided() {
        InputStream in = new ByteArrayInputStream("valid input\n".getBytes());
        System.setIn(in);

        doNothing().when(validator).validate("valid input");

        String result = dialog.getInput();

        assertThat(result).isEqualTo("valid input");
        verify(infoPrinter).print("Test Dialog");
    }

    @Test
    void shouldRepeatInputWhenInvalidInputProvided() {
        InputStream in = new ByteArrayInputStream("invalid input\nvalid input\n".getBytes());
        System.setIn(in);

        doThrow(new RuntimeException("Invalid input"))
            .when(validator).validate("invalid input");
        doNothing().when(validator).validate("valid input");
        when(messageMapper.apply(any(RuntimeException.class))).thenReturn("Invalid input");

        String result = dialog.getInput();

        assertThat(result).isEqualTo("valid input");
        verify(errorPrinter).print("Invalid input");
    }
}
