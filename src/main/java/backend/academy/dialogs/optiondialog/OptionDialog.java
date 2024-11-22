package backend.academy.dialogs.optiondialog;

import backend.academy.dialogs.common.Printer;
import backend.academy.dialogs.common.dialog.AbstractDialog;
import backend.academy.dialogs.common.messagemapper.MessageMapper;
import backend.academy.dialogs.dialogcenter.DialogCenter;
import java.util.List;

public class OptionDialog extends AbstractDialog {
    public OptionDialog(
        Printer infoPrinter,
        Printer errorPrinter,
        DialogCenter dialogCenter,
        String title,
        String... options
    ) {
        super(infoPrinter, errorPrinter, title, new OptionMessageMapper(dialogCenter),
            new OptionValidator(List.of(options)));
    }

    public OptionDialog(Printer printer, DialogCenter dialogCenter, String title, String... options) {
        super(printer, title, new OptionMessageMapper(dialogCenter), new OptionValidator(List.of(options)));
    }

    protected OptionDialog(
        Printer infoPrinter,
        Printer errorPrinter,
        MessageMapper messageMapper,
        String title,
        String... options
    ) {
        super(infoPrinter, errorPrinter, title, messageMapper, new OptionValidator(List.of(options)));
    }

    protected OptionDialog(Printer printer, String title, MessageMapper messageMapper, String... options) {
        super(printer, title, messageMapper, new OptionValidator(List.of(options)));
    }
}
