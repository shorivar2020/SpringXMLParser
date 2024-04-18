package xml_parser;

import lombok.Getter;
import lombok.extern.java.Log;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

@Getter
@Log
public class IniReader {
    private String url;
    private String username;
    private String password;
    private String urlDB;
    public IniReader(String path) {
        try {
            Ini ini = new Ini(new File(path));
            Preferences prefs = new IniPreferences(ini);
            if (ini.containsKey("url")) {
                this.url = prefs.node("url").get("url", null);
            } else {
                log.warning("Section 'url' not found in the ini file.");
            }
            if (ini.containsKey("database")) {
                Preferences dbPrefs = prefs.node("database");
                this.username = dbPrefs.get("username", null);
                this.urlDB = dbPrefs.get("urlDB", null);
                this.password = dbPrefs.get("password", null);
            } else {
                log.warning("Section 'database' not found in the ini file.");
            }
            log.info("Data was successfully loaded from the config file.");
        } catch (IOException e) {
            log.severe("Error occurred while reading the ini file: " + e.getMessage());
        }
    }
}
