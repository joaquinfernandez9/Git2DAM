package config;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;
import java.util.Map;

@Log4j2
@Getter
@Singleton
public class Configuration {

    private String url;
    private String user;
    private String password;
    private String driver;

    public Configuration() {
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> propertiesMap = yaml.load(getClass().getClassLoader().getResourceAsStream(Const.CONFIG_CONFIG_YAML));
            this.url = (String) propertiesMap.get(Const.URL);
            this.password = (String) propertiesMap.get(Const.PASSWORD);
            this.user = (String) propertiesMap.get(Const.USER);
            this.driver = (String) propertiesMap.get(Const.DRIVER);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

    }

}
