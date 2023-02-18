package dao.api.config;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Config {

        private String baseUrl = null;

        public Config() {
            Properties properties = new Properties();
            try {
                properties.load(getClass().getClassLoader().getResourceAsStream("config/api.yaml"));
                this.baseUrl = (String) properties.get("base_url");
                log.info("API base url: {}", this.baseUrl);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
}
