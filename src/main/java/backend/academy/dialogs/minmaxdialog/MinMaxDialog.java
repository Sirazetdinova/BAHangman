package backend.academy.dialogs.minmaxdialog;

import backend.academy.dialogs.common.Printer;
import backend.academy.dialogs.common.dialog.AbstractDialog;
import backend.academy.dialogs.common.messagemapper.MessageMapper;
import backend.academy.dialogs.dialogcenter.DialogCenter;

public class MinMaxDialog extends AbstractDialog {
    public MinMaxDialog(
        Printer infoPrinter,
        Printer errorPrinter,
        DialogCenter dialogCenter,
        String title,
        int min,
        int max
    ) {
        super(infoPrinter, errorPrinter, title, new MinMaxMessageMapper(dialogCenter), new MinMaxValidator(min, max));
    }

    public MinMaxDialog(Printer printer, DialogCenter dialogCenter, String title, int min, int max) {
        super(printer, title, new MinMaxMessageMapper(dialogCenter), new MinMaxValidator(min, max));
    }

    protected MinMaxDialog(
        Printer infoPrinter,
        Printer errorPrinter,
        String title,
        MessageMapper messageMapper,
        int min,
        int max
    ) {
        super(infoPrinter, errorPrinter, title, messageMapper, new MinMaxValidator(min, max));
    }

    protected MinMaxDialog(Printer printer, String title, MessageMapper messageMapper, int min, int max) {
        super(printer, title, messageMapper, new MinMaxValidator(min, max));
    }
}
