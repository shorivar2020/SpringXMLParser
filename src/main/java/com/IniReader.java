package com;

import java.io.File;

import lombok.Getter;
import org.ini4j.Wini;

/*Read configuration file type IMI*/
@Getter
public class IniReader {
    public String url;
    String username;
    String password;
    String urlDB;

    public IniReader (org.slf4j.Logger log, String path) throws Exception{
        Wini ini = new Wini(new File(path));
        this.url = ini.get("website", "url", String.class);
        this.username = ini.get("database", "username", String.class);
        this.urlDB = ini.get("database", "urlDB", String.class);
        this.password = ini.get("database", "password", String.class);
        log.info("Data was received from Config file");
    }
}