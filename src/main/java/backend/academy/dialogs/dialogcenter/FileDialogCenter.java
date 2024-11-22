package backend.academy.dialogs.dialogcenter;

import backend.academy.constant.Language;
import backend.academy.dialogs.dialogcenter.exception.UnableGetDialogCenterException;
import backend.academy.util.inireader.IniReader;
import backend.academy.util.inireader.exception.IniValueNotFoundException;
import backend.academy.util.inireader.exception.OpenIniFileException;
import backend.academy.util.inireader.exception.ReadIniFileException;

public class FileDialogCenter implements DialogCenter {
    private final IniReader iniReader;

    public FileDialogCenter(String directory, String filenameTemplate, Language language) {
        String languageName = language.name().toLowerCase();
        String filename = filenameTemplate.formatted(languageName);
        String iniFilename = String.format("%s/%s", directory.formatted(languageName), filename);
        this.iniReader = getIniReader(iniFilename);
    }

    private IniReader getIniReader(String iniFilename) {
        try {
            return new IniReader(iniFilename);
        } catch (OpenIniFileException | ReadIniFileException e) {
            throw new UnableGetDialogCenterException(e);
        }
    }

    @Override
    public String get(String section, String key) {
        try {
            return iniReader.getValue(section, key);
        } catch (IniValueNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
