package dao.retrofit.config;

import dao.Common;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class ConfigApi {
    private String baseUrl = null;

    public ConfigApi() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader()
                    .getResourceAsStream(Common.CONFIG_FILE));
            this.baseUrl = (String) properties.get(Common.BASE_URL);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
