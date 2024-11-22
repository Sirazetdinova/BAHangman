package backend.academy.dialogs.minmaxdialog.launcherminmaxdialog;

import backend.academy.dialogs.common.Printer;
import backend.academy.dialogs.dialogcenter.DialogCenter;
import backend.academy.dialogs.minmaxdialog.MinMaxDialog;

public class LauncherMinMaxDialog extends MinMaxDialog {
    public LauncherMinMaxDialog(
        Printer infoPrinter,
        Printer errorPrinter,
        DialogCenter dialogCenter,
        String title,
        int max,
        int min
    ) {
        super(infoPrinter, errorPrinter, title, new LauncherMinMaxMessageMapper(dialogCenter), min, max);
    }
}
