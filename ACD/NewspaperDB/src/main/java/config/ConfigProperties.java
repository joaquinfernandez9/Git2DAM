package config;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;


@Singleton
@Getter
@Log4j2
public class ConfigProperties {

    // for paths

    private static ConfigProperties instance=null;
    private Properties p;

    private ConfigProperties() {
        try {
            p = new Properties();
            p.load(ConfigProperties.class.getClassLoader()
                    .getResourceAsStream("config.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigProperties getInstance() {

        if (instance==null) {
            instance=new ConfigProperties();
        }
        return instance;
    }

    public String getProperty(String key) {
        return p.getProperty(key);
    }
}
