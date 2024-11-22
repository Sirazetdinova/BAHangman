package backend.academy.dialogs.minmaxdialog;

import backend.academy.dialogs.common.exception.MoreCharactersInputException;
import backend.academy.dialogs.common.validator.Validator;
import backend.academy.dialogs.minmaxdialog.exception.NotDigitException;
import backend.academy.dialogs.minmaxdialog.exception.NumberOutOfRangeException;

public class MinMaxValidator implements Validator<String> {
    private final int min;
    private final int max;

    public MinMaxValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(String playerInput) {
        if (playerInput.length() != 1) {
            throw new MoreCharactersInputException();
        }

        char typedChar = playerInput.charAt(0);
        if (!isDigit(typedChar)) {
            throw new NotDigitException();
        }

        int typedNumber = tryParseInt(playerInput);
        if (typedNumber < min || typedNumber > max) {
            throw new NumberOutOfRangeException(typedNumber);
        }
    }

    private int tryParseInt(String playerInput) {
        try {
            return Integer.parseInt(playerInput);
        } catch (NumberFormatException e) {
            throw new NotDigitException(e);
        }
    }

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }
}
