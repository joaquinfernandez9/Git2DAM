package config;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

@Log4j2
@Getter
@Singleton
public class Configuration {
    private String url;
    private String user;
    private String password;
    private String driver;
    private int time;



    public Configuration() {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream(Const.CONFIG_CONFIG_YAML));
            url = (String) p.get(Const.URL);
            password = (String) p.get(Const.PASSWORD);
            user = (String) p.get(Const.USER);
            driver = (String) p.get(Const.DRIVER);
            time = Integer.parseInt((String) p.get("time"));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

    }
}
