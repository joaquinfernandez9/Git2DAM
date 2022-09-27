package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class ConfigYaml {

    //for users

    private static ConfigYaml instance = null;
    private Properties p;

    public ConfigYaml() {
        try {
            p = new Properties();
            p.load(ConfigYaml.class.getClassLoader()
                    .getResourceAsStream("path.yaml"));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static ConfigYaml getInstance() {

        if (instance == null) {
            instance = new ConfigYaml();
        }
        return instance;
    }

    public String getProperty(String key) {
        return p.getProperty(key);
    }

}
