package backend.academy.util.inireader;

import backend.academy.util.inireader.exception.IniValueNotFoundException;
import backend.academy.util.inireader.exception.OpenIniFileException;
import backend.academy.util.inireader.exception.ReadIniFileException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class IniReader {
    private final String fileName;
    private INIConfiguration iniConfiguration;

    public IniReader(String fileName) {
        this.fileName = "/" + fileName;
        loadIniConfiguration();
    }

    public String getValue(String section, String key) {
        Object value = iniConfiguration.getSection(section).getProperty(key);
        if (value == null) {
            throw new IniValueNotFoundException(section, key);
        }
        return value.toString();
    }

    private void loadIniConfiguration() {
        iniConfiguration = new INIConfiguration();
        InputStream iniAsStream = getIniAsStream(fileName);
        try (var reader = new BufferedReader(new InputStreamReader(iniAsStream))) {
            iniConfiguration.read(reader);
        } catch (IOException | ConfigurationException e) {
            throw new ReadIniFileException(e);
        }
    }

    private InputStream getIniAsStream(String filePath) {
        InputStream resource = IniReader.class.getResourceAsStream(filePath);
        if (resource == null) {
            throw new OpenIniFileException(filePath);
        }
        return resource;
    }
}
