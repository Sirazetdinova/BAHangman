package backend.academy.dialogs.letterdialog;

import backend.academy.constant.Language;
import backend.academy.dialogs.common.Printer;
import backend.academy.dialogs.common.dialog.AbstractDialog;
import backend.academy.dialogs.common.validator.Validator;
import backend.academy.dialogs.dialogcenter.DialogCenter;
import backend.academy.dialogs.letterdialog.en.EnLetterValidator;
import backend.academy.dialogs.letterdialog.ru.RuLetterValidator;

public class LetterDialog extends AbstractDialog {
    public LetterDialog(
        Printer infoPrinter,
        Printer errorPrinter,
        DialogCenter dialogCenter,
        Language language,
        String title
    ) {
        super(infoPrinter, errorPrinter, title, new LetterMessageMapper(dialogCenter), createValidator(language));
    }

    private static Validator<String> createValidator(Language language) {
        return switch (language) {
            case RU -> new RuLetterValidator();
            case EN -> new EnLetterValidator();
        };
    }
}
