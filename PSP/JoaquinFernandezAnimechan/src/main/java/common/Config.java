package common;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Config {
    private String path;

    public Config(){
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

            this.path = p.getProperty("path");
        }catch (IOException e){
            log.error(e.getMessage());
        }
    }
}
