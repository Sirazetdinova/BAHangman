package backend.academy.dialogs.categorydialog;

import backend.academy.dialogs.common.Printer;
import backend.academy.dialogs.dialogcenter.DialogCenter;
import backend.academy.dialogs.optiondialog.OptionDialog;

public class CategoryDialog extends OptionDialog {
    public CategoryDialog(
        Printer infoPrinter,
        Printer errorPrinter,
        DialogCenter dialogCenter,
        String title,
        String... options
    ) {
        super(infoPrinter, errorPrinter, new CategoryMessageMapper(dialogCenter), title, options);
    }

    public CategoryDialog(Printer printer, String title, DialogCenter dialogCenter, String... options) {
        super(printer, title, new CategoryMessageMapper(dialogCenter), options);
    }
}
