package config;

import jakarta.inject.Singleton;

import java.io.IOException;
import java.util.Properties;

@Singleton
public class Config {
    private static Config instance=null;
    private Properties properties;

    public Config() {
        try {
            properties = new Properties();
            properties.loadFromXML(Config.class
                    .getClassLoader().getResourceAsStream("properties.xml"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance(){
        if (instance==null) {
            instance=new Config();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
