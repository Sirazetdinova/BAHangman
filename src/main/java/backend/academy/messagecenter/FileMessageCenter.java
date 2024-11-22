package backend.academy.messagecenter;

import backend.academy.constant.Language;
import backend.academy.messagecenter.exception.MessageNotFoundException;
import backend.academy.messagecenter.exception.UnableGetMessageCenterException;
import backend.academy.util.inireader.IniReader;
import backend.academy.util.inireader.exception.IniValueNotFoundException;
import backend.academy.util.inireader.exception.OpenIniFileException;
import backend.academy.util.inireader.exception.ReadIniFileException;

public class FileMessageCenter implements MessageCenter {
    private final IniReader iniReader;

    public FileMessageCenter(String directory, String filenameTemplate, Language language) {
        String languageName = language.name().toLowerCase();
        String filename = filenameTemplate.formatted(languageName);
        String iniFilename = String.format("%s/%s", directory.formatted(languageName), filename);
        this.iniReader = getIniReader(iniFilename);
    }

    private IniReader getIniReader(String iniFilename) {
        try {
            return new IniReader(iniFilename);
        } catch (OpenIniFileException | ReadIniFileException e) {
            throw new UnableGetMessageCenterException(e);
        }
    }

    @Override
    public String get(String section, String key) {
        try {
            return iniReader.getValue(section, key);
        } catch (IniValueNotFoundException e) {
            throw new MessageNotFoundException(e);
        }
    }
}
