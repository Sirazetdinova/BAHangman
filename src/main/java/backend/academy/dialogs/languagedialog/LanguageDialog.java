package backend.academy.dialogs.languagedialog;

import backend.academy.constant.Language;
import backend.academy.dialogs.common.Printer;
import backend.academy.dialogs.optiondialog.OptionDialog;
import java.util.Arrays;

public class LanguageDialog extends OptionDialog {
    private static final String[] LANGUAGES = Arrays.stream(Language.values()).map(Enum::name).toArray(String[]::new);

    public LanguageDialog(Printer infoPrinter, Printer errorPrinter, String title) {
        super(infoPrinter, errorPrinter, new LanguageMessageMapper(), title, LANGUAGES);
    }

    public LanguageDialog(Printer printer, String title) {
        super(printer, title, new LanguageMessageMapper(), LANGUAGES);
    }
}
